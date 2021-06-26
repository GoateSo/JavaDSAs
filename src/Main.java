import algos.Sorts;
import structures.lists.DynArray;
import structures.lists.LinkedList;

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
        stdout.println(LinkedList.of(2, 3, 4, 5, 6).get(0));
    }
}
