import java.util.Scanner;
public class java_LeapYear{

		public void calculate(int year){
			if (year % 4 != 0){
				System.out.println("This is not a leap year");
			}
			else if (year % 100 != 0){
				System.out.println("This is a leap year");
			}
			else if (year % 400 != 0){
				System.out.println("This is not a leap year");
			}
			else{
				System.out.println("This is a leap year");
			}
		}
	
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		java_LeapYear leapyear = new java_LeapYear();
		System.out.println("Enter a year:");
		int year = input.nextInt();
		leapyear.calculate(year);

	}
}
