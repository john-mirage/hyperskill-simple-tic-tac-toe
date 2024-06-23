package tictactoe;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) throws IllegalArgumentException {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IllegalArgumentException("Coordinate constructor: Invalid coordinate");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
