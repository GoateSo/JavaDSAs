package algos;

public class Quicksort {
    //hoare partition
    @SuppressWarnings("StatementWithEmptyBody")
    private static int partition(int[] xs, int lo, int hi) {
        int i = lo, j = hi + 1;
        var pivot = xs[lo];
        while (true) {
            while (xs[++i] < pivot && i < hi) {}
            while (xs[--j] > pivot && j > lo) {}
            if (i >= j) break;
            Misc.swap(xs, i, j);
        }
        Misc.swap(xs, lo, j);
        return j;
    }

    private static void quickSort(int[] xs, int lo, int hi) {
        if (lo >= hi) return; //singleton / empty (already sorted)
        int m = partition(xs, lo, hi);
        quickSort(xs, lo, m - 1); //sort left subarray
        quickSort(xs, m + 1, hi); //sort right subarray
    }

    /**
     * basic quicksort implementation which does random shuffle on array before sorting
     * possible optimizations would be to use a median based pivot as opposed to first element
     * as well as to use insertion sort for small arrays
     *
     * @param xs array to sort
     */
    public void sort(int[] xs) {
        Misc.shuffle(xs);
        quickSort(xs, 0, xs.length - 1);
    }
}
