package ru.job4j.io;


import java.io.*;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        Map<String, String> map = new LinkedHashMap<>();
        boolean isNewRange = true;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String line;
            String tmpKey = "";
            while ((line = read.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] keyValue = line.split(" ");
                    if (keyValue.length == 2) {
                        if (keyValue[0].equals("200") || keyValue[0].equals("300")) {
                            isNewRange = true;
                        }
                        if (keyValue[0].equals("400") || keyValue[0].equals("500")) {
                            if (isNewRange) {
                                tmpKey = keyValue[1];
                                map.put(tmpKey, tmpKey);
                                isNewRange = false;
                            } else {
                                map.put(tmpKey, keyValue[1]);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {

            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                out.write(entry.getKey() + ";" + entry.getValue() + System.lineSeparator());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(map);
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
//        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
//            out.println("15:01:30;15:02:32");
//            out.println("15:10:30;23:12:32");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
