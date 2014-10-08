package org.rwin.subset;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubsetSumTest extends SubsetSum {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    int sum(Collection<Integer> e) {
        int sum = 0;
        for (int i : e) {
            sum += i;
        }
        return sum;
    }

    SubsetSumStrategy strategies[] = new SubsetSumStrategy[] { //
    new MultiSubsetSumStrategy2a(), // Alg 2a  well, at least better than 1b (if you have memory to spare)
            new MultiSubsetSumStrategy2b(), // Alg 2b (Surprisingly fast!, if you have memory to spare)
            new RecursiveSubsetSumStrategy(), // Alg 1b (Bad idea!)
            new NaiveSubsetSumStrategy() // Alg 1a (Branch & Search)

    };

    @Test
    public void negativeTest() {

        System.out.println("================ Negative test ==============");
        Random rnd = new Random();

        // Keep in mind, these algorithms are 2^n so don't pick a big i!
        // This is always WORST CASE!
        for (int i = 2; i < 30; i++) {
            int a[] = new int[i];
            int total = 1;
            for (int j = 0; j < i; j++) {
                a[j] = rnd.nextInt(100000); // We don't want any overflow weirdness.
                total += a[j];
            }

            // Obviously a subset sum  doesn't exist, as total = sum of the set + 1
            for (SubsetSumStrategy s : strategies) {
                Collection<Integer> res = this.findSubset(a, total, s);
                assertEquals(0, sum(res));
            }
        }
    }

    @Test
    public void positiveTest() {
        System.out.println("================ Positive test ==============");
        Random rnd = new Random();

        // Keep in mind, these algorithms are 2^n so don't pick a big i!
        for (int i = 2; i < 30; i++) {
            int a[] = new int[i];
            for (int j = 0; j < i; j++) {
                a[j] = rnd.nextInt(100000); // We don't want any overflow weirdness.
            }

            // Select a random set of elements we will add up.
            long bitmask = rnd.nextInt((int) Math.pow(2, i));
            int total = 0;
            for (int j = 0; j < i; j++) {
                if ((bitmask & 1 << j) != 0) {
                    total += a[j];
                }
            }

            // Obviously a subset sum exist.. Let see the strategies in action..
            for (SubsetSumStrategy s : strategies) {
                Collection<Integer> res = this.findSubset(a, total, s);
                assertEquals(total, sum(res));
            }
        }
    }

    @Test
    public void censusTest() {
        System.out.println("================ Census test ==============");
        int census[] = new int[] { 18897109, 12828837, 9461105, 6371773, 5965343, 5946800, 5582170, 5564635, 5268860, 4552402, 4335391,
                4296250, 4224851, 4192887, 3439809, 3279833, 3095313, 2812896, 2783243, 2710489, 2543482, 2356285, 2226009, 2149127,
                2142508, 2134411 };
        int total = 100000000;
        Collection<Integer> res = null;
        // Obviously a subset sum exist.. Let see the strategies in action..
        for (SubsetSumStrategy s : strategies) {
            res = this.findSubset(census, total, s);
            assertEquals(total, sum(res));
        }

        System.out.println("================ Census test, sorted heuristics might help certain algs. ==============");
        Arrays.sort(census);
        for (SubsetSumStrategy s : strategies) {
            res = this.findSubset(census, total, s);
            assertEquals(total, sum(res));
        }
        
        System.out.println("Solution: " + Arrays.toString(res.toArray()));
    }
}
