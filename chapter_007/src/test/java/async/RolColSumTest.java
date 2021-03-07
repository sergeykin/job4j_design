package async;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void sum() {
        int[][] mas = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
        RolColSum.Sums[] sums = RolColSum.sum(mas);
        assertThat(sums[0].getRowSum(), is(6));
        assertThat(sums[0].getColSum(), is(12));
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] mas = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
        RolColSum.Sums[] asyncSum = RolColSum.asyncSum(mas);
        assertThat(asyncSum[0].getRowSum(), is(6));
        assertThat(asyncSum[0].getColSum(), is(12));

    }
}