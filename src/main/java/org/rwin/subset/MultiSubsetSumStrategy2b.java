package org.rwin.subset;

/**
 * Time complexity O(2^n-1) Space complexity O(2^n)
 * 
 * This is a slight optimization of alg 2a. We divide the sets in two.
 * 
 * This algorithm performs surprisingly well. If you have memory to spare it
 * outperforms the recursive strategy.
 * 
 * There are no heuristics right now that could make it go faster.
 * 
 * @author erwinj
 *
 */
public class MultiSubsetSumStrategy2b implements SubsetSumStrategy {

    private void constructSets(int[] e, int[] sum, int r, int offset) {
        int ic = 1;
        sum[0] = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < ic; j++) {
                sum[j + ic] = sum[j] + e[i + offset];
            }
            ic += ic;
        }
    }

    @Override
    public long findSubsetSum(int[] e, int total) {
        // "Split the array in half"
        int r = (int) Math.floor(e.length / 2);

        // OUCH!
        int memoryNeeded = (int) Math.pow(2, r);
        int sum[] = new int[memoryNeeded];

        memoryNeeded = (int) Math.pow(2, e.length - r);
        int sum2[] = new int[memoryNeeded];

        constructSets(e, sum, r, 0);
        constructSets(e, sum2, e.length - r, r);

        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum2.length; j++) {
                if (sum[i] + sum2[j] == total) {
                    // Found it!
                    // bitmask lower array combination[i];
                    // bitmask higher array.
                    return i | j << r;
                }
            }
        }

        return 0;
    }

}
