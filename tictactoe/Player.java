package tictactoe;

public class Player {
    private final char symbol;

    public Player(char symbol) throws IllegalArgumentException {
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Player constructor: Invalid symbol");
        }
        this.symbol = symbol;
    }

    public Cell getOneCell() {
        Coordinate coordinate = UserInterface.askForCoordinate();
        return new Cell(coordinate.getX(), coordinate.getY(), this.symbol);
    }
}
