import java.util.Scanner;
import java.time.LocalDate;
public class java_Quote{

    public String chooseQuote(int number){
    	String quote = "Undefined.";
    	switch(number){
    		case 1: quote = "\"Eppur si muove.\" -- Galileo";
    			break;
    		case 2: quote = "\"Eureka!\" -- Archimedes";
    			break;
    		case 3: quote = "\"In regione caecorum rex est luscus.\" -- Erasmus";
    			break;
    		case 4: quote = "\"I know nothing except the fact of my ignorance.\" -- Socrates";
    			break;
    		case 5: quote = "\"Cogito, ergo sum.\" -- René Descartes";
    			break;
    		case 6: quote = "\"If I have seen further it is by standing on the shoulders of giants.\" -- sir Isaac Newton";
    			break;
    	}
    	System.out.println(quote);
    	return quote;
    }
    
    public int getDate(){
		LocalDate localDate = LocalDate.now();
		int day = localDate.getDayOfMonth();
		int month = localDate.getMonth().getValue();
		//int day = 4;
		//int month = 12;
		int daysbefore = 0;
		int daysYear = 0;
		System.out.println("month is " + month);
		System.out.println("day is " + day);
		switch(month){
			case 1: daysbefore = 0;
				break;
			case 2: daysbefore = 31;
				break;
			case 3: daysbefore = 59;
				break;
			case 4: daysbefore = 90;
				break;
			case 5: daysbefore = 120;
				break;
			case 6: daysbefore = 151;
				break;
			case 7: daysbefore = 181;
				break;
			case 8: daysbefore = 212;
				break;
			case 9: daysbefore = 242;
				break;
			case 10: daysbefore = 273;
				break;
			case 11: daysbefore = 303;
				break;
			case 12: daysbefore = 334;
				break;
		}
		daysYear = daysbefore + day;
		return daysYear;
    }

    public int calculateQuote(int days){
    	int quoteNumber = days % 6;
    	System.out.println("Quotenumber is: " + quoteNumber);
    	return quoteNumber;
    }

	public static void main(String[] args) {
		java_Quote quote = new java_Quote();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a number:");
		int number = input.nextInt();
		int daysYear = quote.getDate();
		int quoteNumber = quote.calculateQuote(daysYear);
		quote.chooseQuote(quoteNumber);
	}
}