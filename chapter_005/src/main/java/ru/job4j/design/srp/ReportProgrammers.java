package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportProgrammers implements Report {
   private final Store store;

    public ReportProgrammers(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> sortedList = store.findBy(filter);
        StringBuilder text = new StringBuilder();
        text.append("<table>").append(System.lineSeparator());
        text.append("<tr>").append(System.lineSeparator())
                .append("<td>").append("Name").append("</td>")
                .append("<td>").append("Hired").append("</td>")
                .append("<td>").append("Fired").append("</td>")
                .append("<td>").append("Salary").append("</td>")
                .append("</tr>");
        for (Employee employee : sortedList) {
            text.append("<tr>").append(System.lineSeparator())
                    .append("<td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td>")
                    .append("</tr>");
        }
        text.append("</table>");
        return text.toString();
    }

}
