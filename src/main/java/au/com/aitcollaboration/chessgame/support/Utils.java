package au.com.aitcollaboration.chessgame.support;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import com.rits.cloning.Cloner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final int RANDOM_NUM = 10;

    private Utils() {
    }

    public static String tossCoin() {
        int chance = new Random().nextInt(RANDOM_NUM);
        return (chance >= (RANDOM_NUM / 2))
                ? Constants.COIN_HEAD : Constants.COIN_TAIL;
    }

    public static int[] toBoardPosition(final String answer)
            throws InvalidPositionException {
        if (answer == null || answer.isEmpty() || answer.length() != 2)
            throw new InvalidPositionException();

        int x = "87654321".indexOf(answer.charAt(1));

        int y = "ABCDEFGH".indexOf(answer.toUpperCase().charAt(0));

        if (x < 0 || y < 0)
            throw new InvalidPositionException();

        return new int[]{x, y};
    }

    public static String toGamePosition(int[] position) {
        if (position == null || position.length != 2)
            throw new InvalidCoordinatesException();

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

    public static <T> T deepCopyOf(T objectToCopy) {
        Cloner cloner = new Cloner();
        return cloner.deepClone(objectToCopy);
    }

    public static boolean isValidPieceToPromote(String chosenPiece) {
        Pattern myPattern = Pattern.compile("Queen|Rook|Bishop|Knight|None", Pattern.CASE_INSENSITIVE);
        Matcher myMatcher = myPattern.matcher(chosenPiece);
        return myMatcher.matches();
    }

    public static Piece getPieceInstanceFromClass(String chosenPiece, Color currentColor) {
        chosenPiece = Character.toString(chosenPiece.charAt(0)).toUpperCase() + chosenPiece.substring(1).toLowerCase();
        try {
            Class pieceClass = Class.forName(Constants.CHESS_PIECES_PACKAGE_PATH + chosenPiece);
            Constructor constructor = pieceClass.getConstructors()[0];
            return (Piece) constructor.newInstance(currentColor);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
