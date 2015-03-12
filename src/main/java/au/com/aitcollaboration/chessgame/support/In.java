package au.com.aitcollaboration.chessgame.support;

import java.util.Scanner;

public class In {
    private static final Scanner in = new Scanner(System.in);

    public static String nextLine(String question) {
        System.out.print("\n" + question);
        return in.nextLine();
    }

    public static char nextChar() {
        return in.nextLine().charAt(0);
    }

    public static int nextInt(String question) throws NumberFormatException {
        String answer = nextLine(question);
        return Integer.parseInt(answer);
    }

    public static double nextDouble() {
        double d = in.nextDouble();
        in.nextLine();
        return d;
    }
}
