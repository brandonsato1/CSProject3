package project3;
import java.util.Date;
public class Record implements  Comparable<Record>{
	//data
	private String username;
	private Date time; 
	private int terminal;
	private boolean login;
	
	//constructor for record object
	public Record(int terminal, boolean login, String username, Date time)throws IllegalArgumentException {
		this.username = username;
		this.time = time;
		this.login = login;
		if (terminal <=0)
			throw new IllegalArgumentException();
		this.terminal = terminal;
		
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
		return(int) ( this.getTime().getTime()- o.getTime().getTime());
	}
	public boolean equals(Record o) {
		if (this.getUsername().equalsIgnoreCase(o.getUsername()) && this.isLogin()==o.isLogin() && this.getTime().equals(o.getTime()) && this.getTerminal() == o.getTerminal()) {
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