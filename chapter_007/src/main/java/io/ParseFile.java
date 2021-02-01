package io;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized  String getContent() throws IOException {
        StringBuilder output = new StringBuilder("");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                output.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        StringBuilder output = new StringBuilder("");
        try (InputStream i = new FileInputStream(file)){
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ParseFile parseFile = new ParseFile();
        parseFile.setFile(new File("input.txt"));
        System.out.println(parseFile.getContent());
        //parseFile.saveContent("ddddddddddddeeeeeeeeeeee");
        System.out.println(parseFile.getContent());
        System.out.println(parseFile.getContentWithoutUnicode());
    }
}
