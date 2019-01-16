import java.util.*;
import java.io.*;
import java.lang.String;

public class Profile{

	public ArrayList<String> booklist = new ArrayList<>();
	public String name;

	public Profile (String customer, ArrayList<String> customerList){
		booklist = customerList;
		name = customer;
	}
}