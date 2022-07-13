package algos;

public class Insertionsort {
    protected static void sort(int[] xs, int lo, int hi){
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
     * @param xs array to sort
     */
    public static void sort(int[] xs){
        sort(xs,0,xs.length-1);
    }
}
