// File: QuizApp.java
import java.util.*;

public class  QuizApp {
    static class Question {
        String questionText;
        String[] options;
        int correctOption; // Index of the correct option

        Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();
        Timer timer = new Timer();
        int totalScore = 0;
        Map<Integer, Boolean> answerSummary = new HashMap<>();

        // Add quiz questions
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"1. Charles Dickens", "2. Jane Austen", "3. William Shakespeare", "4. Mark Twain"}, 3));

        System.out.println("Welcome to the Quiz App!");
        System.out.println("You have 10 seconds to answer each question. Let's begin!");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.questionText);
            for (String option : q.options) {
                System.out.println(option);
            }

            // Timer setup
            boolean[] timeout = {false};
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeout[0] = true;
                    System.out.println("\nTime's up!");
                }
            }, 10000); // 10 seconds

            // Get user answer
            int userAnswer = 0;
            while (!timeout[0]) {
                System.out.print("Enter your answer (1-4): ");
                try {
                    userAnswer = Integer.parseInt(scanner.nextLine());
                    if (userAnswer >= 1 && userAnswer <= 4) break;
                    else System.out.println("Invalid option. Please enter a number between 1 and 4.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            }

            timer.cancel(); // Stop the timer
            timer = new Timer(); // Reset timer for next question

            // Check answer and update score
            if (!timeout[0] && userAnswer == q.correctOption) {
                System.out.println("Correct!");
                totalScore++;
                answerSummary.put(i + 1, true);
            } else {
                System.out.println("Wrong! The correct answer was: " + q.correctOption + ". " + q.options[q.correctOption - 1]);
                answerSummary.put(i + 1, false);
            }
        }

        // Display result summary
        System.out.println("\n----- Quiz Over -----");
        System.out.println("Your Total Score: " + totalScore + "/" + questions.size());
        System.out.println("Summary:");
        for (int i = 1; i <= questions.size(); i++) {
            System.out.println("Question " + i + ": " + (answerSummary.get(i) ? "Correct" : "Incorrect"));
        }

        scanner.close();
    }
}
