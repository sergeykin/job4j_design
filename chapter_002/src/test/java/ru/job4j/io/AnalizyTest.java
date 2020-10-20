package ru.job4j.io;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void whenPairWithoutComment() {
        String pathSource = "..\\server.log";
        String pathTarget = "..\\unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(pathSource, pathTarget);
        String line = "";
        try (BufferedReader read = new BufferedReader(new FileReader(pathTarget))) {
            line = read.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(
                line,
                is("10:57:01;10:58:01")
        );
    }
}