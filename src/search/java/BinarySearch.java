package search.java;

import sort.java.BubbleSort;

import java.util.Arrays;

public class BinarySearch {

    public static int search(int[] array, int key) {
        return  binarySearch(array, 0, array.length -1, key);
    }

    private static int binarySearch(int arr[], int l, int r, int key) {
        if (r >= 1) {
            int mid = (r + l)/2;
            if(arr[mid] == key) {

            }
        }
        return -1;
    }

    public static void main(String... args) {
        int[] a = {7,8,9,1,2,3,10,23,0};
        BubbleSort.bubbleSort(a);
        int key = 11;
        Arrays.stream(a).forEach(System.out::println);
        System.out.println(String.format("found key : %d", search(a, key)));
    }
}
