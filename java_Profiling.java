import java.util.*;
import java.io.*;
import java.lang.String;

public class java_Profiling{
	public ArrayList<Profile> listOfCustomers = new ArrayList<>();
	public ArrayList<String> customerBookList = new ArrayList<String>();


	public void readFile(){
		try{
			Scanner file = new Scanner(new File("C:\\Users\\moudshoorn\\java-opdrachten-code\\src\\main\\resources\\intermediate\\profiling-data.txt"));
			while(file.hasNextLine()){
				String line = file.nextLine();
				process(line);
			}
		}
		catch(FileNotFoundException exception){
			System.out.println("File not found");
		}
	}

	public void process(String customerline){
		ArrayList<String> bookList = new ArrayList<String>(Arrays.asList(customerline.split(", ")));
		String name = bookList.get(0);
		bookList.remove(0);
		Profile customerProfile = new Profile(name, bookList);
		listOfCustomers.add(customerProfile);
	}

	public ArrayList<String> getCustomerBookList(String customer){
		ArrayList<String> listOfBooks = new ArrayList<String>();
		Boolean customerFound = false;
		for(int i = 0; i < listOfCustomers.size(); i++){
			String thisCustomer = listOfCustomers.get(i).name;
			if((thisCustomer.equalsIgnoreCase(customer)) || (thisCustomer.toLowerCase().contains(customer.toLowerCase()))){
				listOfBooks = listOfCustomers.get(i).booklist;
				customerFound = true;
			}
		}
		if(!customerFound){
			System.out.println("We could not find this customer in the system.");
		}
		return listOfBooks;
	}

	public void whoReadThisBook(){
		Scanner input = new Scanner(System.in);
		System.out.println("Which book would you like to search for?");
		String book = input.nextLine();
		System.out.println("The customers that have read this book are:");
		for(int i = 0; i < listOfCustomers.size(); i++){
			ArrayList<String> listOfBooks = new ArrayList<String>();
			listOfBooks = listOfCustomers.get(i).booklist;
			for(int j = 0; j < listOfBooks.size(); j++){
				if(listOfBooks.get(j).toLowerCase().contains(book.toLowerCase())){
					System.out.println(listOfCustomers.get(i).name);
				}
			}
		}
	}

	public String recommendationPerson(String customer){
		ArrayList<String> compareList = new ArrayList<String>();
		ArrayList<String> similarCustomers = new ArrayList<String>();
		customerBookList = getCustomerBookList(customer);
		if(customerBookList.size() < 3){
			return "None";
		}
		for(int i = 0; i < listOfCustomers.size(); i++){
			if(!listOfCustomers.get(i).name.contains(customer)){
				for(int j = 0; j < customerBookList.size(); j++){
					ArrayList<String> listOfCompareBooks = new ArrayList<String>();
					listOfCompareBooks = listOfCustomers.get(i).booklist;
					for(int k = 0; k < listOfCompareBooks.size(); k++){
						if(listOfCompareBooks.get(k).toLowerCase().contains(customerBookList.get(j).toLowerCase())){
							compareList.add(listOfCompareBooks.get(k));
							if(compareList.size() == 3){
								if(!similarCustomers.contains(listOfCustomers.get(i).name)){
									similarCustomers.add(listOfCustomers.get(i).name);		
								}								
							}
						}
					}
				}
			}
			compareList.clear();
		}
		if(similarCustomers.size() < 3){
			return "None";
		}
		Random rand = new Random();
		int customernumber = rand.nextInt(similarCustomers.size());
		String similarCustomer = similarCustomers.get(customernumber);
		return similarCustomer;
	}

	public void recommendedBook(String similarCustomer){
		Random rand = new Random();
		ArrayList<String> similarBookList = new ArrayList<String>();
		if(similarCustomer.equalsIgnoreCase("None")){
			System.out.println("There are no recommendations at this time.");
		}
		else{
			similarBookList = getCustomerBookList(similarCustomer);
			String recommendedBook;
			do{
				int bookNumber = rand.nextInt(similarBookList.size());
				recommendedBook = similarBookList.get(bookNumber);
			}while(customerBookList.contains(recommendedBook));
			System.out.println("We recommend reading: " + recommendedBook);
		}
	}

	public void searchPerson(){
		Scanner input = new Scanner(System.in);
		System.out.println("Who would you like to search for?");
		String name = input.nextLine();
		ArrayList<String> booklist = new ArrayList<String>();
		booklist = getCustomerBookList(name);
		System.out.println("This person has read:");
		for(int i = 0; i < booklist.size(); i++){
			System.out.println(booklist.get(i));
		}
	}

	public void recommendation(){
		Scanner input = new Scanner(System.in);
		System.out.println("Who would you like a recommendation for?");
		String compareTo = input.nextLine();
		String similarCustomer = recommendationPerson(compareTo);
		recommendedBook(similarCustomer);
	}

	public void menu(){
		Scanner input = new Scanner(System.in);
		readFile();
		int choice = 0;
		Boolean running = true;
		while(running){
			System.out.println("What would you like to do: 1.Search booklist of a person 2.See who read a book 3.Get a book recommendation 4.Exit.");
			while(!input.hasNextInt()){
				System.out.println("Please enter a number");
				input.next();
			}
			choice = input.nextInt();
			switch(choice){
				case 1 : searchPerson();
					break;
				case 2 : whoReadThisBook();
					break;
				case 3 : recommendation();
					break;
				case 4 : running = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		java_Profiling profiling = new java_Profiling();
		profiling.menu();
	}
}