package tictactoe;

public class CellNotAvailableException extends RuntimeException {
    public CellNotAvailableException(String message) {
        super(message);
    }
}
