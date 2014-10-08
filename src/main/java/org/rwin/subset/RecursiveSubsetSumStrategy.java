package org.rwin.subset;

/**
 * Time: O(2^n) Space: O(n)
 * 
 * @author erwinj
 *
 */
public class RecursiveSubsetSumStrategy implements SubsetSumStrategy {

    private static long findSubsetStandard(int e[], int len, int total, long selected) {
        if (total == 0)
            return selected;

        if (len == 0 || total < 0)
            return 0;

        // Result of non zero indicates a subset that adds up to total.
        long res = findSubsetStandard(e, len - 1, total - e[len - 1], selected | (1 << (len - 1)));
        if (res > 0)
            return res;

        return findSubsetStandard(e, len - 1, total, selected);
    }

    @Override
    public long findSubsetSum(int[] e, int total) {
        return findSubsetStandard(e, e.length, total, 0);
    }

}
