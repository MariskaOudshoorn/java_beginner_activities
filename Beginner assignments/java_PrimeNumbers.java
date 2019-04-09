import java.util.Scanner;
import java.lang.Math;
public class java_PrimeNumbers{
	
	public boolean isPrime(int number){
		if (number == 2){
			return false;
		}
		for(int x=2; x <= Math.sqrt(number); x++){
			if (number % x == 0){
				return false;
			}
		}
		return true;
	} 

	public void calculate(int number){
		int counter = 1;
		int placenumber = 2;
		while (counter < number){
			if (isPrime(placenumber)){
				counter ++;
			}
			placenumber++;
		}
		int printnumber = placenumber -1;
		System.out.println("The " + number + "th prime number is: " + printnumber);
	}

	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		java_PrimeNumbers prime = new java_PrimeNumbers();
		System.out.println("Enter a number:");
		int number = input.nextInt();
		prime.calculate(number);

	}
}