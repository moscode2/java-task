// File: GuessTheNumberGame.java
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        System.out.println("Welcome to Moises the Guess the Number Game!");

        while (playAgain) {
            // Configuration for the round
            int lowerBound = 1;
            int upperBound = 100;
            int maxAttempts = 5;
            int attemptsLeft = maxAttempts;

            // Generate a random number
            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            System.out.printf("I'm thinking of a number between %d and %d.%n", lowerBound, upperBound);
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            boolean guessedCorrectly = false;

            while (attemptsLeft > 0) {
                System.out.printf("Attempts left: %d. Enter your guess: ", attemptsLeft);
                int userGuess;

                try {
                    userGuess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                }

                // Compare the user's guess
                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number!");
                    guessedCorrectly = true;
                    totalScore += maxAttempts - attemptsLeft + 1; // Higher score for fewer attempts
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Your guess is too low.");
                }

                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.printf("Out of attempts! The correct number was: %d%n", numberToGuess);
            }

            // Ask if the user wants to play another round
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");
        }

        // End of game
        System.out.printf("Game over! Your total score is: %d%n", totalScore);
        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
