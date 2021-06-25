package algos;

import structures.pqs.MaxHeap;

import java.util.Arrays;

public class Sorts {
    int[] xs;
    private static final int CUTOFF = 10;

    @Override
    public String toString() {
        return Arrays.toString(xs);
    }

    /**
     * creates instance of Sorts class to apply algo.s on
     * @param arr array to sort
     */
    public Sorts(int[] arr) {
        xs = new int[arr.length];
        System.arraycopy(arr, 0, xs, 0, arr.length);
    }

    private void insertionSort(int lo, int hi){
        for (int i = lo + 1; i <= hi; i++){
            int ind = i ;
            int x = xs[i];
            while (ind > lo && x < xs[ind-1]){
                Misc.swap(xs,ind,ind-1);
                ind--;
            }
        }
    }

    /**
     * Standard implementation of insertion sort which compares with previous element and swaps
     * possible optimizations are to binary search for position due to loop invariant,
     * thus reducing # comparisons
     * @return reference to self
     */
    public Sorts insertionSort(){
        insertionSort(0,xs.length-1);
        return this;
    }
    private void merge(int lo, int mid,  int hi){
        //[1,2,3,4,1,3,4,5]
        // l     m       h
        // length(left) = m-l+1
        // length(right = h-(m+1)+1 = h-m
        int ll = mid-lo+1, lr = hi-mid;
        int[] left = new int[ll], right = new int[lr];
        System.arraycopy(xs,lo,left,0,ll);
        System.arraycopy(xs,mid+1,right,0,lr);

        int i = 0, j = 0;
        for (int k = lo; k <= hi; k++){
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
    private void mergeSort(int lo, int hi){
        if (lo >= hi) return; //array of size 0 or 1, already sorted
        int m = lo + (hi-lo)/2; //uses subtraction as opposed to addition for average to avoid integer overflow
        mergeSort(lo,m);//sort left subarray
        mergeSort(m+1,hi);//sort right subarray
        merge(lo,m,hi);
    }

    /**
     * rather naive mergesort which creates 2 sub-arrays upon each merge operation
     * possible optimizations being to use insertion sort on small sub-arrays
     * and to use an auxiliary array of size N
     * @return reference to self
     */
    public Sorts mergeSort(){
        mergeSort(0,xs.length-1);
        return this;
    }
    //hoare partition
    @SuppressWarnings("StatementWithEmptyBody")
    public int partition(int lo, int hi){
        int i = lo, j = hi+1;
        var pivot = xs[lo];
        while (true){
            while (xs[++i] < pivot && i < hi) {}
            while (xs[--j] > pivot && j > lo) {}
            if (i>=j) break;
            Misc.swap(xs,i,j);
        }
        Misc.swap(xs,lo,j);
        return j;
    }
    private void quickSort(int lo, int hi){
        if (lo >= hi) return; //singleton / empty (already sorted)
        int m = partition(lo,hi);
        quickSort(lo,m-1); //sort left subarray
        quickSort(m+1,hi); //sort right subarray
    }
    /**
     * basic quicksort implementation which does random shuffle on array before sorting
     * possible optimizations would be to use a median based pivot as opposed to first element
     * as well as to use insertion sort for small arrays
     * @return reference to self
     */
    public Sorts quickSort(){
        Misc.shuffle(xs);
        quickSort(0,xs.length-1);
        return this;
    }
    /**
     * rather naive heapsort which creates an auxiliary heap and array (jvm boxing) in order to sort the array.
     * Access to the private fields of the maxheap itself would allow for faster runtime and less memory used on
     * auxiliaries, although doing so in MaxHeap/DynArray would be unsafe and rigid, disallowing shrinkage
     * and showing implementation detail.
     * better results can be achieved by creating a heap implementation within this class.
     * @return reference to self
     */
    public Sorts heapSort(){
        Integer[] arr = new Integer[xs.length];
        for (int i = 0; i < arr.length; i++){
            arr[i] = xs[i];
        }
        var heap = new MaxHeap<>(arr);
        for (int i = arr.length-1; i >= 0; i--){
            xs[i] = heap.delmax();
        }
        return this;
    }
}
