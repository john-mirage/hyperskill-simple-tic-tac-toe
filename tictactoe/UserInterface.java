package tictactoe;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinate askForCoordinate() {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("^\\d \\d$")) {
                String[] coordinates = input.split(" ");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                if (x >= 1 && x <= 3 && y >= 1 && y <= 3) {
                    return new Coordinate(x, y);
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
