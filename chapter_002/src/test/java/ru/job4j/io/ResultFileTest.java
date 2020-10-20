package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ResultFileTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "..\\app.properties1";
        ResultFile resultFile = new ResultFile();
        resultFile.save(path);
        assertThat(
                "sergey",
                is("sergey")
        );
    }

}