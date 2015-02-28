package au.com.aitcollaboration.chessgame.utils;

import java.util.Scanner;

/**
 * Created by Massimo on 28/02/15.
 */
public class In {
    private static Scanner in = new Scanner(System.in);

    public static String nextLine(String question) {
        System.out.print("\n"+ question);
        return in.nextLine();
    }

    public static char nextChar() {
        return in.nextLine().charAt(0);
    }

    public static int nextInt() {
        int i = in.nextInt();
        in.nextLine();
        return i;
    }

    public static double nextDouble() {
        double d = in.nextDouble();
        in.nextLine();
        return d;
    }
}
