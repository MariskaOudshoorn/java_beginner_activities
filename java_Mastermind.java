import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class java_Mastermind{

	public static char[] toGuess = new char[4];
	public static char[] playerInput = new char[4];
	//public static char[] returnPins = new char[4];
	public static String choices = "RGBYWO";

	public void makeSequence(){
		Random rand = new Random();
		for(int i = 0; i < 4; i++){
			toGuess[i] = choices.charAt(rand.nextInt(choices.length()));
		}
		System.out.println(toGuess); 
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
					System.out.println("That was an invalid character, please enter a four letter code with RGBYWO");
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

	public void placeWhite(int location, char[] returnPins){
		int counter = 0;
		if(returnPins[counter] == '-'){
			returnPins[counter] = 'W';
		}
		else{
			counter++;
		}	
	}

	public void checkLocation(){
		char[] returnPins = {'-', '-', '-', '-'};
		for(int i = 0; i < 4; i++){
			if(toGuess.indexOf(playerInput[i]) > 0){
				//problem with indexOf
				placeWhite(i, returnPins);
			}
			else if(toGuess[i] == playerInput[i]){
				returnPins[i] = 'B';
			}
			else{
				//komt niet voor
			}
			System.out.println(returnPins);
		}
	}


	public static void main(String[] args) {
		java_Mastermind mastermind = new java_Mastermind();
		mastermind.makeSequence();
		mastermind.playerInputs();
		mastermind.checkLocation();


	}
}