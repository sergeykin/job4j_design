package pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex extends RecursiveTask<Integer> {
    private final Object[] objects;
    private final int from;
    private final int to;
    private final int searchIndex;

    public ParallelSearchIndex(Object[] objects, int from, int to, int searchIndex) {
        this.objects = objects;
        this.from = from;
        this.to = to;
        this.searchIndex = searchIndex;
    }

    @Override
    protected Integer compute() {
        if ((to - from) < 10) {
            return linearSearch();
        }
        int mid = (from + to) / 2;
        // создаем задачи для сортировки частей
        ParallelSearchIndex leftSearch = new ParallelSearchIndex(objects, from, mid, searchIndex);
        ParallelSearchIndex rightSearch = new ParallelSearchIndex(objects, mid + 1, to, searchIndex);
        // производим деление.
        // оно будет происходить, пока в частях не останется по одному элементу
        leftSearch.fork();
        rightSearch.fork();
        // объединяем полученные результаты
        int left = leftSearch.join();
        int right = rightSearch.join();
        return left != -1? left: right;
    }

    private Integer linearSearch() {
        for (int i = from; i <= to ; i++) {
            if (i == searchIndex) {
                return searchIndex;
            }
        }
        return -1;
    }

    public static Integer search(Object[] array, int searchIndex) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex(array, 0, array.length - 1, searchIndex));
    }

    public static void main(String[] args) {
        Object[] objects = new Object[100];
        System.out.println(ParallelSearchIndex.search(objects,99));
    }
}
