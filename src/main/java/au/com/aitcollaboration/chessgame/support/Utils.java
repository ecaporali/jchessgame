package au.com.aitcollaboration.chessgame.support;

import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;

public class Utils {

    public static boolean tossCoin(String coinSide) {
        int chance = (int) (Math.random() * 10);
        String coinSideResult = (chance > 4) ? Constants.COIN_HEAD : Constants.COIN_TAIL;
        return coinSideResult.equalsIgnoreCase(coinSide);
    }

    public static int[] getConvertedPosition(String answer) throws InvalidPositionException {
        if (answer.isEmpty() || answer.length() != 2)
            throw new InvalidPositionException();

        int x = "87654321".indexOf(answer.charAt(1));

        int y = "ABCDEFGH".indexOf(answer.toUpperCase().charAt(0));

        if (x < 0 || y < 0)
            throw new InvalidPositionException();

        return new int[]{x, y};
    }

    public static String getGamePosition(int[] position) {
        int numeric = position[0];
        int alpha = position[1];

        char number = "87654321".charAt(numeric);
        char letter = "ABCDEFGH".charAt(alpha);

        return letter + "" + number;
    }
}
