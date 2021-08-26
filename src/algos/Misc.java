package algos;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Misc {
    /**
     * Checks whether an index is in bounds of an array
     *
     * @param xs array
     * @param i  index
     * @return whether the index is in bounds
     */
    public static boolean inBounds(int[] xs, int i) {
        return i >= 0 && i < xs.length;
    }

    /**
     * swaps the values of two indices within the array
     *
     * @param xs array
     * @param i  first index
     * @param j  second index
     */
    public static void swap(int[] xs, int i, int j) {
        //do nothing, but emit a warning
        if (!(inBounds(xs, i) || inBounds(xs, j))) {
            System.out.println("attempted to swap with index outside the bounds of the array");
            return;
        }
        int x = xs[i];
        xs[i] = xs[j];
        xs[j] = x;
    }

    /**
     * Simple Fischer-Yates shuffle, going from 0 -> n-2 instead of n-1 -> 1
     *
     * @param xs arbitrary array of values
     */
    public static void shuffle(int[] xs) {
        var rand = new Random();
        for (int i = 0; i < xs.length - 2; i++) {
            int j = rand.nextInt(xs.length - i) + i;
            swap(xs, i, j);
        }
    }

    /**
     * Partitions an array of integers in accordance with Dijkstra's method
     * in the dutch national flag problem.
     *
     * @param xs array to partition
     * @param lo start of subarray
     * @param hi end of subarray
     * @return the start and end of the subarray of values equal to the partitioning element
     */
    public static Pair threePartition(int[] xs, int lo, int hi) {
        if (hi - lo < 2) return new Pair(lo, hi);
        int lt = lo, i = lo;
        int gt = hi;
        int pivot = xs[0];
        while (i <= gt) {
            int x = xs[i];
            if (x < pivot) {
                swap(xs, i, lt);
                lt++;
                i++;
            } else if (x > pivot) {
                swap(xs, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        return new Pair(lt, gt);
    }

    /**
     * prints a variable amount of objects
     *
     * @param xs objects to print out
     */
    public static void println(Object... xs) {
        System.out.println(Arrays.stream(xs).map(Object::toString).collect(Collectors.joining(" ")));
    }

    private static record Pair(int fst, int snd) {
    }
}
