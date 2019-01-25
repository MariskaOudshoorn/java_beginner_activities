//123456789123456789123456789123456789123456789123456789987654321987654321987654321  allrows
//123456789234567891345678912456789123567891234678912345789123456891234567912345678  allcolumns
//123123123456456456789789789123123123456456456789789789123123123456456456789789789  allboxes
//152489376739256841468371295387124659591763428246895713914637582625948137873512964  a complete sudoku
//000000000000000000000000000000000000000000000000000000000000000000000000000000000
//152489076739256841468371295387120659591763428246095713914637582625948107873512960  partial sudoku
//000820090500000000308040007100000040006402503000090010093004000004035200000700900  given sudoku
//408057000250010408000400000000070106000000300196000005004723061703901500001000023  partial sudoku difficulty *** no 71
//008000903040050700050708000070004500010030000000206004000102000002005080390000100  Partial difficulty **** no 72

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
		ArrayList<Integer> alreadyInColumn = new ArrayList<>();
		for(int i = columnNumber; i < (9 * 9) + columnNumber; i += 9){
			if(solvingList.get(i) != 0){
				alreadyInColumn.add(solvingList.get(i));
			}
		}
		if((alreadyInColumn.contains(originalSudoku.get(indexNumber))) || (alreadyInColumn.contains(chosenNumber))){
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

	public void fillPossibleList(){
		for(int i = 0; i < solvingList.size(); i++){
			for(int j = 0; j < 9; j++){
				possibleList[i][j] = 0;
			}
		}
		for(int i = 0; i < solvingList.size(); i++){
			for(int j = 1; j < 10; j++){
				if(numberNotInRow(i, j) && numberNotInColumn(i, j) && numberNotInBox(i, j) && (solvingList.get(i) == 0)){
					possibleList[i][j-1] = j;
				}
			}
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
		//System.out.println("index: " + indexNumber + "Possibility " + onlyOne);
		return onlyOne;
	}

	public Integer givePossibleLength(int indexNumber){
		int counter = 0;
		for(int i = 0; i < 9; i++){
			if(possibleList[indexNumber][i] != 0){
				counter++;
			}
		}
		System.out.println("The possible amount is " + counter);
		return counter;
	}

	public ArrayList<Integer> returnUniquelist(int indexNumber, int compareIndex){
		boolean contains = false;
		ArrayList<Integer> uniqueList = new ArrayList<>();
		for(int i = 0; i < 9; i++){
			if(possibleList[indexNumber][i] != 0) {
				if (possibleList[indexNumber][i] == possibleList[compareIndex][i]) {
					contains = true;
				}
				if (!contains) {
					uniqueList.add(possibleList[indexNumber][i]);
				}
				else {
					contains = false;
				}
			}
		}
		return uniqueList;
		// correcte uniquelist word gereturned is getest.
	}

	public Integer uniqueForRow(int indexNumber){
		int rowNumber = indexNumber / 9;
		int uniqueCounter;
		nextIndex: for(int i = 0; i < 9; i++){
			uniqueCounter = 0;
			if(possibleList[indexNumber][i] != 0) {
				for (int j = (rowNumber * 9); j < ((rowNumber * 9) + 9); j++) {
					if (solvingList.get(j) == 0 && (indexNumber != j)) {
						if (possibleList[indexNumber][i] == possibleList[j][i]) {
							continue nextIndex;
						} else {
							uniqueCounter++;
						}
					} else {
						uniqueCounter++;
					}
				}
			}
			if( uniqueCounter == 9){
				return (i + 1) ;
			}
		}
		return 0;
	}

	public Integer uniqueForColumn(int indexNumber){
		int columnNumber = indexNumber % 9;
		int uniqueCounter;
		nextIndex: for(int i = 0; i < 9; i++){
			uniqueCounter = 0;
			if(possibleList[indexNumber][i] != 0) {
				for (int j = columnNumber; j < (9 * 9) + columnNumber; j += 9) {
					if (solvingList.get(j) == 0 && (indexNumber != j)) {
						if (possibleList[indexNumber][i] == possibleList[j][i]) {
							continue nextIndex;
						} else {
							uniqueCounter++;
						}
					} else {
						uniqueCounter++;
					}
				}
			}
			if( uniqueCounter == 9){
				return (i + 1) ;
			}
		}
		return 0;
	}

	public Integer uniqueForBox(int indexNumber){
		int currentLocation = whichBoxIsThis(indexNumber);
		int uniqueCounter;
		nextIndex: for(int i = 0; i < 9; i++){
			uniqueCounter = 0;
			if(possibleList[indexNumber][i] != 0) {
				for (int j = currentLocation; j < (currentLocation + 27); j+= 9) {
					for(int k = 0; k < 3; k++) {
						if (solvingList.get(currentLocation + k) == 0 && (indexNumber != (currentLocation + k))) {
							if (possibleList[indexNumber][i] == possibleList[(currentLocation + k)][i]) {
								continue nextIndex;
							} else {
								uniqueCounter++;
							}
						} else {
							uniqueCounter++;
						}
					}
				}
			}
			if( uniqueCounter == 9){
				return (i + 1) ;
			}
		}
		return 0;
	}

	public void singleSolve(){
		int singleCounter = 0;
		int checkCounter = 0;
		boolean singleSolved = false;
		while(!singleSolved) {
			for (int i = 0; i < solvingList.size(); i++) {
				singleCounter = 0;
				if ((solvingList.get(i).equals(0)) || (!originalSudoku.get(i).equals(solvingList.get(i)))) {
					if (singlePossible(i) != 0) {
						int solution = singlePossible(i);
						solvingList.set(i, solution);
						fillPossibleList();
						singleCounter = 1;
						checkCounter = 0;
					}
				}
			}
			if (singleCounter == 0) {
				checkCounter++;
			}
			if (checkCounter == 2){
				singleSolved = true;
			}
		}
	}

	public void fillUnique(){
		boolean noUniquesLeft = false;
		boolean	rowsDone;
		boolean columnsDone;
		boolean boxDone;
		while(!noUniquesLeft) {
			rowsDone = false;
			columnsDone = false;
			boxDone = false;
			for (int i = 0; i < solvingList.size(); i++) {
				if ((solvingList.get(i).equals(0)) || (!originalSudoku.get(i).equals(solvingList.get(i)))) {
					int solutionRow = uniqueForRow(i);
					if (solutionRow != 0) {
						solvingList.set(i, solutionRow);
						fillPossibleList();
						rowsDone = true;
					}
					else if (uniqueForColumn(i) != 0) {
						int solution = uniqueForColumn(i);
						solvingList.set(i, solution);
						fillPossibleList();
						columnsDone = true;
					}
					/*
					else if (uniqueForBox(i) != 0) {
						int solution = uniqueForBox(i);
						solvingList.set(i, solution);
						fillPossibleList();
						boxDone = true;
					}
					*/

				}
			}
			noUniquesLeft = true;
			if(rowsDone || columnsDone || boxDone){
				noUniquesLeft = false;
			}
		}
	}

	public boolean recursiveRemainder(){
		for(int i = 0; i < solvingList.size(); i++){
			if(solvingList.get(i) == 0){
				for(int j = 1; j < 10; j++){
					if((numberNotInRow(i, j)) && (numberNotInColumn(i , j)) && (numberNotInBox(i, j))){
						solvingList.set(i, j);
						if(recursiveRemainder()) {
							return true;
						}
					}
					solvingList.set(i, 0);
				}
				return false;
			}
		}
		return true;
	}

	public void solveSudoku(){
		boolean quit = false;
		int checkUnique = 0;
		fillPossibleList();
		while(!quit) {
			singleSolve();
			fillUnique();
			checkUnique ++;
			if(checkUnique == 2){
				quit = true;
			}
		}
	}

	public static void main(String[] args){
		final long startTime = System.nanoTime();
		SudokuSolver solver = new SudokuSolver();
		solver.playerInput();
		solver.displayOriginalGrid();
		solver.copyOriginalToSolveList();
		solver.fillPossibleList();
		solver.solveSudoku();
		//System.out.println("Unique in row for index 7: " + solver.comparePossiblesinRow(7));
		solver.recursiveRemainder();
		solver.displaySolvingGrid();
		final long duration = System.nanoTime() - startTime;
		System.out.print("It took " + (duration / 1000000000) + " seconds.");
	}
}