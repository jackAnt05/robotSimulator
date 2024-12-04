import java.io.File;
import java.util.Scanner;
//Written by Jackson Antonelli
public class robotSimulator {
	//Creates all of the vars we will need throughout the project
	public static String[] board = new String[100];
	public static QueueI<String> commands;
	public static String s;
	public static Boolean gameOn = true;
	public static Scanner keyboard = new Scanner(System.in);
	//main method this runs the game and calls on methods to interact with the user.
	public static void main(String[] args) {
		while(gameOn) {
		System.out.println("Welcome to the Robot Simulator");
		System.out.println("Enter file for the Board");
		String boardFile = keyboard.nextLine();
		System.out.println("Enter file for Robot Commands");
		String robotFile = keyboard.nextLine();
		board = readBoardFile(boardFile);
		printFirstBoard(board);
		System.out.println("\n Simulation begin");
		commands = readRobotFile(robotFile);
		runCommands(commands,board);
		System.out.println("\n Simulation end");
		System.out.println("Quit? Enter 'true' to quit or hit enter to run another simulation");
		String input = keyboard.nextLine();
		if(input.equalsIgnoreCase("true")) {
			System.out.println("Goodbye!");
			gameOn = false;
		}
		
		
	}
	}
	// this method reads the file and returns an array of the board
	public static String[] readBoardFile(String fileName) {
		int count = 0;
		try
		{
			Scanner fileScanner = new Scanner(new File(fileName));
			while(fileScanner.hasNextLine())
			{
				String fileLine = fileScanner.nextLine();
				String[] splitLines = fileLine.split("");
				if(splitLines.length != 10)
					continue;
				for(int i = 0; i < 10; i++) {
					board[count+i] = splitLines[i];
				}
				count+=10;
			}
			fileScanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return board;
	}
	//this is a special method I use once to place the O on the board.
	public static void printFirstBoard(String[] board) {
		board[0] = "O";
		for(int i=0;i<100;i=i+10) {
			System.out.print("\n");
			for(int j = 0;j<10;j++) {
				System.out.print(board[i+j]);
			}
		}
	}
	
	public static void printBoard(String[] board) {
		for(int i=0;i<100;i=i+10) {
			System.out.print("\n");
			for(int j = 0;j<10;j++) {
				System.out.print(board[i+j]);
			}
		}
	}
	// this reads the robot file and adds the commands to the queue
	public static QueueI<String> readRobotFile(String robotFile) {
		int count = 0;
		commands = new ArrayQueue(26);
		try
		{
			Scanner fileScanner = new Scanner(new File(robotFile));
			while(fileScanner.hasNextLine())
			{
				String fileLine = fileScanner.nextLine();
				commands.enqueue(fileLine);
				
			}
			fileScanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return commands;
	}
	//finds the character on the board
	public static int findO(String[] board) {
		int index = 0;
		for(int i=0; i<board.length;i++) {
			if(board[i].equalsIgnoreCase("o")) {
				index = i;
			}
		}
		return index;
	}
	//runs the commands and removes them from the queue
	public static void runCommands(QueueI<String> commands, String[] board) {
		int index;
		String s;
		for(int i=0; i<26;i++) {
			s= commands.dequeue();
			 if(s == null) {
				 break;
			 }
			 else if(s.equals("Move Right")) {
				 index = findO(board);
				 if(board[index+1].equals("X")) {
					 System.out.print("CRASH!");
					 break;
				 }
				 board[index+1] = "O";
				 board[index] = "_";
			 }
			 else if(s.equals("Move Down")) {
				 index = findO(board);
				 board[index+10] = "O";
				 board[index] = "_";
			 }
			 else if(s.equals("Move Left")) {
				 index = findO(board);
				 board[index-1] = "O";
				 board[index] = "_";
			 }
			 else if(s.equals("Move Up")) {
				 index = findO(board);
				 board[index-10] = "O";
				 board[index] = "_";
			 }
			 System.out.println("\n Command "+i);
			 printBoard(board);
	
			
		}
	}
	
	
	

}
