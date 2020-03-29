package codility;

import java.util.Random;

public class RiddleString {

    final static char QUESTION_MARK = '?';

    static String riddleString(String riddle) {

        StringBuilder riddleBuilder = new StringBuilder(riddle);
        char c;
        for(int i=0 ; i <riddleBuilder.length(); i++) {
            if(riddleBuilder.charAt(i) == QUESTION_MARK) {
                if(i ==0) {
                    c = getRandomChar(QUESTION_MARK, riddleBuilder.charAt(i+1));
                    riddleBuilder.setCharAt(i, c);
                } else if (i == riddleBuilder.length()-1) {
                    c = getRandomChar(riddleBuilder.charAt(i-1), QUESTION_MARK);
                    riddleBuilder.setCharAt(i, c);
                } else {
                    c = getRandomChar( riddleBuilder.charAt(i-1),riddleBuilder.charAt(i+1));
                    riddleBuilder.setCharAt(i, c);
                }
            }

        }
        return riddleBuilder.toString();
    }

    static char getRandomChar(char pre, char nxt) {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');

        while(c == pre || c == nxt ) {
            c = (char)(r.nextInt(26) + 'a');
        }

        return c;
    }

    public static void main(String... args) {
        System.out.println(riddleString("ab?ac?"));
        System.out.println(riddleString("????????"));
    }

}
