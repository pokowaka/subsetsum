package org.rwin.subset;

import java.util.ArrayList;
import java.util.Collection;

public class SubsetSum {

    Collection<Integer> toSubset(long sel, int[] set) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < set.length; i++) {
            if ((sel & (1 << i)) != 0) {
                res.add(set[i]);
            }
        }
        return res;
    }

    public Collection<Integer> findSubset(int[] e, int total, SubsetSumStrategy strategy) {
        long ts = System.currentTimeMillis();
        long sol = strategy.findSubsetSum(e, total);
        ts = System.currentTimeMillis() - ts;
        System.out.format("Class: %30s, size: %4d, found: %6b, ms: %d %n", strategy.getClass().getSimpleName(), e.length, (sol != 0), ts);
        return toSubset(sol, e);
    }

}
