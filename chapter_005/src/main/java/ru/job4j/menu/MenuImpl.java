package ru.job4j.menu;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class MenuImpl implements Menu {
    private final Node root;

    MenuImpl() {
        this.root = new Node(new Paragraph("",""));
    }

    @Override
    public boolean add(Paragraph parent, Paragraph child) {
        boolean rsl = false;
        Optional<Node> nodeParent = findBy(parent);
        if (!nodeParent.isEmpty()) {
            if (findBy(child).isEmpty()) {
                nodeParent.get().children.add(new Node(child));
                rsl = true;
            }
        }
        return rsl;
    }

    public boolean add(Paragraph child) {
        boolean rsl = false;
        if (findBy(child).isEmpty()) {
                root.children.add(new Node(child));
                rsl = true;
            }
        return rsl;
    }

    @Override
    public Optional<Node> findBy(Paragraph value) {
        Optional<Node> rsl;
        rsl = findByPredicate(el -> el.value.equals(value));
        return rsl;
    }

    private Optional<Node> findByPredicate(Predicate<Node> predicate) {
        Optional<Node> rsl = Optional.empty();
        Queue<Node> data = new LinkedList<>();
        data.offer(this.root);

        while (!data.isEmpty()) {
            Node el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public void print() {
        print(root,0);
    }

    private void print(Node node, int level) {
        if (level != 0) {
            System.out.println("--".repeat(level).concat(node.value.getName()));
        }
        for (Node nodeit:node.children) {
            print(nodeit,level+1);
        }
    }

    @Override
    public void select(Paragraph paragraph) {
        System.out.println("Select:".concat(paragraph.getName()));
    }

    public static void main(String[] args) {
        MenuImpl menu = new MenuImpl();
        Paragraph paragraph1 = new Paragraph("1", "p1");
        Paragraph paragraph2 = new Paragraph("2", "p2");
        Paragraph paragraph3 = new Paragraph("3", "p3");
        menu.add(paragraph1);
        menu.add(paragraph2);
        menu.add(paragraph1,new Paragraph("4", "p4"));
        menu.add(paragraph1,paragraph3);
        menu.add(paragraph3,new Paragraph("5", "p5"));
        menu.print();
        menu.select(paragraph2);
    }
}
