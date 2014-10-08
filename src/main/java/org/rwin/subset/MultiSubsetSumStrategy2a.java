package org.rwin.subset;

/**
 * Algorithm 2a, O(2^n) space & time. (Alg. 2a)
 * 
 * This algorithm requires a huge amount of heap space, so make sure you have a
 * large heap available!
 * 
 * This algorithm performs terrible, but it is better then the naive approach.
 * 
 * 
 * @author erwinj
 *
 */
public class MultiSubsetSumStrategy2a implements SubsetSumStrategy {

    @Override
    public long findSubsetSum(int[] e, int total) {

        // OUCH!
        int memoryNeeded = (int) Math.pow(2, e.length);
        int sum[] = new int[memoryNeeded];

        int ic = 1;
        sum[0] = 0;

        // The basic idea is to generate tuples:
        // (a, b) where a contains the sum and b the encoding of which elements where used to get to this sum.
        // the binary representation of b indicates which elements where used.
        // the good news is that b corresponds to the index of the array so that's good news.
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < ic; j++) {
                int sm = sum[j] + e[i];
                if (sm == total)
                    return j + ic;

                sum[ic + j] = sm;
            }
            ic += ic;
        }
        return 0;
    }

}
