# recursive-word-ladder

## 1. Student Information

- **Student:** Oleksandr Marchuk
- **Course:** CIS-287: Object-Oriented Programming, Summer U 2026
- **Assignment:** Programming Assignment: Recursive Word Ladder

## 2. Program Description

This Java program finds a word ladder between a starting word and an ending word.

A word ladder is a sequence of words where:

- Every word has the same length.
- Each word differs from the previous word by exactly one letter.
- Every word exists in the provided `words_small.txt` dictionary file.

The program reads the dictionary file when it starts. It asks the user to enter a starting word and an ending word. The input is trimmed and converted to lowercase before it is validated.

The program checks that both words:

- Are not empty.
- Have the same length.
- Exist in the dictionary.

The program uses recursive depth-first search with backtracking to search for a valid ladder. A `HashSet` stores visited words and prevents the search from repeatedly visiting the same words.

The program does not need to find the shortest ladder. It displays any valid ladder that it finds. If no ladder exists, it displays an appropriate message.

## 3. Class Descriptions

### WordLadderApp

`WordLadderApp` is the driver class and contains the `main` method.

Its responsibilities are:

- Create the required program objects.
- Create a `WordDictionary` using `words_small.txt`.
- Handle an error if the dictionary file cannot be opened.
- Ask the user for a starting word and an ending word.
- Remove extra spaces with `trim()`.
- Convert input to lowercase.
- Validate the user input.
- Create a `WordLadderSolver`.
- Call the solver.
- Display the completed ladder or an error message.
- Close the `Scanner`.

### WordDictionary

`WordDictionary` loads and manages the words from `words_small.txt`.

It stores the words in two private collections:

- An `ArrayList<String>` stores the dictionary words.
- A `HashSet<String>` provides fast word lookup and prevents duplicate dictionary entries.

Its constructor reads the file one line at a time. Each word is trimmed and converted to lowercase.

Its public methods are:

- `contains(String word)` checks whether a word exists in the dictionary.
- `getWordsOfLength(int length)` returns an `ArrayList` containing only words of the requested length.

### WordLadderSolver

`WordLadderSolver` performs the recursive word-ladder search.

It contains:

- A private `WordDictionary` object.
- A private `HashSet<String>` named `visited`.
- A private `ArrayList<String>` named `ladder`.

The constructor receives a `WordDictionary` object.

The `findLadder` method:

- Clears previous search information.
- Normalizes the starting and ending words.
- Gets dictionary words of the correct length.
- Starts the recursive search.
- Returns a completed ladder when one is found.
- Returns an empty list when no ladder exists.

The private `search` method performs the recursive depth-first search and backtracking.

### WordUtils

`WordUtils` contains the static utility method:

```java
public static boolean differsByOneLetter(
        String firstWord, String secondWord)
```

The method returns `true` only when:

- Both words are not `null`.
- Both words have the same length.
- The words differ in exactly one character position.

The method returns `false` when the words are identical, have different lengths, or differ in more than one position.

## 4. Compilation Instructions

Keep the following files in the same folder:

```text
WordLadderApp.java
WordDictionary.java
WordLadderSolver.java
WordUtils.java
words_small.txt
README.md
```

Compile all Java source files with:

```bash
javac *.java
```

The program uses only standard Java libraries. It does not require external libraries or a package declaration.

## 5. Execution Instructions

After compiling the project, run the program with:

```bash
java WordLadderApp
```

The file `words_small.txt` must be in the same folder as the Java source files.

The program will ask for:

1. A starting word.
2. An ending word.

Both words can be entered with uppercase or lowercase letters. Leading and trailing spaces are removed automatically.

## 6. Test Results

The program was compiled and tested in OnlineGDB.

### Test 1: Successful Word Ladder

**Input:**

```text
fish
mast
```

**Output produced:**

```text
Enter the starting word: fish
Enter the ending word: mast

Word ladder found:

fish
dish
dash
cash
case
care
card
cart
cast
fast
last
list
lost
most
mast
```

The result is valid because every neighboring pair differs by exactly one letter, all words have four letters, and all words exist in `words_small.txt`.

### Test 2: No Ladder Exists

**Input:**

```text
stone
peach
```

**Output produced:**

```text
Enter the starting word: stone
Enter the ending word: peach

No word ladder could be found.
```

Both words are valid five-letter dictionary words, but the program cannot find a valid path between them. The recursive search finishes without crashing or entering an infinite loop.

### Test 3: Words of Different Lengths

**Input:**

```text
fish
stone
```

**Output produced:**

