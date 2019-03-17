class Round {
  static boolean playRound(Game game) {
    while (game.getGuessNumber() > 0 && !game.isWinner()) {
      boolean isCorrect = Turn.playTurn(game);

      if (isCorrect) {
        System.out.println("You guessed at least one of the letters correctly");
      } else {
        game.oneLessGuess();
        System.out.println("You didn't guess one of the letters correctly");
      }

      System.out.println("You have " +  game.getGuessNumber() + " guess(es) left.");
      System.out.println("Here's the running solution: " + game.getRunningSolution());
    }

    System.out.println("Round over");

    return game.isWinner();
  }
}
