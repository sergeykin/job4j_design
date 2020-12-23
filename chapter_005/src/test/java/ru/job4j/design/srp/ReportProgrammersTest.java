package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportProgrammersTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report reportProgrammers = new ReportProgrammers(store);


        StringBuilder expect = new StringBuilder();
        expect.append("<table>").append(System.lineSeparator());
        expect.append("<tr>").append(System.lineSeparator())
                .append("<td>").append("Name").append("</td>")
                .append("<td>").append("Hired").append("</td>")
                .append("<td>").append("Fired").append("</td>")
                .append("<td>").append("Salary").append("</td>")
                .append("</tr>");
        expect.append("<tr>").append(System.lineSeparator())
                    .append("<td>").append(worker.getName()).append("</td>")
                    .append("<td>").append(worker.getHired()).append("</td>")
                    .append("<td>").append(worker.getFired()).append("</td>")
                    .append("<td>").append(worker.getSalary()).append("</td>")
                    .append("</tr>");
        expect.append("</table>");
        assertThat(reportProgrammers.generate(em -> true), is(expect.toString()));
    }
}