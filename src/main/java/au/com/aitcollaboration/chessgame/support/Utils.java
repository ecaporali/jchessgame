package au.com.aitcollaboration.chessgame.support;

import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.player.Color;

import java.util.Map;

public class Utils {

    public static boolean tossCoin(String coinSide){
        int chance = (int) (Math.random() * 10);
        String coinSideResult = (chance > 4) ? Constants.COIN_HEAD : Constants.COIN_TAIL;
        return coinSideResult.equalsIgnoreCase(coinSide);
    }

    public static Color getColorFromMap(Map<Color, Pieces> map){
        return map.entrySet().iterator().next().getKey();
    }
}
