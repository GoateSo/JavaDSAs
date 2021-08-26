package algos;

public class Mergesort {
    // merge subroutine, which merges two sorted subsections
    private static void merge(int[] xs, int lo, int mid, int hi) {
        //[1,2,3,4,1,3,4,5]
        // l     m       h
        // length(left) = m-l+1
        // length(right = h-(m+1)+1 = h-m
        int ll = mid - lo + 1, lr = hi - mid;
        int[] left = new int[ll], right = new int[lr];
        System.arraycopy(xs, lo, left, 0, ll);
        System.arraycopy(xs, mid + 1, right, 0, lr);

        int i = 0, j = 0;
        for (int k = lo; k <= hi; k++) {
            if (i >= ll)  //left out of bounds
                xs[k] = right[j++];
            else if (j >= lr)  //right out of bounds
                xs[k] = left[i++];
            else if (left[i] < right[j]) //left smaller
                xs[k] = left[i++];
            else //right smaller
                xs[k] = right[j++];
        }
    }

    // private method for recursion: hides lo and hi parameters
    private static void mergesort(int[] xs, int lo, int hi) {
        if (lo >= hi) return; //array of size 0 or 1, already sorted
        int m = lo + (hi - lo) / 2; //uses subtraction as opposed to addition for average to avoid integer overflow
        mergesort(xs, lo, m);//sort left subarray
        mergesort(xs, m + 1, hi);//sort right subarray
        merge(xs, lo, m, hi);
    }

    /**
     * rather naive mergesort which creates 2 sub-arrays upon each merge operation
     * possible optimizations bei   ng to use insertion sort on small sub-arrays
     * and to use an auxiliary array of size N
     *
     * @param xs array to sort
     */
    public static void sort(int[] xs) {
        mergesort(xs, 0, xs.length - 1);
    }
}
