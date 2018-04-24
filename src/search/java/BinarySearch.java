package search.java;

import sort.java.BubbleSort;

import java.util.Arrays;

public class BinarySearch {

    public static int search(int[] array, int key) {
        return  binarySearch(array, 0, array.length -1, key);
    }

    private static int binarySearch(int arr[], int l, int r, int key) {
        if (l <= r)
        {
            int mid = l + (r - l)/2;

            if(arr[mid] == key)
                return mid;

            if(key < arr[mid])
                return binarySearch(arr, l, mid - 1, key);

            return binarySearch(arr, mid + 1, r, key);

        }
        return -1;
    }

    public static void main(String... args) {
        int[] a = {7,8,9,1,2,3,10,23,0};
        BubbleSort.bubbleSort(a);
        int key = 22;
        Arrays.stream(a).forEach(System.out::println);
        int midIndex = search(a, key);
        if (midIndex == -1)
            System.out.println("no key found");
        else
            System.out.println(String.format("key found at position : %d", midIndex));
    }
}
