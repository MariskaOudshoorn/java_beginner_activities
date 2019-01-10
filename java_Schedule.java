import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class java_Schedule{

	HashMap<String, Integer> schedulemon = new HashMap<String, Integer>();
	HashMap<String, Integer> scheduletue = new HashMap<String, Integer>();
	HashMap<String, Integer> schedulewed = new HashMap<String, Integer>();
	HashMap<String, Integer> schedulethu = new HashMap<String, Integer>();
	HashMap<String, Integer> schedulefri = new HashMap<String, Integer>();

	public void addToSchedule(){
		Scanner input = new Scanner(System.in);
		String day = "sat";
		boolean correctday = false;
		do{
			System.out.println("On which day would you like to schedule something? mon/tue/wed/thu/fri");
			day = input.nextLine();
			if("mon".equals(day) || "tue".equals(day) || "wed".equals(day) || "thu".equals(day) || "fri".equals(day)){
				correctday = true;
			}
			if(correctday == false){
				System.out.println("Please choose a weekday.");
			}
		} while(correctday == false);
		System.out.println("Enter a task name:");
		String taskname = input.nextLine();
		System.out.println("Enter how long this takes in hours:");
		int tasklength = input.nextInt();
		if(day.equals("mon")){
			if(timeWarning(schedulemon, tasklength)){
				schedulemon.put(taskname, tasklength);
			}
			else{
				System.out.println("This would take too much time, it cannot be added to the schedule");
			}
		}
		else if(day.equals("tue")){
			if(timeWarning(scheduletue, tasklength)){
				scheduletue.put(taskname, tasklength);
			}
			else{
				System.out.println("This would take too much time, it cannot be added to the schedule");
			}
		}
		else if(day.equals("wed")){
			if(timeWarning(schedulewed, tasklength)){
				schedulewed.put(taskname, tasklength);
			}
			else{
				System.out.println("This would take too much time, it cannot be added to the schedule");
			}
		}
		else if(day.equals("thu")){
			if(timeWarning(schedulethu, tasklength)){
				schedulethu.put(taskname, tasklength);
			}
			else{
				System.out.println("This would take too much time, it cannot be added to the schedule");
			}
		}
		else if(day.equals("fri")){
			if(timeWarning(schedulefri, tasklength)){
				schedulefri.put(taskname, tasklength);
			}
			else{
				System.out.println("This would take too much time, it cannot be added to the schedule");
			}
		}
	}

	public void calculateDay(){
		Scanner input = new Scanner(System.in);
		System.out.println("Choose a day to calculate total amount. mon/tue/wed/thu/fri.");
		String day = input.nextLine();
		HashMap<String, Integer> chosen = new HashMap<String, Integer>();
		if(day.equals("mon")){
			chosen = schedulemon;
		}
		else if(day.equals("tue")){
			chosen = scheduletue;
		}
		else if(day.equals("wed")){
			chosen = schedulewed;
		}
		else if(day.equals("thu")){
			chosen = schedulethu;
		}
		else if(day.equals("fri")){
			chosen = schedulefri;
		}
		int sum = 0;
		for(int i: chosen.values()){
			sum += i;
		}
		System.out.println("The sum of that day is " + sum);
	}

	public int calculateWeek(){
		int sum = 0;
		for(int i: schedulemon.values()){
			sum += i;
		}
		for(int i: scheduletue.values()){
			sum += i;
		}
		for(int i: schedulewed.values()){
			sum += i;
		}
		for(int i: schedulethu.values()){
			sum += i;
		}
		for(int i: schedulefri.values()){
			sum += i;
		}
		return sum;
		
	}

	public boolean timeWarning(HashMap<String, Integer> schedule, int time){
		int weektime = calculateWeek();
		for(int i: schedule.values()){
			time += i;
		}
		if((time > 9) || (weektime > 40)){
			return false;
		}
		return true;
	}

	public void printWeek(){
		System.out.println("mon");
		System.out.println(schedulemon);
		System.out.println("tue");
		System.out.println(scheduletue);
		System.out.println("wed");
		System.out.println(schedulewed);
		System.out.println("thu");
		System.out.println(schedulethu);
		System.out.println("fri");
		System.out.println(schedulefri);
	}

	public void schedularProgram(){
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to schedular");
		boolean running = true;
		int number = 0;
		while(running){
			System.out.println("What would you like to do. 1.Add task, 2.Sum of day, 3.Sum of week, 4.Print schedule 7.Exit");
			while(!input.hasNextInt()){
				System.out.println("Please enter a number.");
				input.next();
			}
			number = input.nextInt();
			switch(number){
				case 1: addToSchedule();
					break;
				case 2: calculateDay();
					break;
				case 3: System.out.println("The sum of the week is " + calculateWeek());
					break;
				case 4: printWeek();
					break;
				case 7: running = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		java_Schedule schedule = new java_Schedule();
		schedule.schedularProgram();	
	}
	
}