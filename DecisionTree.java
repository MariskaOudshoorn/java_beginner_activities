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
		String name, question, origin, destination, answer;
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

	public String findNextNode(String currentnode, String yesno){
		String nextNode = "None";
		for(int i = 0; i < edges.size(); i++){
			if((currentnode.equals(edges.get(i).origin)) && (yesno.equals(edges.get(i).answer))){
					nextNode = edges.get(i).destination;
			}
		}
		return nextNode;
	}

	public String userInput(){
		Scanner input = new Scanner(System.in);
		Boolean goodAnswer = false;
		String answer = "None";
		while(!goodAnswer){
			System.out.println("Antwoord met Ja of Nee.");
			answer = input.nextLine();
			if((answer.equals("ja")) || (answer.equals("Ja"))){
				answer = "Ja";
				goodAnswer = true;
			}
			else if((answer.equals("nee")) || (answer.equals("Nee"))){
				answer = "Nee";
				goodAnswer = true;
			}
		}
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
	}

	public int getNodeIndex(String currentnode){
		int nodeIndex = 0;
		for(int i = 0; i < nodes.size(); i++){
			if(currentnode.equals(nodes.get(i).name)){
				nodeIndex = i;
			}
		}
		return nodeIndex;
	}

	public Boolean endNode(String currentnode){
		Boolean thisIsLastNode = true;
		for(int i = 0; i < edges.size(); i++){
			if(currentnode.equals(edges.get(i).origin)){
				thisIsLastNode = false;
			}
		}
		return thisIsLastNode;
	}

	public void runTree(){
		readFile();
		String currentnode,answer;
		currentnode = findFirstNode();
		int nodeIndex = 0;
		do{
			nodeIndex = getNodeIndex(currentnode);
			System.out.println(nodes.get(nodeIndex).question);
			if(endNode(currentnode)){
				break;
			}
			answer = userInput();
			currentnode = findNextNode(currentnode, answer);
		}while(!currentnode.equals("None"));
		System.out.println("Je bent bij het einde van de boom, als het goed is weet je nu van welke boom dit blad komt.");
	}

	public static void main(String[] args) {
		DecisionTree tree = new DecisionTree();
		tree.runTree();
	}
}