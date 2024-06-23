package tictactoe;

import java.util.Arrays;

public class Board {
    private final char[][] matrix;
    private State state;
    private int xCount;
    private int oCount;

    public Board() {
        this.matrix = this.createMatrix();
        this.state = State.NOT_FINISHED;
        this.xCount = 0;
        this.oCount = 0;
    }

    private char[][] createMatrix() {
        char[][] matrix = new char[3][3];
        for (char[] chars : matrix) {
            Arrays.fill(chars, ' ');
        }
        return matrix;
    }

    public State getState() {
        return this.state;
    }

    private void checkCoordinate(Coordinate coordinate) throws IllegalArgumentException {
        if (coordinate == null) {
            throw new IllegalArgumentException("Board (checkCoordinate): Coordinate cannot be null");
        }
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (x < 1 || x > this.matrix.length || y < 1 || y > this.matrix[0].length) {
            throw new IllegalArgumentException("Board (checkCoordinate): Coordinates are out of bounds");
        }
    }

    private void checkCell(Cell cell) throws IllegalArgumentException {
        this.checkCoordinate(cell);
        if (cell.getSymbol() != 'X' && cell.getSymbol() != 'O') {
            throw new IllegalArgumentException("Board (checkCell): Symbol is not valid");
        }
    }

    private boolean symbolHasWin(char symbol) throws IllegalArgumentException {
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Board (symbolHasWin): Invalid symbol: " + symbol);
        }
        boolean firstDiagonalWin = true;
        boolean secondDiagonalWin = true;
        for (int i = 0; i < this.matrix.length; i++) {
            if (this.matrix[i][i] != symbol) {
                firstDiagonalWin = false;
            }
            if (this.matrix[i][this.matrix.length - 1 - i] != symbol) {
                secondDiagonalWin = false;
            }
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < this.matrix.length; j++) {
                if (this.matrix[i][j] != symbol) {
                    rowWin = false;
                }
                if (this.matrix[j][i] != symbol) {
                    colWin = false;
                }
            }
            if (rowWin || colWin) {
                return true;
            }
        }
        if (firstDiagonalWin || secondDiagonalWin) {
            return true;
        }
        return false;
    }

    private boolean boardIsFull() {
        for (char[] row : this.matrix) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cellIsEmpty(Coordinate coordinate) throws IllegalArgumentException {
        this.checkCoordinate(coordinate);
        return this.matrix[coordinate.getX() - 1][coordinate.getY() - 1] == ' ';
    }

    public void addCell(Cell cell) throws IllegalArgumentException, IllegalStateException, CellNotAvailableException {
        this.checkCell(cell);
        if (!this.cellIsEmpty(cell)) {
            throw new CellNotAvailableException("This cell is occupied! Choose another one!");
        }
        int x = cell.getX();
        int y = cell.getY();
        char symbol = cell.getSymbol();
        int countDifference = this.xCount - this.oCount;
        if (Math.abs(countDifference) > 1) {
            throw new IllegalStateException("Board (addCell): The board is in an impossible state");
        }
        if ((symbol == 'X' && countDifference > 0) || (symbol == 'O' && countDifference < 0)) {
            throw new IllegalArgumentException("It's not your turn!");
        }
        if (symbol == 'X') {
            this.xCount++;
        } else {
            this.oCount++;
        }
        this.matrix[x - 1][y - 1] = symbol;
        if (this.symbolHasWin(symbol)) {
            this.state = (symbol == 'X') ? State.X_WINS : State.O_WINS;
        } else if (this.boardIsFull()) {
            this.state = State.DRAW;
        }
    }

    @Override
    public String toString() {
        StringBuilder battlefield = new StringBuilder("---------\n");
        for (char[] symbols : this.matrix) {
            battlefield.append("| ");
            for (char symbol : symbols) {
                battlefield.append(symbol).append(" ");
            }
            battlefield.append("|\n");
        }
        battlefield.append("---------");
        return battlefield.toString();
    }
}
