package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.service.ValidationService;
import au.com.aitcollaboration.chessgame.view.exceptions.*;

import java.util.*;

public class RulesController {

    private Map<Pieces, PlayerMoves> possibleMoves;
    private ValidationService validationService;
    private Board board;

    private RulesController() {
        this.possibleMoves = new HashMap<>();
    }

    public RulesController(Board board, ValidationService validationService) {
        this();
        this.board = board;
        this.validationService = validationService;
    }

    public boolean isGameOver() {
        return isMatchDraw() || isCheckMate();
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

        Collection<PlayerMoves> opponentMoves = getOpponentMoves(pieces);
        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        validationService.validateMove(fromSquare, kingSquare, pieces, opponentMoves, currentPieceMoves);

        PlayerMoves playerMoves = getPlayerMoves(pieces);
        playerMoves.add(currentPiece, currentPieceMoves);
    }

    public void mockPieceMove(Square fromSquare) {
        Piece currentPiece = fromSquare.getPiece();
        if (currentPiece != null) {
            fromSquare.setPiece(null);
            findAllPossibleMovesOnBoard();
            fromSquare.setPiece(currentPiece);
        }
    }

    public Collection<PlayerMoves> getOpponentMoves(Pieces pieces) {
        Map<Pieces, PlayerMoves> playerMovesMap = new HashMap<>(possibleMoves);
        playerMovesMap.remove(pieces);
        return playerMovesMap.values();
    }

    public PlayerMoves getPlayerMoves(Square fromSquare, Pieces pieces) throws Exception {
        mockPieceMove(fromSquare);
        validatePieceMove(fromSquare, pieces);
        return getPlayerMoves(pieces);
    }
}
