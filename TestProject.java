import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TestProject {
    private List<String> fileList;
    private Random random;

    public TestProject() {
        fileList = new ArrayList<>();
        random = new Random();
    }

    // Method to add files to the list
    public void addFile(String filePath) {
        fileList.add(filePath);
    }

    // Method to open a random file from the list

    public void openRandomFile() {
        if (fileList.isEmpty()) {
            System.out.println("No files added.");
            return;
        }

        int index = random.nextInt(fileList.size());
        String randomFile = fileList.get(index);

        try {
            File file = new File(randomFile);
            Scanner fileScanner = new Scanner(file);

            // Create Scanner for user input outside the loop
            Scanner userInputScanner = new Scanner(System.in);
            int totalCorrectAnswers = 0; // Counter for total correct answers
            while (fileScanner.hasNextLine()) {
                StringBuilder question = new StringBuilder();
                String line;
                // Read question until blank line or end of file
                while (fileScanner.hasNextLine() && !(line = fileScanner.nextLine()).trim().isEmpty()) {
                    question.append(line).append("\n");
                }

                if (question.length() > 0) {
                    // Print the question if it's not empty
                    System.out.println("Question: " + question);

                    // Read the answer from the file
                    String answer = fileScanner.nextLine();

                    // Prompt the user for their answer
                    System.out.print("Your answer: ");
                    String userAnswer = userInputScanner.nextLine();

                    // Check if the user's answer matches the correct answer
                    if (userAnswer.equalsIgnoreCase(answer)) {
                        System.out.println("Correct!");
                        totalCorrectAnswers++; // Increment total correct answers
                    } else {
                        System.out.println("Incorrect. The correct answer is: " + answer);
                    }
                }
            }
            // Print total number of correct answers
            System.out.println("Total correct answers: " + totalCorrectAnswers);
            // Close the scanners
            fileScanner.close();
            userInputScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
class Main {
    public static void main(String[] args) {
        // Create an instance of FileHandler
        TestProject fileHandler = new TestProject();

        // Adding 5 files to the list
        fileHandler.addFile("D:\\FileHandling\\java1.txt");
        fileHandler.addFile("D:\\FileHandling\\java2.txt");
        fileHandler.addFile("D:\\FileHandling\\java3.txt");
        fileHandler.addFile("D:\\FileHandling\\java4.txt");
        fileHandler.addFile("D:\\FileHandling\\java5.txt");
        fileHandler.addFile("D:\\FileHandling\\java6.txt");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main program loop
        while (running) {
            // Prompt the user to press enter to open a random file or type 'exit' to quit
            System.out.println("enter your name to get the questions related to java or type 'exit' to quit:");
            // Read user input
            String input = scanner.nextLine();
            System.out.println("you should write the yes for the asking about the level.(all the best)");
            System.out.println("answer format:Answer: option) answer");
            // Check if the user wants to exit
            if (input.equalsIgnoreCase("exit")) {
                running = false; // Set running to false to exit the loop
            } else {
                // If user does not want to exit, open a random file
                fileHandler.openRandomFile();
            }
        }
        // Close the scanner
        scanner.close();
    }
}


