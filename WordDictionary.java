import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class WordDictionary {
    private ArrayList<String> words;
    private HashSet<String> wordLookup;
    public WordDictionary(String fileName) throws IOException {
        words = new ArrayList<String>();
        wordLookup = new HashSet<String>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String word = line.trim().toLowerCase();

            if (!word.isEmpty() && !wordLookup.contains(word)) {
                words.add(word);
                wordLookup.add(word);
            }
        }

        reader.close();
    }
    public boolean contains(String word) {
        if (word == null) {
            return false;
        }
        return wordLookup.contains(word.trim().toLowerCase());
    }
    public ArrayList<String> getWordsOfLength(int length) {
        ArrayList<String> matchingWords =new ArrayList<String>();

        for (String word : words) {
            if (word.length() == length) {
                matchingWords.add(word);
            }
        }
        return matchingWords;
    }
}
