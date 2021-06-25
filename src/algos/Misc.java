package algos;

import java.util.Random;

public class Misc {
    /**
     * Checks whether an index is in bounds of an array
     * @param xs array
     * @param i index
     * @return whether the index is in bounds
     */
    public static boolean inBounds(int[] xs, int i){
        return i >= 0 && i < xs.length;
    }
    /**
     * swaps the values of two indices within the array
     * @param xs array
     * @param i first index
     * @param j second index
     */
    public static void swap(int[] xs, int i, int j) {
        //do nothing, but emit a warning
        if (!inBounds(xs,i) && !inBounds(xs,j)) {
            System.out.println("attempted to swap with index outside the bounds of the array");
            return;
        }
        int x = xs[i];
        xs[i] = xs[j];
        xs[j] = x;
    }
    /**
     * Simple Fischer-Yates shuffle, going from 0 -> n-2 instead of n-1 -> 1
     * @param xs arbitrary array of values
     */
    public static void shuffle(int[] xs){
        var rand = new Random(System.nanoTime());
        for (int i = 0; i < xs.length - 2; i++){
            int j = rand.nextInt(xs.length-i)+i;
            swap(xs,i,j);
        }
    }
}
