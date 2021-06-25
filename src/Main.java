import algos.Sorts;
import structures.lists.*;
import structures.pqs.MaxHeap;

import java.util.Random;

public class Main {
    public static void main(String[] args){
        var stdout = System.out;
        stdout.println(new Sorts(new int[]{1,5,3,5,2,3,5,6,4,4,7}).heapSort());
        new DynArray<>("lig ","ma ","balls").forEach(stdout::print);
        stdout.println();
        new LinkedList<>(1,2,3,4,5,6).forEach(stdout::print);
    }
}
