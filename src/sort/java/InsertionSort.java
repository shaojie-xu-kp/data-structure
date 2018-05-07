package sort.java;

import java.util.Arrays;

public class InsertionSort {

    public static void main (String ... args) {
        int[] array = {4,3,1,2,9,0,7,8};
        insertionSort(array);
        Arrays.stream(array).forEach(System.out::println);
    }

    private static void insertionSort(int[] array) {
        int length = array.length;
        for (int key = 1; key < length; key++) {
            for (int j = key; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
}
