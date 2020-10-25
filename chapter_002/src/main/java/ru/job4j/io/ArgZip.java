package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArgZip {

    private final String[] args;
    private Map<String, String> params = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (argZip.valid()) {
            SearchFiles searchFiles = new SearchFiles(p -> !p.toFile().getName().endsWith(argZip.exclude()));
            Files.walkFileTree(Paths.get(argZip.directory()), searchFiles);
            new Zip().packFiles(searchFiles.list,
                    new File(argZip.output())
            );
        } else {
            throw new IllegalArgumentException("Root folder is null or Exclude is null or Output file is null.");
        }
    }

    public ArgZip(String[] args) {
        this.args = args;
        for (int i = 0; i < args.length; i++) {
            String[] entry = args[i].split("=");
            if (entry.length == 2) {
                params.put(entry[0], entry[1]);
            }
        }
    }

    public boolean valid() {
        return directory() != null && output() != null && exclude() != null;
    }

    public String directory() {
        return params.get("-d");
    }

    public String exclude() {
        return params.get("-e");
    }

    public String output() {
        return params.get("-o");
    }
}
