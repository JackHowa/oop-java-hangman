import java.util.ArrayList;

/**
 * @author jackhowa
 */
class Game {
  private String targetSolution;
  private ArrayList<String> runningSolution;
  private int guessNumber;

  int getGuessNumber() {
    return guessNumber;
  }

  ArrayList<String> getRunningSolution() {
    return runningSolution;
  }

  private void setRunningSolution(ArrayList<String> runningSolution) {
    this.runningSolution = runningSolution;
  }

  void oneLessGuess() {
    this.guessNumber = this.guessNumber - 1;
  }

  Game(String targetSolution) {
    this.runningSolution = fillSolution(targetSolution.length());
    this.guessNumber = 5;
    this.targetSolution = targetSolution;
  }

  private String getTargetSolution() {
    return targetSolution;
  }

  boolean isWinner() {
    String joinedRunningSolution = "";

    ArrayList<String> runningSolution = this.getRunningSolution();

    for (int i = 0; i < this.getRunningSolution().size(); i++) {
      joinedRunningSolution = joinedRunningSolution.concat(runningSolution.get(i));
    }

    return joinedRunningSolution.equals(this.getTargetSolution());
  }

  private static ArrayList<String> fillSolution(int length) {
    ArrayList<String> runningSolution = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      runningSolution.add("");
    }
    return runningSolution;
  }

  private void updateRunningSolution(int targetIndex) {
    String correctLetter = this.targetSolution.substring(targetIndex, targetIndex + 1);
    ArrayList<String> newRunningSolution = this.runningSolution;
    newRunningSolution.set(targetIndex, correctLetter);
    this.setRunningSolution(newRunningSolution);
  }

  boolean handleGuess(String letterGuess) {
    boolean anyMatches = false;

    int targetIndex = this.targetSolution.indexOf(letterGuess);

    if (targetIndex >= 0) {
      anyMatches = true;
      updateRunningSolution(targetIndex);

      while (targetIndex > -1) {
        targetIndex = this.targetSolution.indexOf(letterGuess, targetIndex + 1);

        if (targetIndex > 0) {
          updateRunningSolution(targetIndex);
        }
      }
    }

    return anyMatches;
  }
}
