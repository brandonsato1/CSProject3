package project3;
import java.io.File;
import java.util.Date;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LoginStats{
	public static void main(String[] args) throws IllegalArgumentException {
		if (args.length == 0 ) {
			System.err.println("File name expected.\n");
			System.exit(1);
		}

		File file = new File(args[0]);
		if (!file.exists()){
			System.err.println("File does not exist.\n");
			System.exit(1);
		}
		if (!file.canRead()){
			System.err.println("File cannot be read");
			System.exit(1);
		}
		Scanner inFile = null;	
		try {
			inFile = new Scanner (file ) ;
		} catch (FileNotFoundException e) {
			System.err.println("File cannot be opened for reading");
			System.exit(1);
		}
		RecordList list = new RecordList();
		String line = null;
		Scanner parseLine = null;
		int terminal = 0;
		long time = 0;
		String name = null;
		//loop that reads all the text file data
		while (inFile.hasNextLine()) {
			try { 
				line = inFile.nextLine(); 
				parseLine = new Scanner(line);
				parseLine.useDelimiter(" "); 
				terminal = Integer.parseInt(parseLine.next());
				time = Long.parseLong(parseLine.next());
				name = parseLine.next();
			}
			catch (Exception e){
				System.err.println(line);
				continue;
			}
			try {
				Record current = new Record(Math.abs(terminal),terminal>0,name, new Date(time));
				list.add(  current  ); 
			}
			catch (IllegalArgumentException ex ) {
				System.out.println("Record inputted improperly");
			} 
		}
		System.out.println("Data successfully loaded");
		
		Scanner userinput = new Scanner(System.in);
		String userValue = "";
		
		//as long as user doesn't quit, loop will keep running
		while (!userValue.equalsIgnoreCase("quit")) {
			try {
				System.out.println("Options: \n first USERNAME: fetch data for first login \n last USERNAME: fetch data for most recent login \n all USERNAME: fetch all data for username \n total USERNAME: fetch total time spent \n quit: end the program");
				userValue = userinput.nextLine();
			if (userValue.equalsIgnoreCase("quit")) {
				System.out.println("...quitting...");
				System.exit(1);
			}
			else if (userValue.substring(0,3).equalsIgnoreCase("all")) {
				String username = userValue.substring(4,userValue.length()).trim();
				System.out.println(list.getAllSessions(username));
			}
			else if (userValue.substring(0,4).equalsIgnoreCase("last")){
				String username = userValue.substring(5,userValue.length()).trim();
				System.out.println(list.getLastSession(username));
			}
			else if (userValue.substring(0,5).equalsIgnoreCase("first")) {
				String username = userValue.substring(6,userValue.length()).trim();
				System.out.println(list.getFirstSession(username));
			}
			
			else if (userValue.substring(0,5).equalsIgnoreCase("total")) {
				String username = userValue.substring(6,userValue.length()).trim();
				long duration = list.getTotalTime(username);
				int days = (int) (duration/(24*60*60*1000));
				int hours =(int) ((duration/(60*60*1000))%24);
				int minutes =(int) ((duration/(60*1000))%60);
				int seconds =(int) ((duration/1000)%60);
				
				String fduration = days + " days " +  hours + " hours " + minutes + " minutes " + seconds + " seconds ";
				
				System.out.println("Username: "+username+ " Duration: " +fduration);
			}
			else {
				System.out.println("Invalid input. Please enter one of the valid commands");
			}}
			catch(IllegalArgumentException e) {
				System.out.println("Illegal argument");
			}
			catch(NoSuchElementException g) {
				System.out.println("Invalid element detected");
				
			}
		}
				
	}
}