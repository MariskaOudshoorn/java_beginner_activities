//000001234000001234000001234000001234000001234000001234000001234000001234000001234
//120001234210001234340001234430001234560001234650001234780001234870001234990001234
//123004564456007894789001234430001234560001234650001234780001234870001234990001234  singleblock
//123456789123456789123456789123456789123456789123456789987654321987654321987654321  allrows
//123456789234567891345678912456789123567891234678912345789123456891234567912345678  allcolumns
//123123123456456456789789789123123123456456456789789789123123123456456456789789789  allboxes
//152489376739256841468371295387124659591763428246895713914637582625948137873512964  a complete sudoku

import java.util.*;

public class SudokuSolver{
		ArrayList<Integer> givenToSolveList = new ArrayList<>();
		ArrayList<Integer> solvingList = new ArrayList<>();

	public void playerInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Input your sudoku to solve.");
		String temporaryInput = input.nextLine();
		for(int i = 0; i < temporaryInput.length(); i++){
			int number = Character.digit(temporaryInput.charAt(i), 10);
			givenToSolveList.add(number);
		}
	}

	public void displayOriginalGrid(){
		for(int i = 0; i < givenToSolveList.size(); i+=9){
			System.out.println("-------------------");
			for(int j = 0; j < 9; j++){
				System.out.print("|" + givenToSolveList.get(i+j));
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

	public Boolean checkFullSudoku(){
		if(checkAllRows() && checkAllColumns() && checkAllBoxes()){
			return true;
		}
		else{
			return false;
		}
	}

	public void copyGivenToSolve(){
		ArrayList<Integer> tempList = new ArrayList<Integer>(givenToSolveList);
		solvingList.addAll(tempList);

	}

	public void solveColumn(){
		ArrayList<Integer> numbersAlreadyInRow = new ArrayList<>();
		for(int i = 0; i < 9; i++){
			if(givenToSolveList.get(i) != 0){
				numbersAlreadyInRow.add(givenToSolveList.get(i));
			}
		}
		for(int i = 0; i < numbersAlreadyInRow.size(); i++){
			System.out.println(numbersAlreadyInRow.get(i));
		}
		for(int i = 0; i < 9; i++){
			if(givenToSolveList.get(i) == 0){
				for(int j = 0; j < 9; j++){
					if(!numbersAlreadyInRow.contains(j)){
						solvingList.set(i, j);
						numbersAlreadyInRow.add(solvingList.get(i));
					}
				}
			}
		}
		for(int i = 0; i < numbersAlreadyInRow.size(); i++){
			System.out.println(numbersAlreadyInRow.get(i));
		}

	}

	public static void main(String[] args){
		SudokuSolver solver = new SudokuSolver();
		solver.playerInput();
		solver.displayOriginalGrid();
		solver.copyGivenToSolve();
		solver.solveColumn();
		solver.displaySolvingGrid();
		
		
	}
}