package algos;

import structures.Pair;
import structures.queues.ArrayStack;

import java.util.List;

public class TimSort {

    /**
     * get min run (threshold between mergesort and insertion sort)
     * 8 < min run < 256 (shouldn't be to large (insertion sort n^2) and also not too small (merging expensive))
     * 
     * @param len length of array
     * @return length of minimum run
     */
    private static int minRun(int len){
        int r = 0;
        while (len >= 64){
            r |= len & 1; //check for off bit (1 if any , 0 if none)
            len >>= 1; // divide by 2
        }
        return len + r;
    }

    public static void sort(int[] xs){
        if (xs.length <= 1) return;
        int mr = minRun(xs.length);
        // sort small runs using insertion sort
        for (int i = 0; i < xs.length; i += mr)
            Insertionsort.sort(xs, i, Math.min(i + mr, xs.length)-1);
        // merge small sorted arrays using mergesort merge
        for (int size = mr; size < xs.length; size *= 2)
            for (int l = 0; l < xs.length-size; l += 2*size) {
                int m = l + size - 1;
                int h = Math.min(l + 2 * size , xs.length) - 1;
                Mergesort.merge(xs, l, m, h);
            }
    }
}
