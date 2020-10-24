package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DublFiles implements FileVisitor<Path> {

    Map<String, String> allFiles = new LinkedHashMap<>();
    Map<String, String> dubles = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        Path start = Paths.get(args[0]);

        DublFiles dublFiles = new DublFiles();
        Files.walkFileTree(start, dublFiles);
        System.out.println(dublFiles.getDubles());

    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String key = file.getFileName().toString() + "(" + attrs.size() + ")";
        if (allFiles.containsKey(key)) {
            dubles.put(file.toAbsolutePath().toString(), key);
            dubles.put(allFiles.get(key), key);
        } else {
            allFiles.put(key, file.toAbsolutePath().toString());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }

    public Map<String, String> getDubles() {
        return dubles;
    }
}
