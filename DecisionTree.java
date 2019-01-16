import java.util.*;
import java.io.*;
import java.lang.String;

public class DecisionTree{

	public ArrayList<Node> nodes = new ArrayList<>();
	public ArrayList<Edge> edges = new ArrayList<>();


	public void readFile(){
		try{
			Scanner file = new Scanner(new File("C:\\Users\\moudshoorn\\java-opdrachten-code\\src\\main\\resources\\intermediate\\decision-tree-data.txt"));
			while(file.hasNextLine()){
				String line = file.nextLine();
				processFile(line);
			}
		}
		catch(FileNotFoundException exception){
			System.out.println("File not found");
		}
	}

	public void processFile(String incomingLine){
		ArrayList<String> newLine = new ArrayList<String>(Arrays.asList(incomingLine.split(", ")));
		String name;
		String question;
		String origin;
		String destination;
		String answer;
		if(newLine.size() == 2){
			name = newLine.get(0);
			question = newLine.get(1);
			Node node = new Node(name, question);
			nodes.add(node);
		}
		else{
			origin = newLine.get(0);
			destination = newLine.get(1);
			answer = newLine.get(2);
			Edge edge = new Edge(origin, destination, answer);
			edges.add(edge);
		}
	}

	public String findNextNode(String currentNode, String answer){
		String nextNode = "None";
		for(int i = 0; i < edges.size(); i++){
			if(currentNode.equals(edges.get(i).origin)){
				if(answer.equals("Nee")){
					nextNode = edges.get(i).destination;
					//Verschil maken in nextnode voor antwoord ja of nee. 
				}
			}
		}
		return nextNode;
	}

	public String userInput(){
		Scanner input = new Scanner(System.in);
		String answer = input.nextLine();
		return answer;
	}

	public String findFirstNode(){
		int counter = 0;
		String firstNode = "None";
		for(int i = 0; i < nodes.size(); i++){
			for(int j = 0; j < edges.size(); j++){
				if(nodes.get(i).name.equals(edges.get(j).destination)){
					counter++;
				}
			}
			if(counter == 0){
				firstNode = nodes.get(i).name;
			}
			counter = 0;
		}
		return firstNode;

		// De naam van de node die niet in de destinationlijst voorkomt. 
	}

	public void checkThings(){

	}

	public static void main(String[] args) {
		DecisionTree tree = new DecisionTree();
		tree.readFile();
		String firstnode = "none";
		firstnode = tree.findFirstNode();
		String nextnode = tree.findNextNode(firstnode, Nee);
		System.out.println(nextnode);
	}
}