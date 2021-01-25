package concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {}
        );
        Thread second = new Thread(
                () -> {}
        );
        System.out.println(first.getState().toString().concat(" - ").concat(first.getName()));
        System.out.println(second.getState().toString().concat(" - ").concat(second.getName()));
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState().toString().concat(" - ").concat(first.getName()));
            System.out.println(second.getState().toString().concat(" - ").concat(second.getName()));
        }
        System.out.println(first.getState().toString().concat(" - ").concat(first.getName()));
        System.out.println(second.getState().toString().concat(" - ").concat(second.getName()));
        System.out.println("End work");
    }
}
