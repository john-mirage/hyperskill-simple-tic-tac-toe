package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    char[][] matrix;
    Scanner scanner;
    char currentPlayer;
    boolean isFinished;
    String state;

    public Game() {
        this.matrix = createMatrix();
        this.scanner = new Scanner(System.in);
        this.currentPlayer = 'X';
        this.isFinished = false;
        this.state = "Draw";
    }

    private char[][] createMatrix() {
        char[][] matrix = new char[3][3];
        for (char[] chars : matrix) {
            Arrays.fill(chars, ' ');
        }
        return matrix;
    }

    private void print() {
        System.out.println("---------");
        for (char[] symbols : this.matrix) {
            System.out.print("| ");
            for (char symbol : symbols) {
                System.out.print(symbol + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private void analyze() {
        boolean hasSpotLeft = false;
        ArrayList<String> data = new ArrayList<>();
        StringBuilder diagonal1 = new StringBuilder();
        StringBuilder diagonal2 = new StringBuilder();
        for (int i = 0; i < this.matrix.length; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder col = new StringBuilder();
            diagonal1.append(this.matrix[i][i]);
            diagonal2.append(this.matrix[this.matrix.length - 1 - i][i]);
            for (int j = 0; j < this.matrix[i].length; j++) {
                row.append(this.matrix[i][j]);
                col.append(this.matrix[j][i]);
                if (!hasSpotLeft && this.matrix[i][j] == ' ') {
                    hasSpotLeft = true;
                }
            }
            data.add(row.toString());
            data.add(col.toString());
        }
        data.add(diagonal1.toString());
        data.add(diagonal2.toString());
        this.process(data, hasSpotLeft);
    }

    private void process(ArrayList<String> data, boolean hasSpotLeft) {
        for (String symbols : data) {
            if (symbols.equals("XXX") || symbols.equals("OOO")) {
                this.isFinished = true;
                this.state = symbols.charAt(0) + " wins!";
            }
        }
        if (!this.isFinished && !hasSpotLeft) {
            this.isFinished = true;
        }
    }

    private void askForCoordinates() {
        while (true) {
            String rowInput = this.scanner.next();
            String colInput = this.scanner.next();
            try {
                int row = Integer.parseInt(rowInput);
                int col = Integer.parseInt(colInput);
                if ((row > 0 && row <= 3) && (col > 0 && col <= 3)) {
                    if (this.matrix[row - 1][col - 1] == ' ') {
                        this.matrix[row - 1][col - 1] = this.currentPlayer;
                        this.print();
                        this.currentPlayer = this.currentPlayer == 'X' ? 'O' : 'X';
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public void start() {
        this.print();
        while (!this.isFinished) {
            this.askForCoordinates();
            this.analyze();
        }
        System.out.println(this.state);
    }
}
