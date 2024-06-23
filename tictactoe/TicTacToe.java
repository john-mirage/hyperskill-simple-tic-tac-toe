package tictactoe;

public class TicTacToe {
    private final Board board;

    public TicTacToe() {
        this.board = new Board();
    }

    public void play() {
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        Player currentPlayer = player1;
        System.out.println(this.board);
        while (this.board.getState().getName().equals("Not finished")) {
            Cell cell = currentPlayer.getOneCell();
            try {
                this.board.addCell(cell);
                System.out.println(this.board);
                currentPlayer = currentPlayer == player1 ? player2 : player1;
            } catch (CellNotAvailableException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(this.board.getState().getName());
    }
}
