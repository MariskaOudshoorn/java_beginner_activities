import java.util.Scanner;
public class java_Collatz{

	public void sequence(int number){
		while(number != 1){
			if (number % 2 == 0){
				number /= 2;
			}
			else {
				number *= 3;
				number ++;
			}
			System.out.println(number);
		}
	}

	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		java_Collatz collatz = new java_Collatz();
		System.out.println("Enter a number:");
		int number = input.nextInt();
		collatz.sequence(number);
	}
}