package ru.job4j.design.srp;


import java.util.List;
import java.util.function.Predicate;


public class ReportBookkeeping implements Report {

    private final Store store;

    public ReportBookkeeping(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(getSalary(employee)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private Double getSalary(Employee employee) {
        return employee.getSalary() * 1000;
    }
}
