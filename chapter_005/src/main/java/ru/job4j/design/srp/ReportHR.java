package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        sortedList.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        text.append("Name; Salary;");
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(getSalary(employee)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private Double getSalary(Employee employee) {
        return employee.getSalary();
    }
}
