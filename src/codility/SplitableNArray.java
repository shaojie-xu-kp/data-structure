package codility;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class SplitableNArray {

    public int solution(int[] A) {

        int a = splitN(A, 1);
        return a;
    }


    public int splitN(int[] A, int base) {

        if(Objects.isNull(A) || A.length == 1) {
            return base;
        }

        int midIndex = A.length / 2;
        int[] l = Arrays.copyOfRange(A, 0, midIndex);
        int[] r = Arrays.copyOfRange(A, midIndex, A.length);
        int maxLeft = Arrays.stream(l).max().getAsInt();
        int minRight = Arrays.stream(r).min().getAsInt();

        if(maxLeft <= minRight) {
            base = splitN(l, base + 1);
            base = splitN(r, base);
        } else {
            base = splitN(l, base);
            base = splitN(r, base);
        }

        return base;
    }

    public static void main(String... args){

        int a[]= {2, 4, 1, 6, 5, 9, 7};
        int b[] = {4,3,2,6,1};
        int c[] = {2, 1, 6, 4, 3, 7};
        int d[] = {0,0,0,0,0,0};
        int e[] = {1,2,3,4,5,6,7};

        int[]  randomIntsArray = IntStream.generate(() -> new Random().nextInt(1000000000)).limit(100000).toArray();

        SplitableNArray sp = new SplitableNArray();
        System.out.println(sp.solution(a));
        System.out.println(sp.solution(b));
        System.out.println(sp.solution(c));
        System.out.println(sp.solution(d));
        System.out.println(sp.solution(e));
        System.out.println(sp.solution(randomIntsArray));
    }

}
