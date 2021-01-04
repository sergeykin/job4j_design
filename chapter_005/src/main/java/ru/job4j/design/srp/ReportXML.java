package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report{
    private final Store store;
    public static final String separator = System.lineSeparator();

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        text.append("<xml>").append(separator);
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</xml>");
        return text.toString();
    }
}

