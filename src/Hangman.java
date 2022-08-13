import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("./words.txt"));
        Scanner userInput = new Scanner(System.in);

        List<String> words = new ArrayList<>();

        while (scanner.hasNext()){
            words.add(scanner.nextLine()); // Placing all words from file into an ArrayList
        }

        Random random = new Random();

        String word = words.get(random.nextInt(words.size())); // Choose random word from ArrayList

        System.out.println(word); // DELETE LATER

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while(true){
            printHangedMan(wrongCount);

            if(wrongCount >= 6){
                System.out.println("YOU LOSE!");
                break;
            }

            printWordState(word, playerGuesses);
            if(!getPlayerGuess(userInput,word,playerGuesses)){
                wrongCount++;
            }
            if(printWordState(word, playerGuesses)){
                System.out.println("YOU WIN!!");
                break;
            }
            System.out.println("Please enter your guess for the word: ");
            if(userInput.nextLine().equals(word)){
                System.out.println("YOU WIN!!");
                break;
            }
            else {
                System.out.println("Incorrect. Try again.");
            }
        }



    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if (wrongCount >= 1){
            System.out.println(" O");
        }
        if (wrongCount >= 2){
            System.out.print("\\ ");
            if(wrongCount >= 3){
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }
        if (wrongCount >= 4){
            System.out.println(" |");
        }
        if (wrongCount >= 5){
            System.out.print("/ ");
            if(wrongCount >= 6){
                System.out.println("\\");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner userInput, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter: ");
        String userGuess = userInput.nextLine();
        //String capUserGuess = userGuess.toUpperCase();
        playerGuesses.add(userGuess.charAt(0)); // only takes 1st letter of guess

        return (word.contains(userGuess));
    }


    private static boolean printWordState(String word, List<Character> playerGuesses){
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print(" _ ");
            }
        }
        System.out.println();

        return(word.length() == correctCount);
    }
}
