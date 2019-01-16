import java.util.*;

public class java_Roborally{
	public static int x;
	public static int y;
	public static String facing;
	public static ArrayList<String> toExecute = new ArrayList<String>();
	public static ArrayList<Integer> moveList = new ArrayList<Integer>();
	public static int executeCounter = 0;

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
		toExecute.add("turnLeft");
	}

	public void executeTurnLeft(){
		if(facing == "North"){
			facing = "West";
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
		toExecute.add("turnRight");
	}

	public void executeTurnRight(){
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
		toExecute.add("forward");
		moveList.add(speed);

	}

	public void executeForward(int speed){
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
		toExecute.add("backward");
		moveList.add(speed);
	}

	public void executeBackward(int speed){
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

	public void execute(){
		int size = toExecute.size();
		for(int i = 0; i < toExecute.size(); i++){
			switch(toExecute.get(i)){
				case "turnLeft" : executeTurnLeft();
					break;
				case "turnRight" : executeTurnRight();
					break;
				case "forward" : executeForward(moveList.get(executeCounter));
					executeCounter++;
					break;
				case "backward" : executeBackward(moveList.get(executeCounter));
					executeCounter++;
					break;
			}
		}
	}
	
	public static void main(String[] args){
		java_Roborally roborally = new java_Roborally();
		java_Roborally second_robot = new java_Roborally(0, 1, "North");
		System.out.println("We create a 'second_robot', X: " + second_robot.x + " Y: " + second_robot.y + " facing: " + second_robot.facing);
		System.out.println("second_robot.turnLeft();");
		second_robot.turnLeft();
		System.out.println("second_robot.turnLeft();");
		second_robot.turnLeft();
		System.out.println("second_robot.forward(2);");
		second_robot.forward(2);
		System.out.println("second_robot.turnRight();");
		second_robot.turnRight();
		System.out.println("second_robot.execute();");
		second_robot.execute();
		System.out.println("After execute: X " + second_robot.x + " Y " + second_robot.y + " facing " + second_robot.facing);

	}
}