import java.util.Random;
import java.util.Scanner;
public class java_Lists{
	
	public void createRandomList(int length, int[] list){
		Random rand = new Random();
		for(int i = 0; i < length; i++){
			list[i] = rand.nextInt(101);
		}
	}

	public void printList(int length, int[] list){
		for(int i = 0; i < length; i++){
			System.out.println(list[i]);
		}
	}

	public void printHighest(int length, int[] list){
		int highestnumber = 0;
		for(int i = 0; i < length; i++){
			if( list[i] > highestnumber){
				highestnumber = list[i];
			}
		}
		System.out.println("The highest number in this list is:" + highestnumber);
	}

	public void printLowest(int length, int[] list){
		int lowestnumber = 100;
		int almostlowest = 100;
		for(int i = 0; i < length; i++){
			if( list[i] <= almostlowest ){
				if(list[i] <= lowestnumber){
					almostlowest = lowestnumber;
					lowestnumber = list[i];
				}
				else{
					almostlowest = list[i];
				}
			}
		}
		int add = almostlowest + lowestnumber;
		System.out.println("The two lowest numbers are: " + almostlowest + " and " + lowestnumber + ". Added together these are " + add);
	}

	public void filter(int length, int[] list){
		System.out.println("The even numbers in this list are:");
		for(int i = 0; i < length; i++){
			if( list[i] % 2 == 0){
				System.out.println(list[i]);
			}
		}

	}

	public void split(int length, int[] list){
		int[] list2 = new int[length];
		int[] list3 = new int[length];
		int[] list5 = new int[length];
		int[] listremain = new int[length];
		int counter2 = 0;
		int counter3 = 0;
		int counter5 = 0;
		int counterremain = 0;
		for(int i =0; i < length; i++){
			if( list[i] % 2 == 0){
				list2[counter2] = list[i];
				counter2++;
			}
			if( list[i] % 3 == 0){
				list3[counter3] = list[i];
				counter3++;
			}
			if( list[i] % 5 == 0){
				list5[counter5] = list[i];
				counter5++;
			}
			if((list[i] % 2 != 0) && (list[i] % 3 != 0) && (list[i] % 5 != 0)){
				listremain[counterremain] = list[i];
				counterremain++;
			}
		}
		System.out.println("List that can be divided by 2: ");
		for(int i = 0; i < counter2; i++){
			System.out.println(list2[i]);
		}
		System.out.println("List that can be divided by 3: ");
		for(int i = 0; i < counter3; i++){
			System.out.println(list3[i]);
		}
		System.out.println("List that can be divided by 5: ");
		for(int i = 0; i < counter5; i++){
			System.out.println(list5[i]);
		}
		System.out.println("List of everything else: ");
		for(int i = 0; i < counterremain; i++){
			System.out.println(listremain[i]);
		}
	}

	public void bubbleSort(int length, int[] list){
		int temp = 0;
		for(int j = 0; j < length; j++){
			for(int i = 1; i< length-j; i++){
				if(list[i-1] > list[i]){
					temp = list[i];
					list[i] = list[i-1];
					list[i-1] = temp;
				}
			}
		}
		System.out.println("The sorted list:");
		for(int i = 0; i < length; i++){
			System.out.println(list[i]);
		}
	}

	public static void main(String []args){
		java_Lists lists = new java_Lists();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a list length");
		int length = input.nextInt();
		int[] list = new int[length];
		lists.createRandomList(length, list);
		lists.printList(length, list);
		boolean run = true;
		while(run){
			System.out.println("What would you like to do with this list? 1.Print highest 2.Print lowest 3.Even numbers 4.Split 5.Bubblesort 6.Exit");
			System.out.println("Enter the number of the menu option");
			int number = input.nextInt();
			if( number == 1){
				lists.printHighest(length, list);
			}
			if( number == 2){
				lists.printLowest(length, list);
			}
			if(number == 3){
				lists.filter(length, list);
			}
			if(number == 4){
				lists.split(length, list);
			}
			if(number == 5){
				lists.bubbleSort(length, list);
			}
			if(number == 6){
				run = false;
			}
		}
		
	}
}