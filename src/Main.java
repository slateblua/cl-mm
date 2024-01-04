import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int MAX_GUESSES = 12;
    public static void main(String[] args) {
        masterMind();
    }

    public static void masterMind() {

        String sequence = generateSequence();
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Mastermind!");
        System.out.println("Mastermind kept a secret sequence, try to guess it in under " + MAX_GUESSES + " steps!");

        for (int i = 0; i < MAX_GUESSES; i++) {
            String guess = scan.nextLine();
            int[] respond = makeGuess(sequence, guess);
            if (guess.equals(sequence)) {
                handleWin(scan);
            }
            else {
                System.out.println(Arrays.toString(respond));
                System.out.println("Guesses remained: " + (MAX_GUESSES - i - 1));
            }
        }
        handleLoss(scan);
    }

    private static void handleWin(Scanner scanner) {
        System.out.println("You won!");
        System.out.println("Would you like to play again? 1/0");

        int response = scanner.nextInt();
        if (response == 1) {
            masterMind();
        } else {
            System.exit(0);
        }
    }
    private static void handleLoss(Scanner scanner) {
        System.out.println("You lost :(");
        System.out.println("Would you like to play again? 1/0");

        int response = scanner.nextInt();
        if (response == 1) {
            masterMind();
        } else {
            System.exit(0);
        }
    }
    private static int[] makeGuess(String sequence, String guess) {
        int[] respond = new int[4];
        char[] sequenceArray = sequence.toCharArray();
        char[] guessArray = guess.toCharArray();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (guessArray[i] == sequenceArray[j]) {
                    respond[i] = i == j ? 9 : 8;
                }
            }
        }
        return respond;
    }

    private static String generateSequence() {
        StringBuilder sequence = new StringBuilder();
        while (sequence.length() < 4) {
            int nextColor = (int) (Math.random() * 6) + 1;
            if (sequence.indexOf(String.valueOf(nextColor)) == -1) {                // Ensures that there are only unique colors
                sequence.append(nextColor);
            }
        }
        return sequence.toString();
    }
}