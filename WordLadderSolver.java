import java.util.ArrayList;
import java.util.HashSet;
public class WordLadderSolver {

    private WordDictionary dictionary;
    private HashSet<String> visited;
    private ArrayList<String> ladder;
    public WordLadderSolver(WordDictionary dictionary) {
        this.dictionary = dictionary;
        visited = new HashSet<String>();
        ladder = new ArrayList<String>();
    }

    public ArrayList<String> findLadder(
            String startWord, String endWord) {
        visited.clear();
        ladder.clear();
        if (startWord == null || endWord == null) {
            return new ArrayList<String>();
        }

        startWord = startWord.trim().toUpperCase();
        endWord = endWord.trim().toUpperCase();

        ArrayList<String> candidates = dictionary.getWordsOfLength(startWord.length());
        boolean found = search(startWord, endWord, candidates);

        if (found) {
            return new ArrayList<String>(ladder);
        }
        return new ArrayList<String>();
    }
    private boolean search(
            String currentWord,
            String endWord,
            ArrayList<String> candidates) {

        ladder.add(currentWord);
        visited.add(currentWord);
        if (currentWord.equals(endWord)) {
            return true;
        }
        for (String candidate : candidates) {

            if (!visited.contains(candidate)
                    && WordUtils.differsByOneLetter(
                            currentWord, candidate)) {
              
                if (search(candidate, endWord, candidates)) {
                    return true;
                }
            }
        }
        ladder.remove(ladder.size() - 1);
        return false;
    }
}
