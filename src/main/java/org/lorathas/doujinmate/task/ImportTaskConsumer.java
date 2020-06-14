package org.lorathas.doujinmate.task;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.hibernate.SessionFactory;
import org.lorathas.doujinmate.Settings;
import org.lorathas.doujinmate.entity.Doujin;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class ImportTaskConsumer implements Runnable {

    private static final int CHUNK_SIZE = 16 * 1024;

    private final BlockingQueue<ImportTask> importTaskQueue;
    private final SessionFactory sessionFactory;
    private final Settings settings;

    public ImportTaskConsumer(BlockingQueue<ImportTask> importTaskQueue, SessionFactory sessionFactory, Settings settings) {
        this.importTaskQueue = importTaskQueue;
        this.sessionFactory = sessionFactory;
        this.settings = settings;
    }

    private Set<Path> getPathsToImport(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (var stream = Files.walk(path)) {
                return stream
                        .filter(f -> !Files.isDirectory(f))
//                        .map(Path::getFileName)
                        .collect(Collectors.toSet());
            }
        } else {
            var set = new HashSet<Path>();
            set.add(path);
            return set;
        }
    }

    private static final List<String> VALID_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".bmp", ".gif");

    private static boolean isValidImageFilename(String filename) {
        for (var ext : VALID_EXTENSIONS) {
            if (filename.endsWith(ext)) {
                return true;
            }
        }

        return false;
    }

    private void processImportTask(ImportTask importTask) {
        Set<Path> pathsToImport;
        try {
            pathsToImport = getPathsToImport(importTask.getPath());
        } catch (IOException e) {
            return;
        }

        for (var path : pathsToImport) {
            String archiveType = null;

            if (path.toString().endsWith(".zip") || path.toString().endsWith(".cbz")) {
                archiveType = "zip";
            } else if (path.toString().endsWith(".7z") || path.toString().endsWith(".cb7")) {
                archiveType = "7z";
            } else {
                return;
            }

            boolean hasImage = false;

            try (var is = Files.newInputStream(path)) {
                var input = new ArchiveStreamFactory().createArchiveInputStream(archiveType, is);

                ArchiveEntry entry;
                while ((entry = input.getNextEntry()) != null) {
                    if (!input.canReadEntryData(entry)) {
                        continue;
                    }

                    if (entry.isDirectory()) {
                        continue;
                    }

                    String name = entry.getName();

                    if (!isValidImageFilename(name)) {
                        continue;
                    }

                    hasImage = true;
                    break;
                }


            } catch (IOException | ArchiveException e) {
                e.printStackTrace();
            }

            if (!hasImage) {
                return;
            }

            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                // Per Oracle docs SHA-256 should always be present, and we need this for hashing
                throw new RuntimeException(e);
            }

            byte[] buffer = new byte[CHUNK_SIZE];
            byte[] hash = null;

            try (var is = new BufferedInputStream(Files.newInputStream(path))) {
                int bytesRead;
                while ((bytesRead = is.read(buffer, 0, CHUNK_SIZE)) > 0) {
                    digest.update(buffer, 0, bytesRead);
                }

                hash = digest.digest();
            } catch (IOException e) {
                // Ignore?
                e.printStackTrace();
            }

            if (hash == null) {
                return;
            }

            try (var session = sessionFactory.openSession()) {
                var query = session.createQuery("select count(*) from Doujin where shaHash = :shaHash");
                query.setParameter("shaHash", hash);
                boolean exists = (long) query.uniqueResult() > 0;

                if (!exists) {
                    session.beginTransaction();

                    var uuid = (UUID) session.save(new Doujin(path.getFileName().toString(), path.toString(), hash));

                    session.getTransaction().commit();
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                var importTask = importTaskQueue.take();

                processImportTask(importTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
