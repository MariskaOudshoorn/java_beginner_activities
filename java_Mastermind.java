import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class java_Mastermind{

	public static char[] toGuess = new char[4];
	public static char[] playerInput = new char[4];
	public static String choices = "RGBYWO";

	public void makeSequence(){
		Random rand = new Random();
		for(int i = 0; i < 4; i++){
			toGuess[i] = choices.charAt(rand.nextInt(choices.length()));
		} 
	}

	public void playerInputs(){
		Scanner input = new Scanner(System.in);
		String sequence;
		do{
			System.out.println("Guess a code of four characters, consisting of RGBYWO:");
			sequence = input.nextLine();
			playerInput = sequence.toCharArray();
			for(int i = 0; i < 4; i++){
				while(choices.indexOf(playerInput[i]) < 0){
					System.out.println("That was an invalid guess, please enter a four letter code with RGBYWO");
					sequence = input.nextLine();
					playerInput = sequence.toCharArray();
				}
			}
		}while(sequence.length() != 4);
	}

	public boolean checkForWin(){
		boolean equal = Arrays.equals(toGuess, playerInput);
		return equal;
	}

	public void placeReturnPins(char[] returnPins, char colour, char[] savedLetters, int i){
		int counter = 0;
		boolean placed = false;
		while(!placed){
			if(returnPins[counter] == '-'){
				returnPins[counter] = colour;
				placed = true;
			}
			else if((returnPins[counter] == 'W') && (savedLetters[i] == 'X') && (colour == 'B')){
				returnPins[counter] = colour;
				placed = true;
			}
			else{
				counter++;
			}
		}	
	}

	public boolean checkContains(char location, char[]savedLetters){
		for(int i = 0; i< 4; i++){
			if(location == toGuess[i] && (savedLetters[i] != 'X')){
					savedLetters[i] = 'X';
					return true;
			}
		}
		return false;
	}

	public void checkLocation(){
		char[] returnPins = {'-', '-', '-', '-'};
		char[] savedLetters ={'-', '-', '-', '-'} ;
		int savedLettersCounter = 0;
		for(int i = 0; i < 4; i++){
			if(toGuess[i] == playerInput[i]){
				placeReturnPins(returnPins, 'B', savedLetters, i);
				savedLetters[i] = 'X';
			}
			else if(checkContains(playerInput[i], savedLetters)){
				placeReturnPins(returnPins, 'W', savedLetters, i);
			}

		}
		System.out.println(returnPins);
	}

	public boolean playAgain(){
		Scanner input = new Scanner(System.in);
		char answer = input.next().charAt(0);
		if(answer != 'y'){
			return false;
		}
		return true;
	}


	public void playGame(){
		boolean playing = true;
		while(playing){
			boolean turn = true;
			makeSequence();
			int turns = 10;
			while(turn){
				playerInputs();
				checkLocation();
				boolean winning = checkForWin();
				if(winning){
					System.out.println("You won! Want to play again? y/n");
					playing = playAgain();
					turn = false;
				}
				else{
					turns--;
					System.out.println("You have " + turns + " guesses left.");
					if(turns <= 0){
						System.out.println("you lost! Want to play again? y/n");
						playing = playAgain();
						turn = false;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		java_Mastermind mastermind = new java_Mastermind();
		mastermind.playGame();
	}
}
