package codility;

import java.util.Arrays;

public class DigitsToTime {

    public final static String COLLON = ":";
    public final static String NOT_POSSIBLE = "NOT POSSIBLE";
    public static String solution(int A, int B, int C, int D, int E, int F) {
        // write your code in Java SE 8

        int[] d = {A, B, C, D, E, F};

        // sort the array in ascending order
        Arrays.sort(d);

        StringBuilder sb = new StringBuilder();

        //24:59:59 -> HH:MM:SS

        // if the first digit of second is larger than 6, NOT POSSIBLE
        // else all the previous digit is less than 6
        if (d[4] < 6) {
            // start to build the hour part and it should be non greater 24
            if (10 * d[0] + d[1] < 24)
                 sb.append(d[0])
                         .append(d[1])
                         .append(COLLON)
                         .append(d[2])
                         .append(d[3])
                         .append(COLLON)
                         .append(d[4])
                         .append(d[5]);

            else
                 sb.append(NOT_POSSIBLE);
        }
        // if the last 2 digit is greater than 6 is okay, we can allow max 3 digits bigger than 6
        else if (d[3] < 6) {
            // still check the hour part
            if (10 * d[0] + d[1] < 24)
                // combine the second digit with 4th, as we know 4th is bigger than 6, but still less than 5th
                sb.append(d[0])
                        .append(d[1])
                        .append(COLLON)
                        .append(d[2])
                        .append(d[4])
                        .append(COLLON)
                        .append(d[3])
                        .append(d[5]);
            else
                sb.append(NOT_POSSIBLE);
        }
        // here it is, 3 digits non smaller than 6, than possibly 1 for H, 1 for M, 1 for S
        else if (d[2] < 6) {
            if (10 * d[0] + d[3] < 24)
                sb.append(d[0])
                        .append(d[3])
                        .append(COLLON)
                        .append(d[1])
                        .append(d[4])
                        .append(COLLON)
                        .append(d[2])
                        .append(d[5]);
            else
                sb.append(NOT_POSSIBLE);
        } else {
            sb.append(NOT_POSSIBLE);
        }

        return sb.toString();

    }

    public static void main(String... args){
        System.out.println(solution(1,2,2,6,6,7));
    }


}
