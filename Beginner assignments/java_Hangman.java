import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class java_Hangman{

	public static char[] toGuessWord;
	public static char[] toRememberWord = {'-','-','-','-','-','-'};
	public String generateWord(){
		Random rand = new Random();
		String[] wordlist = new String[]{"object", "margin", "ticket", "sunday", "course", "indoor", "sketch", "basket"};
		int arrayLength = wordlist.length -1;
		int chooseword = rand.nextInt(arrayLength);
		return wordlist[chooseword];
	}

	public void checkLetter(String word){
		Scanner input = new Scanner(System.in);
		System.out.println("Choose a letter");
		char check = input.next().charAt(0);
		for(int i = 0; i <= 5; i++){
			if(toGuessWord[i] == check){
				toRememberWord[i] = toGuessWord[i];
				System.out.println("yay");
				System.out.println(toRememberWord);
				return;
			}
		}
		System.out.println("nay");
	}

	public void compareWords(){
		if( toGuessWord == toRememberWord){
			System.out.println("yay, you win.");
		}
	}

	public void playGame(){
		String word = generateWord();
		System.out.println("the chosen word is " + word);
		toGuessWord = word.toCharArray(); 
		checkLetter(word);
	}

	public static void main(String []args){
		java_Hangman hangman = new java_Hangman();
		hangman.playGame();
		
		
	}
}