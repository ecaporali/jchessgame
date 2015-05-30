package au.com.aitcollaboration.chessgame.support;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class In {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String nextLine(String question) {
        System.out.print("\n" + question);

        String line = "";
        try {
            line = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return line;
    }

    public static char nextChar(String question) {
        return nextLine(question).charAt(0);
    }

    public static int nextInt(String question) {
        while (true) {
            try {
                String answer = nextLine(question);
                return Integer.valueOf(answer);
            } catch (NumberFormatException e) {
                MyLogger.debug(e);
                System.out.println(UIMessages.INVALID_NUMBER_EXCEPTION);
            }
        }
    }

    public static double nextDouble(String question) {
        return Double.valueOf(nextLine(question));
    }

    //Used only for testing
    public static void setInputStream(BufferedReader bufferedReader) {
        In.in = bufferedReader;
    }
}
