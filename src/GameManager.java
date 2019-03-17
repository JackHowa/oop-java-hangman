import util.AppConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author jackhowa
 */
public class GameManager {
  private ArrayList<String> availableSolutions;
  private String fileName;

  private ArrayList<String> getAvailableSolutions() {
    return availableSolutions;
  }

  private GameManager(String fileName) {
    this.fileName = fileName;
    this.availableSolutions = findAvailableSolution();
  }

  private String getRandomSolution() {
    ArrayList<String> availableSolutions = this.getAvailableSolutions();

    int randomNumber = GameManager.findRandomNumber(availableSolutions.size() - 1, 0);

    return availableSolutions.get(randomNumber);
  }

  private ArrayList<String> findAvailableSolution() {
   File file = new File(this.fileName);

   Scanner scanner = null;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    ArrayList<String> runningAvailable = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String nextLine = scanner.nextLine();

      runningAvailable.add(nextLine);
    }

    return runningAvailable;
  }

  public static void main(String[] args) {
    GameManager gameManager = new GameManager(AppConstants.MOVE_LIST);

    boolean continuePlaying = true;

    while (continuePlaying) {
      String randomSolution = gameManager.getRandomSolution();

      Game game = new Game(randomSolution);

      boolean winGame = Round.playRound(game);

      if (winGame) {
        System.out.println("You won the round!");
      } else {
        System.out.println("You lost the run :(");
      }

      Scanner scanner = new Scanner(System.in);

      System.out.println("Type 'yes' to play again");

      String nextRoundInput = scanner.nextLine();

      if ("yes".equals(nextRoundInput)) {
        System.out.println("Alright! Lets do this.");
      } else {
        continuePlaying = false;
      }
    }
    System.out.println("*****");
    System.out.println("All done");
  }

  private static int findRandomNumber(int max, int min) {
    Random random = new Random();

    // target: allowed 1 - 100
    // nextInt(100) = 0 - 99
    // the lower limit adds 1
    // making it 1 - 100
    return random.nextInt(max) + min;
  }
}


