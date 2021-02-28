package tracker;

//1.1. Реализация с применением enum, аналогична однопоточной реализации.
//public enum TrackerSingle {
//    INSTANCE;
//
//    public Item add(Item model) {
//        return model;
//    }
//
//    public static void main(String[] args) {
//        TrackerSingle tracker = TrackerSingle.INSTANCE;
//    }
//}

//1.2. Реализация с применением поля final.
//public class TrackerSingle {
//
//    private static final TrackerSingle INSTANCE = new TrackerSingle();
//
//    private TrackerSingle() {
//    }
//
//    public static TrackerSingle getInstance() {
//        return INSTANCE;
//    }
//
//    public Item add(Item model) {
//        return model;
//    }
//
//    public static void main(String[] args) {
//        TrackerSingle tracker = TrackerSingle.getInstance();
//    }
//}

//2.2. Double checked locking.
//public class TrackerSingle {
//
//    private static volatile TrackerSingle INSTANCE;
//
//    private TrackerSingle() {
//    }
//
//    public static TrackerSingle getInstance() {
//        if (INSTANCE == null) {
//            synchronized (TrackerSingle.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new TrackerSingle();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    public Item add(Item model) {
//        return model;
//    }
//
//    public static void main(String[] args) {
//        TrackerSingle tracker = TrackerSingle.getInstance();
//    }
//}

//2.3. Holder.
public class TrackerSingle {
    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerSingle INSTANCE = new TrackerSingle();
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}