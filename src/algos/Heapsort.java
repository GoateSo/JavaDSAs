package algos;

public class Heapsort {
    // subtractions by 1 done to i and j  to correct 1 indexed indices to 0 indexed
    private static void swap(int[] xs, int i, int j) {
        int temp = xs[i - 1];
        xs[i - 1] = xs[j - 1];
        xs[j - 1] = temp;
    }

    // subtractions by 1 done to i and j to correct 1 indexed indices to 0 indexed
    private static void sink(int[] xs, int i, int size) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && xs[j - 1] < xs[j]) j++; //max of children
            if (xs[j - 1] < xs[i - 1]) break; //operation done, heap property satisfied
            //swap to heapify
            swap(xs, i, j);
            i = j; //next layer
        }
    }

    /**
     * heapsort implementation using implicit array implemented binary heap. arithmetic is done on base 1 indexed
     * i and j indices, although the array itself is 0 indexed. the conversion is handled by heapify (sink) and
     * swap methods, so as to make the code itself look somewhat cleaner.
     *
     * @param xs array to sort
     */
    public static void sort(int[] xs) {
        // build heap
        for (int i = xs.length / 2; i >= 1; i--) {
            sink(xs, i, xs.length);
        }
        // sort by repeated delete max
        for (int i = xs.length; i >= 1; i--) {
            swap(xs, 1, i);
            sink(xs, 1, i - 1);
        }
    }
}
