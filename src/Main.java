import algos.*;
import algos.Misc.*;
import org.jetbrains.annotations.NotNull;
import structures.lists.LinkedList;
import static java.time.temporal.ChronoUnit.YEARS;

import java.lang.reflect.Array;
import java.time.LocalDate;

import java.util.Comparator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static final Random gen = new Random();
    private static int[] mkarr(int len) {
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            ret[i] = gen.nextInt(len/2+1)-len/2;
        }
        return ret;
    }
    public static int[] shuffle(int[] array) {
        return shuffle(array, gen);
    }
    public static int[] shuffle (int[] array, Random gen) {
        int n = array.length;
        while (n > 1) {
            int k = gen.nextInt(n--); //decrements after using the value
            int temp = array[n];
            array[n] = array[k];
            array[k] = temp;
        }
        return array;
    }
    private static boolean isSorted(int[] arr){
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i+1] < arr[i]) {
                System.out.println(i);
                return false;
            }
        return true;
    }
    public static void main(String[] args) {
//        var arr = shuffle(mkarr(10));
//        System.out.println(Arrays.toString(arr));
//        TimSort.sort(arr);
//        System.out.println(Arrays.toString(arr));
        Random gen = new Random(10), gen2 = new Random(10);
        int cases = 1000;
        int len = 2000;
        int[][] xss = new int[cases][];

        for (int i = 0; i < cases; i++){
            xss[i] = mkarr(gen.nextInt(len));
        }
        int[][] yss = new int[cases][];
        for (int i = 0; i < cases; i++){
            yss[i] = Arrays.copyOf(xss[i],xss[i].length);
        }

        System.out.println("run");
        long time = System.nanoTime();
        for (int i = 0; i < cases; i++)
            Mergesort.sort(xss[i]);
        long ntime = System.nanoTime();
        System.out.printf("mergesort\t: %d\n",ntime-time);
        time = System.nanoTime();
        for (int i = 0; i < cases; i++)
            TimSort.sort(yss[i]);
        ntime = System.nanoTime();
        System.out.printf("timsort\t\t: %d\n", ntime-time);
        for (int[] xs : xss)
            if (!isSorted(xs)) {
                System.out.println("bad mergesort");
                break;
            }
        for (int[] ys : yss)
            if (!isSorted(ys)) {
                System.out.println("bad timsort");
                break;
            }

    }
}
