package org.rwin.subset;

/**
 * Algorithm 2a, O(2^n) space & time. (Alg. 2a in paper)
 * 
 * This algorithm requires a huge amount of heap space, so make sure you have a
 * large heap available!
 * 
 * This algorithm performs terrible, but it is better then the naive approach.
 * The basic idea is that we are going to create a set with all the sums of the
 * individual elements.
 * 
 * This is done using the following formula:
 * 
 * A is a multiset of (a1, b1) where a1 is the partial sum and b1 is an encoding
 * of j such that sum_(j|j-1 \in b1) = a1
 * 
 * Now for b1 we simply use the binary representation so for example b1 = 2 = 10
 * we will include element 1 and not 0.
 * 
 * We use the algorithm:
 * 
 * A = (0, 0) i <-- 1,..., r A union { A + (s_i, IC) ic <-- ic + 1
 * 
 * Now the smart observer will notice that we don't need to keep track of b1,
 * since b1 == index in A so we can get rid of keeping track of that.
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

        // See paper for details, or description above.
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < ic; j++) {
                sum[ic + j] = sum[j] + e[i];
                if (sum[ic + j] == total)
                    return j + ic;
            }
            ic += ic;
        }
        return 0;
    }
}
