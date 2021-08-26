package algos;

public class LSDRadix {

    private static int charAt(String str, int d) {
        if (d < 0) throw new IllegalArgumentException("negative digit not allowed");
        return d >= str.length() ? 0 : str.charAt(d);
    }

    // differs from charAt since leading 0s are needed as opposed to trailing 0s
    private static int digitAt(int i, int d, int maxLen) {
        if (i < 0) throw new IllegalArgumentException("negative integers not allowed");
        if (d < 0) throw new IllegalArgumentException("negative digit not allowed");
        int len = (int) Math.log10(i) + 1;
        return d < maxLen - len ? 0 : i / (int) Math.pow(10, maxLen - d - 1) % 10;
    }

    /**
     * Sorts an arbitrary array of strings using Least-Significant digit radix sort {@link #sort(String[], int)}.
     * implicitly pads strings to max length (k) with trailing 0s via a bound check in {@link #charAt(String, int)}
     *
     * @param xs array to sort
     * @return reference to array (now sorted)
     */
    public static String[] sort(String[] xs) {
        int maxlen = 0;
        for (var x : xs) {
            maxlen = Math.max(maxlen, x.length());
        }
        return sort(xs, maxlen);
    }

    /**
     * sorts an array with strings with length {@code maxLen} using least significant digit (LSD) radix sort,
     * giving guaranteed runtime of n * maxLen. Not really advised to use for smaller strings, since the 256 value
     * count array would be the bottleneck
     *
     * @param xs     array to sort
     * @param maxLen length of elements
     * @return reference to array (now sorted)
     */
    public static String[] sort(String[] xs, int maxLen) {
        String[] temp = new String[xs.length];
        // sort each digit starting from the right using counting sort
        for (int d = maxLen - 1; d >= 0; d--) {
            // using extended ascii alphabet (and one for the extra slot)
            int[] count = new int[257];
            // counting sort
            for (var x : xs)
                count[charAt(x, d) + 1]++;
            for (int i = 1; i < 256; i++)
                count[i] += count[i - 1];
            for (String x : xs)
                temp[count[charAt(x, d)]++] = x;
            System.arraycopy(temp, 0, xs, 0, xs.length);
        }
        return xs;
    }

    /**
     * Sorts an arbitrary array of unsigned integers using Least-Significant digit radix sort {@link #sort(int[], int)}.
     * implicitly pads strings to max length with leading 0s via a bound check in {@link #digitAt(int, int, int)}
     * since {@code digitAt} uses leading 0s as opposed to trailing 0s, an additional length argument is required
     *
     * @param xs array to sort
     * @return reference to array (now sorted)
     */
    public static int[] sort(int[] xs) {
        int maxlen = 0;
        for (var x : xs) {
            maxlen = Math.max(maxlen, (int) Math.log10(x) + 1);
        }
        return sort(xs, maxlen);
    }

    /**
     * sorts an array of unsigned integers with {@code maxLen} digits using least significant digit (LSD) radix sort,
     * giving a worst case of n * maxLen time (assuming constant time logarithm and power function). However since
     * java integers are bounded to 2^31-1, this is effectively a linear time guarantee. A better LSD sorting algorithm
     * would probably sort using base 2 digits, which leaves maxLen = 32 but removes the need to call log10 and pow.
     *
     * @param xs     array to sort
     * @param maxLen length of elements
     * @return reference to array (now sorted)
     */
    public static int[] sort(int[] xs, int maxLen) {
        int[] temp = new int[xs.length];
        // sort each digit starting from the right using counting sort
        for (int d = maxLen - 1; d >= 0; d--) {
            // using extended ascii alphabet (and one for the extra slot)
            int[] count = new int[11];
            // counting sort
            for (var x : xs)
                count[digitAt(x, d, maxLen) + 1]++;
            for (int i = 1; i < 10; i++)
                count[i] += count[i - 1];
            for (int x : xs)
                temp[count[digitAt(x, d, maxLen)]++] = x;
            System.arraycopy(temp, 0, xs, 0, xs.length);
        }
        return xs;
    }
}
