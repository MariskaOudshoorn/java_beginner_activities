//000001234000001234000001234000001234000001234000001234000001234000001234000001234
//120001234210001234340001234430001234560001234650001234780001234870001234990001234  two columns
//123004564456007894789001234430001234560001234650001234780001234870001234990001234  singleblock
//123456789123456789123456789123456789123456789123456789987654321987654321987654321  allrows
//123456789234567891345678912456789123567891234678912345789123456891234567912345678  allcolumns
//123123123456456456789789789123123123456456456789789789123123123456456456789789789  allboxes
//152489376739256841468371295387124659591763428246895713914637582625948137873512964  a complete sudoku
//000000000000000000000000000000000000000000000000000000000000000000000000000000000
//152489076739256841468371295387120659591763428246095713914637582625948107873512960  partial sudoku
//000820090500000000308040007100000040006402503000090010093004000004035200000700900  given sudoku

import java.util.*;

public class SudokuSolver{
	ArrayList<Integer> originalSudoku = new ArrayList<>();
	ArrayList<Integer> solvingList = new ArrayList<>();
	int[][] possibleList = new int[81][9];

	public void playerInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Input your sudoku to solve.");
		String temporaryInput = input.nextLine();
		for(int i = 0; i < temporaryInput.length(); i++){
			int number = Character.digit(temporaryInput.charAt(i), 10);
			originalSudoku.add(number);
		}
	}

	public void displayOriginalGrid(){
		for(int i = 0; i < originalSudoku.size(); i+=9){
			System.out.println("-------------------");
			for(int j = 0; j < 9; j++){
				System.out.print("|" + originalSudoku.get(i+j));
			}
			System.out.println("|");
		}
		System.out.println();
	}

	public void displaySolvingGrid(){
		for(int i = 0; i < solvingList.size(); i+=9){
			System.out.println("-------------------");
			for(int j = 0; j < 9; j++){
				System.out.print("|" + solvingList.get(i+j));
			}
			System.out.println("|");
		}
		System.out.println();
	}

	public Boolean checkSingleRow(int rowtocheck){
		int startNumber = 9*rowtocheck;
		int sumOfRow = 0;
		for(int i = startNumber; i < startNumber + 9; i++){
			sumOfRow += solvingList.get(i);
		}
		if(sumOfRow == 45){
			return true;
		}
		else{
			return false;
		}
	}

	public Boolean checkAllRows(){
		Boolean rowCorrect = false;
		for(int i = 0; i < 9; i++){
			if(!checkSingleRow(i)){
				return false;
			}
		}
		return true;
	}

	public Boolean numberNotInRow(int indexNumber, int chosenNumber){
		int rowNumber = indexNumber / 9;
		ArrayList<Integer> alreadyInRow = new ArrayList<>(); 
		for(int i = rowNumber * 9; i < (rowNumber * 9) + 9; i++){
			if(solvingList.get(i) != 0){
				alreadyInRow.add(solvingList.get(i));
			}
		}
		if((alreadyInRow.contains(originalSudoku.get(indexNumber))) || (alreadyInRow.contains(chosenNumber))){
			return false;
		}
		return true;
	}

	public Boolean checkSingleColumn(int columntocheck){
		int sumOfColumn = 0;
		for(int i = 0; i < 9; i++){
			sumOfColumn += solvingList.get(columntocheck);
			columntocheck += 9;
		}
		if(sumOfColumn == 45){
			return true;
		}
		else{
			return false;
		}
	}

	public Boolean checkAllColumns(){
		Boolean columnCorrect = false;
		for(int i = 0; i < 9; i++){
			if(!checkSingleColumn(i)){
				return false;
			}
		}
		return true;
	}

	public boolean numberNotInColumn(int indexNumber, int chosenNumber){
		int columnNumber = indexNumber % 9;
		ArrayList<Integer> alreadyInRow = new ArrayList<>(); 
		for(int i = 0 + columnNumber; i < (9 * 8) + columnNumber; i += 9){
			if(solvingList.get(i) != 0){
				alreadyInRow.add(solvingList.get(i));
			}
		}
		if((alreadyInRow.contains(originalSudoku.get(indexNumber))) || (alreadyInRow.contains(chosenNumber))){
			return false;
		}
		return true;
	}

	public Boolean checkSingleBox(int leftTopOfBox){
		int sumOfBox = 0;
		int currentLocation = leftTopOfBox;
		for(int i = 0; i < 3; i++){	
			for(int j = 0; j < 3; j++){
				sumOfBox += solvingList.get(currentLocation + j);
			}
			currentLocation += 9;
		}
		if(sumOfBox == 45){
			return true;
		}
		else{
			return false;
		}
	}

	public Boolean checkAllBoxes(){
		Boolean boxCorrect = false;
		for(int i = 0; i < 81; i += 27){
			for( int j = 0; j < 9; j += 3){
				if(!checkSingleBox(i+j)){
					return false;
				}
			}
		}
		return true;
	}

	public Boolean numberNotInBox(int indexNumber, int chosenNumber){
		int currentLocation = whichBoxIsThis(indexNumber);
		ArrayList<Integer> alreadyInBox = new ArrayList<>();
		for(int i = 0; i < 3; i++){	
			for(int j = 0; j < 3; j++){
				if(solvingList.get(currentLocation + j) != 0){
					alreadyInBox.add(solvingList.get(currentLocation + j));
				}
			}
			currentLocation += 9;
		}
		if((alreadyInBox.contains(originalSudoku.get(indexNumber))) || (alreadyInBox.contains(chosenNumber))){
			return false;
		}
		return true;
	}

	public int whichBoxIsThis(int indexNumber){
		int topLeftOfBox = -1; 
		int rowNumber = indexNumber / 9;
		int columnNumber = indexNumber % 9;
		if(columnNumber < 3){
			if(rowNumber < 3){
				topLeftOfBox = 0;
			}
			else if((rowNumber > 2) && (rowNumber < 6)){
				topLeftOfBox = 27;
			}
			else if(rowNumber > 5){
				topLeftOfBox = 54;
			}
		}
		else if((columnNumber > 2) && (columnNumber < 6)){
			if(rowNumber < 3){
				topLeftOfBox = 3;
			}
			else if((rowNumber > 2) && (rowNumber < 6)){
				topLeftOfBox = 30;
			}
			else if(rowNumber > 5){
				topLeftOfBox = 57;
			}
		}
		else if(columnNumber > 5){
			if(rowNumber < 3){
				topLeftOfBox = 6;
			}
			else if((rowNumber > 2) && (rowNumber < 6)){
				topLeftOfBox = 33;
			}
			else if(rowNumber > 5){
				topLeftOfBox = 60;
			}
		}
		return topLeftOfBox;
	}

	public Boolean checkFullSudoku(){
		if(checkAllRows() && checkAllColumns() && checkAllBoxes()){
			return true;
		}
		else{
			return false;
		} // possibly eventually outdated
	}

	public void copyOriginalToSolveList(){
		ArrayList<Integer> tempList = new ArrayList<Integer>(originalSudoku);
		solvingList.addAll(tempList);
	}

	public Boolean noZeroesLeft(){
		for(int i = 0; i < solvingList.size(); i++){
			if(solvingList.contains(0)){
				return false;
			}
		}
		return true;
	}

	public void fillPossibleList(){
		for(int i = 0; i < solvingList.size(); i++){
			for(int j = 1; j < 10; j++){
				if(numberNotInRow(i, j) && numberNotInColumn(i, j) && numberNotInBox(i, j)){
					possibleList[i][j-1] = j;
				}
			}
			/* System.out.println("Next index");
			for(int j = 0; j < 9; j++){
				System.out.println(possibleList[i][j]);
			}	
			*/
		}
	}

	public Integer singlePossible(int indexNumber){
		int onlyOne = 0;
		for(int i = 0; i < 9; i++){
			if(onlyOne == 0){
				onlyOne = possibleList[indexNumber][i];
			}
			else if((onlyOne != 0) && (possibleList[indexNumber][i] != 0)){
				return 0;
			}
		}
		return onlyOne;
	}

	public Integer givePossibleLength(int indexNumber){
		int counter = 0;
		for(int i = 0; i < 9; i++){
			if(possibleList[indexNumber][i] != 0){
				counter++;
			}
		}
		return counter;
	}

	public void possibleLengthTwo(int number){
		int twoCounter = 0;
		ArrayList<Integer> indexKeeper = new ArrayList<>();
		for(int i = 0; i < 9; i++){ //checks entire row! 
			if(givePossibleLength(i) == 2){
				twoCounter++;
				indexKeeper.add(i)
			}
		}
		if(twoCounter == 2){
			int[] arrayOne = new int[9];
			int[] arrayTwo = new int[9];
			int indexOne = indexKeeper.get(0);
			int indexTwo = indexKeeper.get(1);
			for(int i = 0; i < 9; i++){
				arrayOne[i] = possibleList[indexOne][i];
				arrayTwo[i] = possibleList[indexTwo][i];
			}
			if(Arrays.equals(arrayOne, arrayTwo)); //skip...
			//If two places with two options compare two options if they differ enter the number with which they differ. 

		}
	}

	public void oldSolveSudoku(){
		int check1 = 1;
		int check2 = 2;
		int check3 = 3;
		int check4 = 4;
		int check5 = 5;
		int check6 = 6;
		int check7 = 7;
		int check8 = 8;
		int check9 = 9;
		for(int i = 0; i < originalSudoku.size(); i++){
			if((solvingList.get(i).equals(0))  || (!originalSudoku.get(i).equals(solvingList.get(i)))){
				if((numberNotInRow(i, check1)) && (numberNotInColumn(i, check1)) && (numberNotInBox(i, check1))){
					solvingList.set(i, check1);
				}
				else if((numberNotInRow(i, check2)) && (numberNotInColumn(i, check2)) && (numberNotInBox(i, check2))){
					solvingList.set(i, check2);
				}
				else if((numberNotInRow(i, check3)) && (numberNotInColumn(i, check3)) && (numberNotInBox(i, check3))){
					solvingList.set(i, check3);
				}
				else if((numberNotInRow(i, check4)) && (numberNotInColumn(i, check4)) && (numberNotInBox(i, check4))){
					solvingList.set(i, check4);
				}
				else if((numberNotInRow(i, check5)) && (numberNotInColumn(i, check5)) && (numberNotInBox(i, check5))){
					solvingList.set(i, check5);
				}
				else if((numberNotInRow(i, check6)) && (numberNotInColumn(i, check6)) && (numberNotInBox(i, check6))){
					solvingList.set(i, check6);
				}
				else if((numberNotInRow(i, check7)) && (numberNotInColumn(i, check7)) && (numberNotInBox(i, check7))){
					solvingList.set(i, check7);
				}
				else if((numberNotInRow(i, check8)) && (numberNotInColumn(i, check8)) && (numberNotInBox(i, check8))){
					solvingList.set(i, check8);
				}
				else if((numberNotInRow(i, check9)) && (numberNotInColumn(i, check9)) && (numberNotInBox(i, check9))){
					solvingList.set(i, check9);
				}
			}
		} // possibly eventually outdated
	}

	public void solveSudoku(){
		for(int i = 0; i < originalSudoku.size(); i++){
			if((solvingList.get(i).equals(0)) || (!originalSudoku.get(i).equals(solvingList.get(i)))){
				if(singlePossible(i) != 0){
					int solution = singlePossible(i);
					solvingList.set(i, solution);
				}
			}
		}
	}

	public static void main(String[] args){
		SudokuSolver solver = new SudokuSolver();
		solver.playerInput();
		solver.displayOriginalGrid();
		solver.copyOriginalToSolveList();
		solver.fillPossibleList();
		solver.solveSudoku();
		solver.displaySolvingGrid();

	}
}