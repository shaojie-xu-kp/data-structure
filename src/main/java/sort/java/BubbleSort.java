package sort.java;

import java.util.Arrays;

/**
 * a simple java imperative way of implementing bubble sort
 */
public class BubbleSort {

    public static void main(String... args) {
        int[] a = {7,8,9,1,2,3,10,23,0};
        System.out.println(Arrays.toString(bubbleSort(a)));
    }

    public  static int[] bubbleSort(int[] a) {
        for(int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length -1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

}
