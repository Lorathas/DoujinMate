package org.lorathas.doujinmate.task;

import java.nio.file.Path;

public class ImportTask {
    private final Path path;

    public ImportTask(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
