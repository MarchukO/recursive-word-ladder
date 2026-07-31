public class WordUtils {
    public static boolean differsByOneLetter(
            String firstWord, String secondWord) {

        if (firstWord == null || secondWord == null) {
            return false;
        }
        if (firstWord.length() != secondWord.length()) {
            return false;
        }

        int differences = 0;
        for (int index = 0; index < firstWord.length(); index++) {
            if (firstWord.charAt(index) != secondWord.charAt(index)) {
                differences++;

                if (differences > 1) {
                    return false;
                }
            }
        }
        return differences == 1;
    }
}
