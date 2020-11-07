package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenPairWithoutComment() throws IOException {
        File source = folder.newFile("source");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("#123123");
            out.println("!asdfasdf = asdfasdf");
            out.println("name = sergey");
            out.println("path = c:\\\\123");
        }

        String path = source.getPath();
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("sergey")
        );
    }
}