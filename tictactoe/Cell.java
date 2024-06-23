package tictactoe;

public class Cell extends Coordinate {
    private final char symbol;

    public Cell(int x, int y, char symbol) throws IllegalArgumentException {
        super(x, y);
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Cell constructor: Invalid symbol");
        }
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }
}
