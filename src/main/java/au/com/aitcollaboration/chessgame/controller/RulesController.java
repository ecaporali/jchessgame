package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.service.validation.KingInCheckMateService;
import au.com.aitcollaboration.chessgame.service.validation.MoveSelectionService;
import au.com.aitcollaboration.chessgame.service.validation.MoveValidation;
import au.com.aitcollaboration.chessgame.service.validation.PieceSelectionService;
import au.com.aitcollaboration.chessgame.view.exceptions.*;

import java.util.*;

public class RulesController {

    private Map<Pieces, PlayerMoves> possibleMoves;
    private List<MoveValidation> moveValidation;
    private Board board;

    private RulesController() {
        this.possibleMoves = new HashMap<>();
    }

    public RulesController(Board board) {
        this();
        this.board = board;
        this.moveValidation = RulesController.buildMoveValidation();
    }

    public static List<MoveValidation> buildMoveValidation() {
        return Arrays.asList(
                new KingInCheckMateService(),
                new KingInCheckMateService(),
                new MoveSelectionService(),
                new PieceSelectionService()
        );
    }

    public boolean isGameOver() {
        return isMatchDraw() ||
                isCheckMate();
    }

    public boolean isCheckMate() {
        return false;
    }

    public boolean isMatchDraw() {
        return false;
    }

    public void findAllPossibleMovesOnBoard() {
        possibleMoves.clear();
        possibleMoves = board.getAllValidMoves();
    }

    public PlayerMoves getPlayerMoves(Pieces pieces) {
        return possibleMoves.get(pieces);
    }

    public void validatePieceMove(Square fromSquare, Pieces pieces) throws Exception {
        Piece king = pieces.getPiece(King.class);
        Square kingSquare = board.getCurrentSquare(king);

        Piece currentPiece = fromSquare.getPiece();

        if (currentPiece == null)
            throw new PieceNotFoundException();

        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        Collection<PlayerMoves> opponentMoves = getOpponentMoves(pieces);

        if (currentPieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        if (!pieces.contains(currentPiece))
            throw new InvalidPieceException();

        if (isKingInDanger(fromSquare, kingSquare, opponentMoves))
            throw new KingInDangerException();

        if (isKingInCheck(kingSquare, opponentMoves)) {
            if (kingCannotBeSaved(fromSquare, kingSquare, currentPieceMoves, opponentMoves)) {
                throw new KingInCheckException();
            }
        }
    }

    private boolean kingCannotBeSaved(Square fromSquare, Square kingSquare, PieceMoves currentPieceMoves, Collection<PlayerMoves> opponentMoves) {
        if (fromSquare.equals(kingSquare))
            return false;


        for (PlayerMoves opponentPlayerMoves : opponentMoves)
            if (opponentPlayerMoves.canEatKing(currentPieceMoves))
                return true;

        return false;
    }


    public void mockPieceMove(Square fromSquare) {
        Piece currentPiece = fromSquare.getPiece();
        if (currentPiece != null) {
            fromSquare.setPiece(null);
            fromSquare.setPiece(currentPiece);
        }
    }

    private boolean isKingInDanger(Square fromSquare, Square kingSquare, Collection<PlayerMoves> opponentMoves) {
        for (PlayerMoves opponentPlayerMoves : opponentMoves)
            if (opponentPlayerMoves.isKingInDanger(fromSquare, kingSquare))
                return true;

        return false;
    }

    private boolean isKingInCheck(Square kingSquare, Collection<PlayerMoves> opponentMoves) {
        for (PlayerMoves opponentPlayerMoves : opponentMoves)
            if (opponentPlayerMoves.isKingInCheck(kingSquare))
                return true;

        return false;
    }

    public Collection<PlayerMoves> getOpponentMoves(Pieces pieces) {
        Map<Pieces, PlayerMoves> playerMovesMap = new HashMap<>(possibleMoves);
        playerMovesMap.remove(pieces);
        return playerMovesMap.values();
    }

    public PlayerMoves getPlayerMoves(Square fromSquare, Pieces pieces) throws Exception {
        findAllPossibleMovesOnBoard();
        validatePieceMove(fromSquare, pieces);
        return getPlayerMoves(pieces);
    }
}
