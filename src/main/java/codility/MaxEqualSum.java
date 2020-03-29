package codility;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class MaxEqualSum {

    static int digitSum(int n)
    {
        int sum = 0;
        while (n != 0)
        {
            sum += (n % 10);
            n /= 10;
        }
        return sum;
    }

    static int findSum(int arr[])
    {

        HashMap<Integer, Integer> mp = new HashMap<>();

        int ans = -1;

        for (int i = 0; i < arr.length; i++)
        {
            int dSum = digitSum(arr[i]);

            if (Objects.nonNull(mp.get(dSum)))
            {
                ans = Math.max(ans, mp.get(dSum) + arr[i]);
            }
            mp.put(dSum, Math.max(Optional.ofNullable(mp.get(dSum)).orElse(0) , arr[i]));
        }
        return ans;
    }

    public static void main (String[] args)
    {
        int arr1[] = {51, 71, 17, 42};
        int arr2[] = {42, 33, 60};
        int arr3[] = {51, 32, 43};

        System.out.println(findSum(arr1));
        System.out.println(findSum(arr2));
        System.out.println(findSum(arr3));
    }

}
