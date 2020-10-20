package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ResultFile {
//    public static void main(String[] args) {
//        try (FileOutputStream out = new FileOutputStream("result.txt")) {
//            for (int i = 1; i < 10; i++) {
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int j = 1; j < 10; j++) {
//                    stringBuilder.append(String.format("%4d", i * j));
//                }
//                stringBuilder.append("\n");
//                out.write(stringBuilder.toString().getBytes());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(
            new BufferedOutputStream(
                    new FileOutputStream("result.txt")
            ))) {
        out.write("Hello, world!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void save(String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.write("Hello new File" + System.lineSeparator());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
