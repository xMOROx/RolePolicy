package pl.zajdel.patryk;

import java.io.File;
import java.util.Optional;

public class JsonFile {
    private final File file;

    private JsonFile(File file) {
        this.file = file;
    }

    public static Optional<JsonFile> fromPath(String path) throws IllegalArgumentException {
        if (!path.matches(".*\\.json$")) {
            throw new IllegalArgumentException("The file must have a .json extension");
        }


        File file = new File(path);
        if (file.exists()) {
            return Optional.of(new JsonFile(file));
        }
        return Optional.empty();
    }

    public File getFile() {
        return file;
    }

}
