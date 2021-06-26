import algos.*;
import structures.*;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        var r = new Random();
        var stdout = System.out;
        Function<Integer, int[]> gen = n ->
                IntStream.iterate(r.nextInt(20), k -> r.nextInt(20))
                        .limit(n)
                        .toArray();
        // sorting tests
        stdout.println(new Sorts(gen.apply(10)).quickSort());
        stdout.println(new Sorts(gen.apply(10)).mergeSort());
        stdout.println(new Sorts(gen.apply(10)).heapSort());

        Function<Integer, Integer[]> gen2 = n ->
                Stream.iterate(r.nextInt(20), k -> r.nextInt(20))
                        .limit(n)
                        .toArray(Integer[]::new);
        // data structure tests
        stdout.println(DynArray.of(gen2.apply(10)));
        stdout.println(DynArray.of(1,2,3,4,5).equals(DynArray.of(1,2,4,5)));
        stdout.println(LinkedList.of(1,2,3,4).equals(LinkedList.of(1,2,3,4)));
        stdout.println(LinkedList.of(1,2,3,4).equals(LinkedList.of(1,2,3)));
        int[] xs = {2,1,5,4,2,3,4,5,3,5,1,2,3,7,8,1,5,9};
        var pair = Misc.threePartition(xs,0,xs.length-1);
        stdout.println(Arrays.toString(xs));
        stdout.println(pair);
    }
}
