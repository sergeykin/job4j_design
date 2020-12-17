package ru.job4j.collection;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Article {
    public static boolean generateBy(String origin, String line) {
        Set<String> setOrigin = new HashSet<>(List.of(origin.split("[,.!? ]\\s*")));
        Set<String> setLine = new HashSet<>(List.of(line.split("[,.!? ]\\s*")));
        return setOrigin.containsAll(setLine);
    }

    public static void main(String[] args) {
        System.out.println(generateBy("asdf.ssss asdf asdf ffff", "ffff asdf"));
    }

    @Benchmark
    public void init() {
        generateBy("asdf.ssss ffff ffff", "ffff asdf asdf");
    }
}
