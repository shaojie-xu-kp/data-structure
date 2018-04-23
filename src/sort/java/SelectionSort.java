package sort.java;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String... args) {
        int[] a = {7,8,9,1,2,3,10,23,0};
        System.out.println(Arrays.toString(selectionSort(a)));
    }

    private static int[] selectionSort(int[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            int imin = i;
            for(int j = i + 1; j < a.length; j++) {
                if (a[imin] > a[j]) {
                    imin = j;
                }
            }
            if(i != imin) {
                int temp = a[i];
                a[i] = a[imin];
                a[imin] = temp;
            }
        }
        return a;
    }
}
