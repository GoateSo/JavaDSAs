import algos.*;
import structures.lists.DynArray;
import structures.lists.IList;
import structures.lists.LinkedList;
import structures.trees.RedBlackTree;

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
        /*stdout.println(new Sorts(gen.apply(10)).quickSort());
        stdout.println(new Sorts(gen.apply(10)).mergeSort());
        stdout.println(new Sorts(gen.apply(10)).heapSort());*/

        Function<Integer, Integer[]> gen2 = n ->
                Stream.iterate(r.nextInt(20), k -> r.nextInt(20))
                        .limit(n)
                        .toArray(Integer[]::new);
        // data structure tests
        RedBlackTree<Integer, String> bst = new RedBlackTree<>();
        for (int i = 1; i < 21; i++){
            bst.insert(i, Integer.toString(i));
        }
        for (int i = 1; i < 21; i++){
            stdout.println(bst.contains(i) + " " + i);
        }
        bst.bfs();
        bst.goRight();
    }
}
