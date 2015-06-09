//package au.com.aitcollaboration.chessgame.service;
//
//import au.com.aitcollaboration.chessgame.model.game.structure.Square;
//import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ValidationResult {
//
//    private PieceMoves pieceMoves;
//    private List<ValidationError> errorMessages;
//    private ValidationError validationError;
//
//    public ValidationResult(){
//        this.pieceMoves = new PieceMoves();
//        this.errorMessages = new ArrayList<>();
//    }
//
//    public void addValidSquare(Square square) {
//        pieceMoves.add(square);
//    }
//
//    public void addErrorMessage(String errorMessage) {
//        errorMessages.add(new ValidationError(errorMessage));
//    }
//
//    public void clearErrors() {
//        errorMessages.clear();
//    }
//}
