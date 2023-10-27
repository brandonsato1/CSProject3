package project3;
import java.util.Date;
public class Session implements Comparable<Session>{
	//session data
	int terminal;
	Date loginTime;
	Date logoutTime;
	String username;
	long duration;
	Record login;
	Record logout;
	//session csontructor
	public Session (Record login, Record logout) throws IllegalArgumentException {
		if (login!=null) {
			if (logout != null) {
				if (!(login.getUsername().equals(logout.getUsername()) && login.getTerminal( )== -logout.getTerminal() && logout.getTime().after(login.getTime())))
				{
					throw new IllegalArgumentException();
				}
				else {
					this.logoutTime = logout.getTime();
					//duration is time from record objects converted to miliseconds
					this.duration = logout.getTime().getTime()-login.getTime().getTime();
					this.logout = logout;
				}
				
			}
			this.loginTime = login.getTime();
			this.username = login.getUsername();
			this.terminal = login.getTerminal();
			this.login = login;
			
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	public int getTerminal() {
		return terminal;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public String getUsername() {
		return username;
	}

	public long getDuration() {
		return duration;
	}
	
	public Record getLogout() {
		return logout;
	}
	
	public Record getLogin() {
		return login; 
	}
	
	public int compareTo(Session s) {
		if (this.getLoginTime().before(s.getLoginTime()))
		return 1;
		else {
			return -1;
		}
	}
	public boolean equals(Session s) {
		if (this.getLogin().equals(s.getLogin()) && this.getLogout().equals(s.getLogout())) {
			return true;
			
		}
		else {
			return false;
		}
	}
	
	
	
	public String toString() {
		int days = (int) (duration/(24*60*60*1000));
		int hours =(int) ((duration/(60*60*1000))%24);
		int minutes =(int) ((duration/(60*1000))%60);
		int seconds =(int) ((duration/1000)%60);
		String fduration = days + " days " +  hours + " hours " + minutes + " minutes " + seconds + " seconds ";
		//tostring method. Prints different results for user still logged in and user that has logged out.
		
		
		if (this.getLogoutTime()!=null) {
			
			String statement = " Username: " + username + " terminal: " + terminal + " \n duration: " + fduration + "\n" + "Logout time: " + getLogoutTime() + "\n" + "Login time: " + getLoginTime() + "\n";
			return statement;
		}
		else {
			String statement = " Username: " + username + " terminal " + terminal + "\n" + "Login time: " + getLoginTime() + "\n" + " Still logged in " + "\n";
			return statement;
		}
		
	}
}