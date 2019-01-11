import java.util.ArrayList;
import java.util.List;
public class java_Roborally{
	int x;
	int y;
	String facing;
	ArrayList toExecute = new ArrayList();
	int executeCounter = 0;

	public java_Roborally(){
		x = 0;
		y = 0;
		facing = "North";
	}

	public java_Roborally(int horizontal, int vertical, String pointing){
		x = horizontal;
		y = vertical;
		facing = pointing;
	}

	public void turnLeft(){
		if(facing == "North"){
			toExecute.add("West");
			// problems with to add: Note: java_Roborally.java uses unchecked or unsafe operations. Note: Recompile with -Xlint:unchecked for details.
		}
		else if(facing == "East"){
			facing = "North";
		}
		else if(facing == "South"){
			facing = "East";
		}
		else if(facing == "West"){
			facing = "South";
		}
	}

	public void turnRight(){
		if(facing == "North"){
			facing = "East";
		}
		else if(facing == "East"){
			facing = "South";
		}
		else if(facing == "South"){
			facing = "West";
		}
		else if(facing == "West"){
			facing = "North";
		}
	}

	public void forward(int speed){
		if(speed == 0){
			speed = 1;
		}
		if(facing == "North"){
			y += speed;
		}
		else if(facing == "East"){
			x += speed;
		}
		else if(facing == "South"){
			y -= speed;
		}
		else if(facing == "West"){
			x -= speed;
		}
	}

	public void backward(int speed){
		if(speed == 0){
			speed = 1;
		}
		if(facing == "North"){
			y -= speed;
		}
		else if(facing == "East"){
			x -= speed;
		}
		else if(facing == "South"){
			y += speed;
		}
		else if(facing == "West"){
			x += speed;
		}
	}

	public void addToList(){

	}
	
	public static void main(String[] args){
		java_Roborally roborally = new java_Roborally();
		java_Roborally second_robot = new java_Roborally(0, 1, "East");
		System.out.println("second robot turns twice: X " + second_robot.x + " Y " + second_robot.y + " facing " + second_robot.facing);
		second_robot.turnLeft();
		second_robot.turnLeft();
		System.out.println("X " + second_robot.x + " Y " + second_robot.y + " facing " + second_robot.facing);
		second_robot.forward(0);
		System.out.println("X " + second_robot.x + " Y " + second_robot.y + " facing " + second_robot.facing);

	}
}