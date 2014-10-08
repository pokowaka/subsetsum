package org.rwin.subset;

/**
 * O(n*2^n), space O(lg n) 
 * @author erwinj
 *
 */
public class NaiveSubsetSumStrategy implements SubsetSumStrategy {

    @Override
    public long findSubsetSum(int[] e, int total) {
        long possible = (long) Math.pow(2, e.length);

        // We create every possible subset, if a bit is set we include that element,
        // otherwise we don't.
        for (long delta = 0; delta < possible; delta++) {
            int count = 0;
            for (int i = 0; i < e.length; i++) {
                if ((delta & (1 << i)) != 0) {
                    count += e[i];
                }
                if (count == total) {
                    return delta;
                }
                if (count > total)
                    break;
            }
        }
        return 0;
    }

}
