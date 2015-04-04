package au.com.aitcollaboration.chessgame.support;

public class Utils {

    public static boolean tossCoin(String coinSide) {
        int chance = (int) (Math.random() * 10);
        String coinSideResult = (chance > 4) ? Constants.COIN_HEAD : Constants.COIN_TAIL;
        return coinSideResult.equalsIgnoreCase(coinSide);
    }
}
