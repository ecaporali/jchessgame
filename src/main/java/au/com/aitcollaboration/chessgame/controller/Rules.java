package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.exceptions.*;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.service.ValidationService;

public class Rules {

    //    private Map<Pieces, PlayerMoves> possibleMoves;
    private final ValidationService validationService;

    public Rules(ValidationService validationService) {
//        this.possibleMoves = new HashMap<>();
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

    public PieceMoves validatePieceMove(Square fromSquare, Board board, Pieces pieces) throws InvalidMoveException {
//        Piece currentPiece = fromSquare.getPiece();

        try {
            return validationService.validateMove(fromSquare, board, pieces);
        } catch (PieceCannotBeMovedException | InvalidPieceException | PieceNotFoundException | KingInCheckException | KingInDangerException e) {
            throw new InvalidMoveException(e);
        }
    }

//    public void findAllPossibleMovesOnBoard(Board board) {
//        possibleMoves = board.getAllValidMoves();
//    }

//    public PlayerMoves getPlayerMoves(Pieces pieces) {
//        return possibleMoves.get(pieces);
//    }

//    public void validatePieceMove(Square fromSquare, Pieces pieces, Board board) throws InvalidMoveException {
//        Piece king = pieces.getPiece(King.class);
//        Square kingSquare = board.getCurrentSquare(king);
//
//        Piece currentPiece = fromSquare.getPiece();
//
//        if (currentPiece == null)
//            throw new PieceNotFoundException();
//
//        PlayerMoves opponentMoves = getOpponentMoves(pieces);
//        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);
//
//        try{
//            validationService.validateMove(fromSquare, kingSquare, pieces, opponentMoves, currentPieceMoves);
//        }catch (PieceCannotBeMovedException | InvalidPieceException | KingInDangerException | KingInCheckException e){
//            throw new InvalidMoveException(e);
//        }
//
//        PlayerMoves playerMoves = getPlayerMoves(pieces);
//        playerMoves.add(currentPiece, currentPieceMoves);
//    }

//    private PlayerMoves getOpponentMoves(Pieces pieces) {
//        Map<Pieces, PlayerMoves> playerMovesMap = new HashMap<>(possibleMoves);
//        playerMovesMap.remove(pieces);
//        return playerMovesMap.values().iterator().next();
//    }
}
