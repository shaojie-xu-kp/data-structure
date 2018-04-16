package sort.java.divideConquer;

import java.util.Arrays;

/**
 * Created by shaojiexu on 2/18/18.
 */
public class MergeSort {

    public static void main(String... args) {
        int[] a = {7,8,9,1,2,3,10,23,0};
        mergesort(a);
        System.out.println(Arrays.toString(mergesort(a)));
    }

    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i] <= b[j])  c[k] = a[i++];
            else                    c[k] = b[j++];
        }
        return c;
    }

    public static int[] mergesort(int[] input) {
        int N = input.length;
        if (N <= 1) return input;
        int[] a = new int[N/2];
        int[] b = new int[N - N/2];
        for (int i = 0; i < a.length; i++)
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)
            b[i] = input[i + N/2];
        return merge(mergesort(a), mergesort(b));
    }

}
