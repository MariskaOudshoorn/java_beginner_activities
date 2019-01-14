import java.time.*;
import java.util.*;

public class java_Clock{
	public static int storei = 0;
	public static int[][] stored = new int[5][25];
	public static String[][] stringStored = new String[25][125];
	public static String[][] stringStored2 = new String[25][125];
	public static String[] stringStored3 = new String[125];
	
	public void chooseNumber(int number){
		int[][] numberArray = new int[5][5]; 
		switch(number){
			case 1: numberArray = makeOne();
				break;
			case 2: numberArray = makeTwo();
				break; 
			case 3: numberArray = makeThree();
				break; 
			case 4: numberArray = makeFour();
				break;
			case 5: numberArray = makeFive();
				break; 
			case 6: numberArray = makeSix();
				break;
			case 7: numberArray = makeSeven();
				break;
			case 8: numberArray = makeEight();
				break;
			case 9: numberArray = makeNine();
				break; 
			case 0: numberArray = makeZero();
				break; 
		}

		//storeNumber(numberArray);
		enlargeNumber(numberArray);
	}

	public void enlargeNumber(int[][] number){
		String[] symbols = new String[]{" ","_","|"};
		int size = 2;
		for(int j = 0; j < 5; j++){
			for(int i = 0; i < 5; i++){
				int symbolNumber = number[j][i];
				if((symbolNumber == 0) || (symbolNumber == 1)){
					for(int x = 0; x < size; x++){
						stringStored3[storei] = symbols[symbolNumber];
						storei++;
					}
				}
				else if( symbolNumber == 2){
					for(int x = 0; x-1 < size; x++){
						stringStored3[storei] = "  ";
						storei++;
					}
					stringStored3[storei] = " ";
					stringStored3[storei] = "|";
					storei += 2;
				}
			}
		}
		for(int i = 0; i < 50; i++){
			if(i % 10 == 0){
				System.out.println();
			}
			System.out.print(stringStored3[i]);
		}

	}

	/* public void storeNumber(int[][] array){
		String[] symbols = new String[]{" ","_","|"};
		for(int j = 0; j < 5; j++){
			for(int i = 0; i < 5; i++){
				int symbolNumber = array[j][i];
				if(i == 10){
					if((j == 2) || (j == 4)){
						stringStored[j][storei + i] = "-";
					}
					else{
						stringStored[j][storei + i] = " ";
					}
				}
				stringStored[j][storei + i] = symbols[symbolNumber];
				stored[j][storei + i] = array[j][i];
			}
		}
		storei += 5;
	}

	public void printNumber(int size){
		for(int j = 0; j < 5; j++){
			for(int i = 0; i < storei; i++){
				if((stringStored[j][i] == "_") || (stringStored[j][i] == " ")){
					for(int x = 0; x < size; x++){	
						stringStored2[j][i+x] = stringStored[j][i];
					}
				}
				if(stringStored[j][i] == "|"){
					for(int x = 0; x < size; x++){	
						stringStored2[j+x][i] = stringStored[j][i];
					}
				}
			}
			System.out.println();
		}
		for(int j = 0; j < 5*size; j++){
			for(int i = 0; i < storei; i++){
				System.out.print(stringStored2[j][i]);
			}
		}
	}

	public void printNumber(int[][] array, int size){
		String[] symbols = new String[]{" ","_","|"};
		if(size == 1){
			for(int j = 0; j < 5; j++){
				for(int i = 0; i < storei; i++){
					if(i == 10){
						if((j == 2) || (j == 4)){
							System.out.print("-");
						}
						else{
							System.out.print(" ");
						}
					}
					int symbolNr = array[j][i];
					System.out.print(symbols[symbolNr]);
				}
				System.out.println();
			}
		}
		else{
			for(int j = 0; j < 5; j++){
				if(j % 2  == 1){
					for(int i = 0; i < storei; i++){
						int symbolNr = array[j][i];
						if(symbolNr == 0){
							System.out.print(symbols[symbolNr]);
						}
						System.out.print(symbols[symbolNr]);
					}
					System.out.println();
					for(int i = 0; i < storei; i++){
						int symbolNr = array[j][i];
						if(symbolNr == 0){
							System.out.print(symbols[symbolNr]);
						}
						System.out.print(symbols[symbolNr]);
					}
					System.out.println();
				}
				else{
					for(int i = 0; i < storei; i++){
						if(i == 10){
							if((j == 2) || (j == 4)){
								System.out.print("--");
							}
							else{
								System.out.print("  ");
							}
						}
						int symbolNr = array[j][i];
						if((symbolNr == 1) || (symbolNr == 0)){
							System.out.print(symbols[symbolNr]);
							System.out.print(symbols[symbolNr]);
						}
						else{
							System.out.print(symbols[0]);
							System.out.print(symbols[symbolNr]);
						}
					}
					System.out.println();
				}
			}
		}
	}
	*/

	public int[][] makeOne(){
		int[][] array = {{0,0,0,0,0},{0,0,0,0,2},{0,0,0,0,0},{0,0,0,0,2},{0,0,0,0,0}};
		return array;
	}

	public int[][] makeTwo(){
	int[][] array = {{0,0,1,0,0},{0,0,0,0,2},{0,0,1,0,0},{2,0,0,0,0},{0,0,1,0,0}};
	return array;
	}

	public int[][] makeThree(){
	int[][] array = {{0,0,1,0,0},{0,0,0,0,2},{0,0,1,0,0},{0,0,0,0,2},{0,0,1,0,0}};
	return array;
	}

	public int[][] makeFour(){
	int[][] array = {{0,0,0,0,0},{2,0,0,0,2},{0,0,1,0,0},{0,0,0,0,2},{0,0,0,0,0}};
	return array;
	}

	public int[][] makeFive(){
	int[][] array = {{0,0,1,0,0},{2,0,0,0,0},{0,0,1,0,0},{0,0,0,0,2},{0,0,1,0,0}};
	return array;
	}

	public int[][] makeSix(){
	int[][] array = {{0,0,1,0,0},{2,0,0,0,0},{0,0,1,0,0},{2,0,0,0,2},{0,0,1,0,0}};
	return array;
	}

	public int[][] makeSeven(){
	int[][] array = {{0,0,1,0,0},{0,0,0,0,2},{0,0,0,0,0},{0,0,0,0,2},{0,0,0,0,0}};
	return array;
	}

	public int[][] makeEight(){
	int[][] array = {{0,0,1,0,0},{2,0,0,0,2},{0,0,1,0,0},{2,0,0,0,2},{0,0,1,0,0}};
	return array;
	}

	public int[][] makeNine(){
	int[][] array = {{0,0,1,0,0},{2,0,0,0,2},{0,0,1,0,0},{0,0,0,0,2},{0,0,1,0,0}};
	return array;
	}

	public int[][] makeZero(){
	int[][] array = {{0,0,1,0,0},{2,0,0,0,2},{0,0,0,0,0},{2,0,0,0,2},{0,0,1,0,0}};
	return array;
	}

	public void getTime(int size){
		int hours = LocalDateTime.now().getHour();
		int minutes = LocalDateTime.now().getMinute();
		int min1 = minutes/10;
		int min2 = minutes%10;
		int hr1 = hours/10;
		int hr2 = hours%10;
		//chooseNumber(hr1);
		chooseNumber(hr2);
		//chooseNumber(min1);
		//chooseNumber(min2);
		//printNumber(size);
	}

	public static void main(String[] args) {
		java_Clock clock = new java_Clock();
		clock.getTime(2);
		
	}
}