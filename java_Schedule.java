import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class java_Schedule{

	HashMap<String, Integer> schedule = new HashMap<String, Integer>();

	public void addToSchedule(){
		schedule.put("Work", 8);
		System.out.println(schedule);
	}

	public static void main(String[] args) {
		java_Schedule schedule = new java_Schedule();
		schedule.addToSchedule();
	}
	
}