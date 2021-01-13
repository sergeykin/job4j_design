package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Menu {
    boolean add(Paragraph parent, Paragraph child);
    boolean add(Paragraph child);
    void print();
    void select(Paragraph paragraph);
    Optional<Node> findBy(Paragraph value);

    class Node {
        public final Paragraph value;
        public final List<Node> children = new ArrayList<>();
        public Node(Paragraph value) {
            this.value = value;
        }
    }
}
