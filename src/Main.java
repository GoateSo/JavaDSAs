import algos.*;
import algos.Misc.*;
import structures.lists.LinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Misc.println(Arrays.toString(LSDRadix.sort(new String[]{"a","a","b","ddd","ab","abcbc",""})));
//        Misc.println(Arrays.toString(LSDRadix.sort(new int[]{1,5,10,800,9999,20,0,1001})));
        Misc.println(Arrays.toString(Counting.sort(new int[]{7,5,2,6,9,1})));
    }
}
