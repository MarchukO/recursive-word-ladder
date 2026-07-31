import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class WordLadderApp {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        WordDictionary dictionary;

        try {
            dictionary = new WordDictionary("words_small.txt");
        } catch (IOException exception) {
            System.out.println(
                    "Error: Unable to open words_small.txt.");
            keyboard.close();
            return;
        }

        System.out.print("Enter the starting word: ");
        String startWord = keyboard.nextLine().trim().toLowerCase();
        System.out.print("Enter the ending word: ");
        String endWord = keyboard.nextLine().trim().toLowerCase();

        if (startWord.isEmpty()) {
            System.out.println("The starting word cannot be empty.");
            keyboard.close();
            return;
        }
        if (endWord.isEmpty()) {
            System.out.println("The ending word cannot be empty.");
            keyboard.close();
            return;
        }
        if (startWord.length() != endWord.length()) {
            System.out.println(
                    "The starting and ending words must have the same length.");
            keyboard.close();
            return;
        }
        if (!dictionary.contains(startWord)) {
            System.out.println(
                    "The starting word does not exist in the dictionary.");
            keyboard.close();
            return;
        }
        if (!dictionary.contains(endWord)) {
            System.out.println(
                    "The ending word does not exist in the dictionary.");
            keyboard.close();
            return;
        }

        WordLadderSolver solver = new WordLadderSolver(dictionary);
        ArrayList<String> result =solver.findLadder(startWord, endWord);

        if (result.isEmpty()) {
            System.out.println();
            System.out.println("No word ladder could be found.");
        } else {
            System.out.println();
            System.out.println("Word ladder found:");
            System.out.println();

            for (String word : result) {
                System.out.println(word);
            }
        }
        keyboard.close();
    }
}
