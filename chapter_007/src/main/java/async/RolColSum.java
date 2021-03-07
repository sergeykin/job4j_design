package async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
            sums[i].setColSum(0);
            sums[i].setRowSum(0);
            for (int j = 0; j < matrix.length; j++) {
                sums[i].setColSum(sums[i].getColSum() + matrix[j][i]);
                sums[i].setRowSum(sums[i].getRowSum() + matrix[i][j]);
            }
        }

        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int i) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            sums.setColSum(0);
            sums.setRowSum(0);
            for (int j = 0; j < data.length; j++) {
                sums.setColSum(sums.getColSum() + data[j][i]);
                sums.setRowSum(sums.getRowSum() + data[i][j]);
            }
            return sums;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] mas = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
        for (int i = 0; i < mas.length; i++) {
            for (int j = 0; j < mas[i].length; j++) {
                System.out.print(mas[i][j]+" ");
            }
            System.out.println();
        }
        Sums[] sums = RolColSum.sum(mas);
        Sums[] asyncSum = RolColSum.asyncSum(mas);
        for (int i = 0; i < sums.length; i++) {
            System.out.println(i + " row:"+sums[i].getRowSum());
            System.out.println(i + " col:"+sums[i].getColSum());
        }
        for (int i = 0; i < asyncSum.length; i++) {
            System.out.println(i + " row:"+asyncSum[i].getRowSum());
            System.out.println(i + " col:"+asyncSum[i].getColSum());
        }
    }
}
