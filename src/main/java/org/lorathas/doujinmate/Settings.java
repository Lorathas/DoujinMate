package org.lorathas.doujinmate;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Settings {
    private final Path appDataPath;
    private final Path thumbnailPath;

    @Inject
    public Settings() {
        appDataPath = Paths.get(System.getenv("APPDATA"), "DoujinMate");
        thumbnailPath = Paths.get(appDataPath.toString(), "thumbs");
    }

    public void initializeEnvironment() {
        if (!Files.isDirectory(appDataPath)) {
            try {
                Files.createDirectory(appDataPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!Files.isDirectory(thumbnailPath)) {
            try {
                Files.createDirectory(thumbnailPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.setProperty("derby.system.home", appDataPath.toString());
    }

    public Path getAppDataPath() {
        return appDataPath;
    }

    public Path getThumbnailPath() {
        return thumbnailPath;
    }
}
