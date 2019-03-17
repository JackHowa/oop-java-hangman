import java.util.Scanner;

class Turn {
  static boolean playTurn(Game game) {
    System.out.print("Guess a letter\n");
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();

    if (input.length() != 1) {
      System.out.println("That was not one letter");
      return playTurn(game);
    }

    return game.handleGuess(input);
  }
}
