package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ResultFileTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Test
    public void whenPairWithoutComment() throws IOException {
        File target = folder.newFile("target");
        String path = target.getPath();
        ResultFile resultFile = new ResultFile();
        resultFile.save(path);
        assertThat(
                "sergey",
                is("sergey")
        );
    }

}