```text
Enter the starting word: fish
Enter the ending word: stone
The starting and ending words must have the same length.
```

The program stops before starting the recursive search.

### Test 4: Ending Word Not in the Dictionary

**Input:**

```text
fish
zzzz
```

**Output produced:**

```text
Enter the starting word: fish
Enter the ending word: zzzz
The ending word does not exist in the dictionary.
```

The program correctly rejects an ending word that is not in `words_small.txt`.

### Test 5: Empty Starting Word

**Input:**

```text

mast
```

**Output produced:**

```text
Enter the starting word:
Enter the ending word: mast
The starting word cannot be empty.
```

The program handles empty input without crashing.

### Additional Tests

The program was also tested with:

- `cold` to `warm`
- `lead` to `gold`
- `hit` to `cog`
- `stone` to `clone`
- The same starting and ending word
- An invalid starting word
- An empty ending word
- Uppercase input
- Mixed-case input
- Extra spaces before and after input
- A missing dictionary file

The missing-file test produced:

```text
Error: Unable to open words_small.txt.
```

## 7. Recursion Explanation

The recursive method is the private `search` method in `WordLadderSolver`.

The recursive call occurs here:

```java
if (search(candidate, endWord, candidates)) {
    return true;
}
```

The method calls itself using a valid neighboring word as the new current word.

The base case is:

```java
if (currentWord.equals(endWord)) {
    return true;
}
```

The base case is reached when the current word is equal to the ending word. At that point, the ladder is complete and the method returns `true`.

The search progresses toward the base case by examining candidate words. A candidate can be visited only when:

- It has not already been visited.
- It differs from the current word by exactly one letter.
- It has the correct length because the candidate list was filtered before the search began.

Each recursive call moves to another valid neighboring word. The search continues until it reaches the ending word or runs out of possible paths.

## 8. Backtracking Explanation

Backtracking occurs when the recursive search follows a possible path but cannot reach the ending word from that path.

When the method enters a word, it adds the word to the current ladder:

```java
ladder.add(currentWord);
```

The method then recursively tries every valid unvisited neighboring word.

If none of those choices leads to the ending word, the current word is removed from the ladder:

```java
ladder.remove(ladder.size() - 1);
```

Removing the word restores the ladder to its previous state. The method then returns `false` to the previous recursive call.

The previous call continues its loop and tries another candidate word. This allows the program to return from a failed path and explore a different possible path.

## 9. Visited Words Explanation

The `visited` collection is a `HashSet<String>` that tracks words already explored during the current search.

Before the program recursively visits a candidate, it checks:

```java
!visited.contains(candidate)
```

When a word is visited, it is added to the set:

```java
visited.add(currentWord);
```

This prevents cycles. For example, without `visited`, the program could repeatedly move between words such as:

```text
cold -> cord -> cold -> cord
```

That could cause unnecessary repeated work, infinite recursion, or a stack overflow.

The `visited` collection is cleared at the beginning of every new call to `findLadder`, so a new search starts with no previously visited words.

A `HashSet` is appropriate because it prevents duplicate entries and provides fast checks for whether a word has already been visited.

## 10. Object-Oriented Design Explanation

### Encapsulation

Encapsulation is demonstrated by declaring the instance variables in `WordDictionary` and `WordLadderSolver` as `private`.

For example:

```java
private ArrayList<String> words;
private HashSet<String> wordLookup;
```

and:

```java
private WordDictionary dictionary;
private HashSet<String> visited;
private ArrayList<String> ladder;
```

Other classes do not directly modify these fields. They communicate through constructors and public methods.

### Composition

Composition is demonstrated because `WordLadderSolver` contains a reference to a `WordDictionary` object.

The dictionary is passed to the solver through its constructor:

```java
WordLadderSolver solver =
        new WordLadderSolver(dictionary);
```

The solver uses the dictionary but does not take over the dictionary's responsibility of reading the file.

### Separation of Responsibilities

Each class has a separate purpose:

- `WordLadderApp` controls the application and handles user interaction.
- `WordDictionary` loads and manages dictionary words.
- `WordLadderSolver` performs the recursive search and backtracking.
- `WordUtils` compares two words.

This design prevents all program responsibilities from being placed in one large class.

### Object Communication

The objects communicate through constructors, method parameters, and return values.

`WordLadderApp` creates a `WordDictionary` object and passes it to the `WordLadderSolver` constructor.

The application calls:

```java
dictionary.contains(startWord)
```

and:

```java
solver.findLadder(startWord, endWord)
```

The solver calls:

```java
dictionary.getWordsOfLength(startWord.length())
```

and uses:

