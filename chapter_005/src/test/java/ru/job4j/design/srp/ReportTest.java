package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportTest {
    private final String separator = System.lineSeparator();

    @Test
    public void whenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportHTML reportHTML = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(separator)
                .append("Name; Hired; Fired; Salary;")
                .append(separator)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(separator).append("</html>");
        assertThat((reportHTML.generate(em -> true)), is(expect.toString()));
    }

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportXML reportXML = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<xml>").append(separator)
                .append("Name; Hired; Fired; Salary;")
                .append(separator)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(separator).append("</xml>");
        assertThat(reportXML.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportJSON reportJSON = new ReportJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("{json}").append(separator)
                .append("Name; Hired; Fired; Salary;")
                .append(separator)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(separator).append("{json}");
        assertThat(reportJSON.generate(em -> true), is(expect.toString()));
    }
}
