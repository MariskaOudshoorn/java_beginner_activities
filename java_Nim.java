import java.util.Scanner;
import java.util.Random;

public class java_Nim{
	
	public static int matches = 11;
	public int playerInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of matches you want to take.");
		int number = input.nextInt();
		return number;
	}

	public int matchesRemaining(int number){
		while( number > matches || number > 4 || number == 0){
			System.out.println("Take a different amount of matches.");
			number = playerInput();
		}
		matches = matches - number;
		System.out.println("There are " + matches + " remaining.");
		System.out.println(" ");
		return matches;
	}

	public int computerInput(){
		Random rand = new Random();
		int number = 1;
		if(matches <= 5){
			number = matches - 1;
		}
		else if(matches == 10){
			number = 4;
		}
		else if(matches == 9){
			number = 3;
		}
		else if(matches == 8){
			number = 2;
		}
		else{
			number = 1;
		}	
		System.out.println("The computer takes " + number + " match(es).");
		return number;
	}

	public void playerVsComputer(){
		boolean playerturn = true;
		while(matches > 0){
			playerturn = true;
			int number = playerInput();
			matches = matchesRemaining(number);
			if(matches > 0){
				playerturn = false;
				if(matches == 1){
					matches --;
				}
				else{
					int compunumber = computerInput();
					matches = matchesRemaining(compunumber);
				}
			}
		}
		if(playerturn){
			System.out.println("You lose.");
		}
		else{
			System.out.println("Congratulations you win.");
		}
	}

	public void playerVsPlayer(){
		int turn = 1;
		while(matches >= 1){
			if(turn == 1){
				int number = playerInput();
				matches = matchesRemaining(number);
				turn = 2;
			}
			if(turn == 2 && matches > 0){
				int number = playerInput();
				matches = matchesRemaining(number);
				turn = 1;
			} 
		}
		if(turn == 1){
			System.out.println("Player 1 wins.");
		}
		else{
			System.out.println("Player 2 wins.");
		}
	}

	public void playGame(){
		boolean playing = true;
		while(playing){
			System.out.println("Want to play a game of nim? 1.Against the computer 2.A two player game 3.Exit.");
			System.out.println("Choose a number:");
			Scanner input = new Scanner(System.in);
			int number = input.nextInt();
			System.out.println();
			switch(number){
				case 1: playerVsComputer();
					break;
				case 2: playerVsPlayer();
					break;
				case 3: playing = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		java_Nim nim = new java_Nim();
		nim.playGame();
	}
}