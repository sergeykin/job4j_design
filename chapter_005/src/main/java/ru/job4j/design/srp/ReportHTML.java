package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportHTML implements Report{
    private final Store store;
    public static final String separator = System.lineSeparator();

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        text.append("<html>").append(separator);
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</html>");
        return text.toString();
    }
}
