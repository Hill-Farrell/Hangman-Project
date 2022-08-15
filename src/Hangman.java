import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {
    public static void startHangman(){
        try {
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
            System.out.println("✩ Welcome to Hangman! Enter letters when prompted ✩ Press 4 to guess the word! ✩");
            while(true){

                printHangedMan(wrongCount);

                if(wrongCount >= 6){
                    System.out.println("YOU LOSE!");
                    break;
                }
                printWordState(word, playerGuesses);

                if(!getPlayerGuess(userInput,word,playerGuesses,wrongCount)){
                    wrongCount++;
                }
                if(printWordState(word, playerGuesses)){
                    System.out.println("YOU WIN!!");
                    break;
                }
            }
        } catch (FileNotFoundException fnf){
            fnf.printStackTrace();
        }
    }

    // Drawing Hangman
    private static void printHangedMan(int wrongCount) {
        System.out.println(" _______");
        System.out.println(" |     ");
        if (wrongCount >= 1){
            System.out.println(" O");
        }
        if (wrongCount >= 2){
            System.out.print("\\ ");
            if(wrongCount >= 3){
                System.out.println("/");
            }
            else {
                System.out.println(" ");
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
                System.out.println(" ");
            }
        }
        System.out.println(" ");
        System.out.println(" ");
    }


    private static void wordGuess(Scanner userInput, String word, List<Character> playerGuesses,int wrongCount){
        System.out.println("Please enter your guess for the word: ");
        if(userInput.nextLine().toUpperCase().equals(word)){
            System.out.println("YOU WIN!!");
            System.exit(0);
        }
        else {
            System.out.println("Incorrect. Try again.");
        }
        printHangedMan(wrongCount);
        printWordState(word, playerGuesses);
        getPlayerGuess(userInput,word,playerGuesses,wrongCount);

    }

    // Take user input return only first letter if there are multiple inputs. Also makes input capital letters for consistency
    // Also checks if user presses "4" to start word guess
    private static boolean getPlayerGuess(Scanner userInput, String word, List<Character> playerGuesses,int wrongCount) {
        System.out.println("Please enter a letter: ");
        String userGuess = userInput.nextLine();
        String userGuessCap = userGuess.toUpperCase();

        if(userGuess.equals("4")) {
            wordGuess(userInput, word,playerGuesses,wrongCount);
        }
        else {
            playerGuesses.add(userGuessCap.charAt(0)); // only takes 1st letter of guess
        }
        return (word.contains(userGuessCap));

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
