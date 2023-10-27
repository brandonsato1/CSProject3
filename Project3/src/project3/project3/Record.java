package project3;
import java.util.Date;
public class Record implements  Comparable<Record>{
	//data
	private String username;
	private Date time; 
	private int terminal;
	private boolean login;
	
	//constructor for record object
	public Record(int terminal, boolean login, String username, Date time) {
		this.username = username;
		this.time = time;
		this.terminal = terminal;
		this.login = login;
		
		
	}
	public int getTerminal() {
		return terminal;
	}

	public boolean isLogin() {
		return login;
	}

	public boolean isLogout() {
		return !login;
	}

	public String getUsername() {
		return username;
		
	}

	public Date getTime() {
		return time;
		
	}
	@Override
	public int compareTo(Record o) {
		if (this.getTime().before(o.getTime())) {
			return 1;
			}
		else {
			return -1;
		}
	}
	
	public boolean equals(Record o) {
		if (this.getUsername().equalsIgnoreCase(username) && this.isLogin()==o.isLogin() && this.getTime().equals(o.getTime()) && this.getTerminal() == o.getTerminal()) {
			return true;
		}
		else {
			return false;
		}
	}
	public String toString() {
		return "Username: "+this.username + "Terminal" + this.terminal + "time" + time;
	}
	
	
	
}