```java
WordUtils.differsByOneLetter(currentWord, candidate)
```

The solver returns an `ArrayList<String>` to the application, and the application displays the result.

## 11. Gemini Use and Reflection

### How Gemini Was Used

I used Google Gemini to help me understand how a `HashSet` named `visited` prevents cycles during a recursive search.

I also used Gemini to suggest test cases for the program, including successful ladders, invalid words, words of different lengths, empty input, and situations where no ladder exists.

I reviewed Gemini's suggestions and compared them with the assignment requirements and the provided `words_small.txt` file before using them.

I also used ChatGPT to review portions of the code, organize the GitHub repository, and prepare the documentation. I reviewed, tested, and verified the final program before submission.

### Prompts Submitted to Gemini

#### Prompt 1

> Explain in simple terms how the HashSet named visited prevents cycles in my Java Word Ladder program.

#### Prompt 2

> Suggest test cases for my Java Word Ladder program, including invalid words, words of different lengths, empty input, and a case where no ladder exists.

### Summary of Gemini Assistance

Gemini explained that `visited` acts like a record of words that the search has already explored.

Before the program moves to another word, it checks whether that word is already in the `HashSet`. If the word is already present, the program skips it. This prevents the recursive search from moving repeatedly between the same words.

Gemini also suggested testing:

- Empty starting and ending input
- Words of different lengths
- Starting or ending words that are not in the dictionary
- The same starting and ending word
- A one-step ladder
- A multi-step ladder
- A case where no ladder exists

These suggestions helped create a more complete testing plan.

### Gemini Suggestions That I Changed or Rejected

Gemini suggested test words such as `CAT`, `DOG`, `ALOE`, `ZINC`, and a custom dictionary.

I did not use all of these examples because this assignment requires the provided `words_small.txt` dictionary. Some of Gemini's suggested words were not included in that file.

Instead, I selected test words that exist in the provided dictionary, including:

- `fish` and `mast`
- `cold` and `warm`
- `lead` and `gold`
- `hit` and `cog`
- `stone` and `clone`
- `stone` and `peach`

Gemini also suggested that invalid input could cause the program to throw an `IllegalArgumentException`.

I did not use that suggestion. I chose to display clear error messages and stop the program because this behavior more closely matches the assignment's example output and input-validation requirements.

Gemini described `visited` as recording words in the current path. In my final program, `visited` records words explored during the entire current search. It is cleared when a new search starts, but words are not removed from `visited` during backtracking. This prevents the program from exploring the same word repeatedly through different paths.

### How I Tested and Verified the Program

I compiled the complete project using:

```bash
javac *.java
```

I ran the program using:

```bash
java WordLadderApp
```

I tested successful ladders with three-letter, four-letter, and five-letter words.

I also tested:

- A valid multi-step ladder
- A one-step ladder
- The same starting and ending word
- Words of different lengths
- An invalid starting word
- An invalid ending word
- Empty input
- Input with extra spaces
- Uppercase and mixed-case input
- A valid pair for which no ladder exists
- A missing dictionary file

For every successful ladder, I verified that:

- The first word matched the starting word.
- The final word matched the ending word.
- Every word had the same length.
- Every neighboring pair differed by exactly one letter.
- Every word existed in `words_small.txt`.

For invalid and unsuccessful tests, I verified that the program displayed an appropriate message, did not crash, and did not enter an infinite loop.

### Recursive Method in My Own Words

The `search` method solves one part of the problem and then calls itself to continue from a neighboring word.

Each call adds its current word to the ladder and marks it as visited. It then checks the base case. If the current word is the ending word, the search is complete.

Otherwise, the method checks possible neighboring words. When it finds an unvisited word that differs by exactly one letter, it calls itself with that word.

If that recursive path succeeds, `true` is returned through all previous calls. If the path fails, the word is removed from the ladder, and the program returns to an earlier call to try another candidate.

### Object-Oriented Design in My Own Words

The program uses object-oriented design by dividing the work among separate classes.

`WordLadderApp` handles the user and controls the program. `WordDictionary` is responsible for dictionary data. `WordLadderSolver` is responsible for searching. `WordUtils` contains a reusable comparison method.

The classes protect their internal data with private fields. They communicate by using constructors and methods instead of directly changing another class's private data.

Composition is used because the solver receives and stores a `WordDictionary` object. This allows the solver to use dictionary information while keeping file loading inside the dictionary class.

I reviewed the final code, tested its behavior, and verified that I could explain the responsibility of each class, the recursive call, the base case, backtracking, the visited collection, input validation, and file handling.
