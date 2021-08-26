package algos;

public class Counting {
    /**
     * perform counting sort for an array of integers. which involves using the number of occurrences
     * of each number in the array to determine the index in which it appears.
     * @param xs array to be sorted
     * @return reference to array (now sorted)
     */
    public static int[] sort(int[] xs) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (var x : xs) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        int[] temp = new int[xs.length];
        int[] count = new int[max - min + 2];
        if (max - min > xs.length * 32) // arbitrary bound for warning
            System.out.println("maximum range of values far exceeds length of array, consider using an alternative sorting method");

        for (var x : xs)
            count[x - min + 1]++;
        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];
        for (var x : xs)
            temp[count[x - min]++] = x;
        System.arraycopy(temp, 0, xs, 0, xs.length);
        return xs;
    }
}
