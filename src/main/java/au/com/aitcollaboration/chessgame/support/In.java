package au.com.aitcollaboration.chessgame.support;

import java.text.ParseException;
import java.util.Scanner;

public class In {
    private static final Scanner IN = new Scanner(System.in);

    public static String nextLine(String question) {
        System.out.print("\n" + question);
        return IN.nextLine();
    }

    public static char nextChar() {
        return IN.nextLine().charAt(0);
    }

    public static int nextInt(String question) throws NumberFormatException{
        String answer = nextLine(question);
        return Integer.parseInt(answer);
    }

    public static double nextDouble() {
        double d = IN.nextDouble();
        IN.nextLine();
        return d;
    }
}
