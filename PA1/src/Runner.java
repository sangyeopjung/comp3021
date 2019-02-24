import Exceptions.InvalidMapException;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws InvalidMapException {
        Game g = new Game();
        if (args.length != 1) {
            System.out.println("Please specify map name.");
            System.exit(-1);
        }
        System.out.println("Loading map: " + args[0]);
        g.loadMap(args[0]);
        g.display();

        try (Scanner reader = new Scanner(System.in)) {
            while (!g.isWin()) {
                if (g.isDeadlocked()) {
                    System.out.println("Game deadlocked. Terminating...");
                    return;
                }
                char c;
                do {
                    System.out.print("Enter a valid move [wasdr]: ");
                    c = reader.next().trim().toLowerCase().charAt(0);
                } while (!"wasdr".contains("" + c));

                g.makeMove(c);
                g.display();
            }
            System.out.println("You win!");
        }
    }
}
