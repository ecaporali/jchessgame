package au.com.aitcollaboration.chessgame.support;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;
import com.rits.cloning.Cloner;

public class Utils {

    public static String tossCoin() {
        int chance = (int) (Math.random() * 10);
        return (chance > 4) ? Constants.COIN_HEAD : Constants.COIN_TAIL;
    }

    public static int[] toBoardPosition(String answer) throws InvalidPositionException {
        if (answer.isEmpty() || answer.length() != 2)
            throw new InvalidPositionException();

        int x = "87654321".indexOf(answer.charAt(1));

        int y = "ABCDEFGH".indexOf(answer.toUpperCase().charAt(0));

        if (x < 0 || y < 0)
            throw new InvalidPositionException();

        return new int[]{x, y};
    }

    public static String toGamePosition(int[] position) {
        int numeric = position[0];
        int alpha = position[1];

        char number = "87654321".charAt(numeric);
        char letter = "ABCDEFGH".charAt(alpha);

        return letter + "" + number;
    }

    public static String boardToConsole(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        Square[][] grid = board.getClonedGrid();

        for (int i = 0; i < 50; ++i)
            stringBuilder.append("\n");

        for (int i = 0; i < grid.length; i++) {
            String s = Utils.getDivider();
            s += Utils.addVerticalNumbering(Board.BOARD_SIZE - i);
            for (int j = 0; j < grid[i].length; j++) {
                Square square = grid[i][j];
                s += square;
            }
            s += "|\n";
            stringBuilder.append(s);
        }
        stringBuilder.append(Utils.getDivider());

        stringBuilder.append("\t");

        for (int i = 0; i < Board.BOARD_SIZE; i++)
            stringBuilder.append(Utils.addHorizontalLetters(i + 65));

        return stringBuilder.toString();
    }

    private static String getDivider() {
        return "\t---------------------------------\n";
    }

    private static String addVerticalNumbering(int position) {
        return "  " + position + " ";
    }

    private static String addHorizontalLetters(int position) {
        return "  " + Character.toChars(position)[0] + " ";
    }

    public static Board deepCopyOf(Board board){
        Cloner cloner = new Cloner();
        return cloner.deepClone(board);
    }
}
