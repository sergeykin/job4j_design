package ru.job4j.menu;

import java.util.Objects;

public class Paragraph {
    private String id;
    private String Name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paragraph paragraph = (Paragraph) o;
        return Objects.equals(id, paragraph.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Paragraph(String id, String name) {
        this.id = id;
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
