package sort.java;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by shaojiexu on 2/18/18.
 */
public class MergeSort {

    public static void main(String... args) {


        int[] a = IntStream.generate(() -> new Random().nextInt(1000000)).limit(10000).toArray();
        long startTime = System.nanoTime();
        mergesort(a);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(String.format("running time for traditional merge sort: %d, %s", duration, Arrays.toString(mergesort(a))));

        int[] dest = IntStream.generate(() -> new Random().nextInt(1000000)).limit(10000).toArray();
        int[] src = dest.clone();
        long startTime1 = System.nanoTime();
        mergeSort(src, dest, 0, dest.length);
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1);
        System.out.println(String.format("running time for enhanced jdk merge sort: %d, %s", duration1, Arrays.toString(mergesort(dest))));

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
        int[] a = Arrays.copyOfRange(input, 0, N/2);
        int[] b = Arrays.copyOfRange(input, N/2, N);
        return merge(mergesort(a), mergesort(b));
    }


    private static final int INSERTIONSORT_THRESHOLD = 7;

    /**
     * Src is the source array that starts at index 0
     * Dest is the (possibly larger) array destination with a possible offset
     * low is the index in dest to start sorting
     * high is the end index in dest to end sorting
     * To be removed in a future release.
     */
    private static void mergeSort(int[] src,
                                  int[] dest,
                                  int low,
                                  int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low && dest[j-1] > dest[j]; j--)
                    swap(dest, j, j-1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid);
        mergeSort(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid-1] <= src[mid]) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }



}
