package org.rwin.subset;

/**
 * This is pretty much the first solution that might spring to  mind. It is clearly very slow.
 *
 * Basically it calculates the powerset of e, and then the summation of every set in the powerset until
 * we have found the set that sums up to the number we are looking for.
 * 
 * There is a slight optimization in the sense the we will stop calculating the sum.
 * 
 * O(n*2^n), space O(lg n).. 
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
