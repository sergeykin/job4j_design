package ru.job4j.design.srp;

public interface OutFormat {
    String convertToXML(String convertStr);

    String convertToJson(String convertStr);

    String convertToHTML(String convertStr);
}
