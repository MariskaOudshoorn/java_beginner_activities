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
		for(int i = 0 + columnNumber; i < (9 * 9) + columnNumber; i += 9){
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
			for(int j = 0; j < 9; j++){
				possibleList[i][j] = 0;
			}
		}
		for(int i = 0; i < solvingList.size(); i++){
			for(int j = 1; j < 10; j++){
				if(numberNotInRow(i, j) && numberNotInColumn(i, j) && numberNotInBox(i, j)){
					possibleList[i][j-1] = j;
				}
			}

			/*
			System.out.println("Next index");
			for(int j = 0; j < 9; j++){
				System.out.println(possibleList[i][j]);
			}
			*/
		}
	} // Contains code that will be obsolete

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

	public boolean possibleRowLengthTwo(int indexNumber){
		System.out.println("Going to check for two!");
		int rowNumber = indexNumber / 9;
		int startNumber = rowNumber * 9;
		int twoCounter = 0;
		boolean didItHappen = false;
		ArrayList<Integer> indexKeeper = new ArrayList<>();
		for(int i = startNumber; i < (startNumber + 9); i++){
			System.out.println("Indexnumber is " + i);
			if(givePossibleLength(i) == 2){
				twoCounter++;
				System.out.println("twocounter is: " + twoCounter);
				indexKeeper.add(i);
			}
		}
		if(twoCounter == 2){
			int[] arrayOne = new int[2];
			int[] arrayTwo = new int[2];
			int indexOne = indexKeeper.get(0);
			int indexTwo = indexKeeper.get(1);
			for(int i = 0; i < 2; i++){
				arrayOne[i] = possibleList[indexOne][i];
				arrayTwo[i] = possibleList[indexTwo][i];
			}
			if(Arrays.equals(arrayOne, arrayTwo));
			else if(arrayOne[0] == arrayTwo[0]){
				solvingList.set(indexOne, arrayOne[1]);
				solvingList.set(indexTwo, arrayTwo[1]);
				System.out.println("I placed two");
				didItHappen = true;
			}
			else if(arrayOne[1] == arrayTwo[1]){
				solvingList.set(indexOne, arrayOne[0]);
				solvingList.set(indexTwo, arrayTwo[0]);
				System.out.println("I placed two");
				didItHappen = true;
			}
			else if(arrayOne[0] == arrayTwo[1]){
				solvingList.set(indexOne, arrayOne[1]);
				solvingList.set(indexTwo, arrayTwo[0]);
				System.out.println("I placed two");
				didItHappen = true;
			}
			else if(arrayOne[1] == arrayTwo[0]){
				solvingList.set(indexOne, arrayOne[0]);
				solvingList.set(indexTwo, arrayTwo[1]);
				System.out.println("I placed two");
				didItHappen = true;
			}
		}
		return didItHappen;
	}

	public void uniqueInBox(int indexNumber){
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		int count7 = 0;
		int count8 = 0;
		int count9 = 0;
		int oneIndex = 0;
		int twoIndex = 0;
		int threeIndex = 0;
		int fourIndex = 0;
		int fiveIndex = 0;
		int sixIndex = 0;
		int sevenIndex = 0;
		int eightIndex = 0;
		int nineIndex = 0;

		int topLeftOfBox = whichBoxIsThis(indexNumber);
		for( int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(possibleList[topLeftOfBox + j][0] == 1){
					count1++;
					oneIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][1] == 2){
					count2++;
					twoIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][2] == 3){
					count3++;
					threeIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][3] == 4){
					count4++;
					fourIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][4] == 5){
					count5++;
					fiveIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][5] == 6){
					count6++;
					sixIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][6] == 7){
					count7++;
					sevenIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][7] == 8){
					count8++;
					eightIndex = i+j;
				}
				else if(possibleList[topLeftOfBox + j][8] == 9){
					count9++;
					nineIndex = i+j;
				}
			}
			topLeftOfBox += 9;
		}
		if((count1 == 1) && (!originalSudoku.get(oneIndex).equals(solvingList.get(oneIndex)))){
			solvingList.set(oneIndex, 1);
		}
		if((count2 == 1) && (!originalSudoku.get(twoIndex).equals(solvingList.get(twoIndex)))){
			solvingList.set(twoIndex, 2);
		}
		if((count3 == 1) && (!originalSudoku.get(threeIndex).equals(solvingList.get(threeIndex)))){
			solvingList.set(threeIndex, 3);
		}
		if((count4 == 1) && (!originalSudoku.get(fourIndex).equals(solvingList.get(fourIndex)))){
			solvingList.set(fourIndex, 4);
		}
		if((count5 == 1) && (!originalSudoku.get(fiveIndex).equals(solvingList.get(fiveIndex)))){
			solvingList.set(fiveIndex, 5);
		}
		if((count6 == 1) && (!originalSudoku.get(sixIndex).equals(solvingList.get(sixIndex)))){
			solvingList.set(sixIndex, 6);
		}
		if((count7 == 1) && (!originalSudoku.get(sixIndex).equals(solvingList.get(sixIndex)))){
			solvingList.set(sevenIndex, 7);
		}
		if((count8 == 1) && (!originalSudoku.get(sevenIndex).equals(solvingList.get(sevenIndex)))){
			solvingList.set(eightIndex, 8);
		}
		if((count9 == 1) && (!originalSudoku.get(nineIndex).equals(solvingList.get(nineIndex)))){
			solvingList.set(nineIndex, 9);
		}
	}

	public void uniqueInRow(int indexNumber){
		int rowNumber = indexNumber / 9;
		ArrayList<Integer> uniqueNumbers = new ArrayList<>();
		for(int i = (rowNumber * 9); i < ((rowNumber*9) + 9); i++){
			if( i != indexNumber){
				for(int j = 0; j < 9; j++){
					if((possibleList[indexNumber][j] != possibleList[i][j]) & (possibleList[indexNumber][j] != 0) & (!uniqueNumbers.contains(j))){
						uniqueNumbers.add(possibleList[indexNumber][j]);
					}
				}
			}
		}
		if(uniqueNumbers.size() != 0) {
			System.out.println("Unique number for index " + indexNumber);
			for (int i = 0; i < uniqueNumbers.size(); i++) {
				System.out.println(uniqueNumbers.get(i));
			}
		}
	}

	public ArrayList<Integer> returnUniqelist(int indexNumber, int compareIndex){
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

	public int uniqueOption(int indexNumber){
		int rowNumber = indexNumber / 9;
		ArrayList<Integer> singlePossibleList = new ArrayList<>();
		for(int i = (rowNumber * 9); i < ((rowNumber*9) + 9); i++){
			ArrayList<Integer> uniqueList;
			uniqueList = returnUniqelist(indexNumber, i);
			if(uniqueList.size() == 1){ // en uniquelist is niet gelijk aan wat al in possiblelist staat
				singlePossibleList.add(uniqueList.get(0));
			}
		}
		if(singlePossibleList.size() == 2){
			solvingList.set(indexNumber, singlePossibleList.get(1));
		}
		return 0;
	}


	public void solveSudoku(){
		boolean quit = false;
		int singleCounter = 0;
		int checkCounter = 0;
		int checkUnique = 0;
		fillPossibleList();
		while(!quit) {
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

				if (checkCounter == 2) {
					singleSolved = true;
				}
			}
			for (int i = 0; i < solvingList.size(); i++) {
				if ((solvingList.get(i).equals(0)) || (!originalSudoku.get(i).equals(solvingList.get(i)))) {
					if (uniqueOption(i) != 0) {
						int solution = uniqueOption(i);
						solvingList.set(i, solution);
						fillPossibleList();
					}
				}
			}
			//checkUnique ++;
			//if(checkUnique == 2) {
				quit = true;
			//}
		}
	}

	public static void main(String[] args){
		SudokuSolver solver = new SudokuSolver();
		solver.playerInput();
		solver.displayOriginalGrid();
		solver.copyOriginalToSolveList();
		solver.fillPossibleList();
		solver.solveSudoku();
		//System.out.println("Possiblelist for 2 index 0 is " + solver.possibleList[0][1]);
		System.out.println("Uniquelist is " + solver.returnUniqelist(25, 7));
		solver.displaySolvingGrid();
	}
}