package project3;
import java.util.Date;
public class Session implements Comparable<Session>{
	//session data
	int terminal = 0;
	Date loginTime = null;
	Date logoutTime = null;
	String username = "";
	long duration = 0;
	Record login = null;
	Record logout = null;
	//session csontructor
	public Session (Record login, Record logout) throws IllegalArgumentException {
		if (login!=null) {
			if (logout != null) {
				if (!(login.getUsername().equals(logout.getUsername()) && login.getTerminal( )== logout.getTerminal() && logout.getTime().after(login.getTime())))
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
		
		//Straightforward getter methods
	}
	public int getTerminal() {
		return this.terminal;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public String getUsername() {
		return this.username;
	}

	public long getDuration() {
		return this.duration;
	}
	
	public Record getLogout() {
		return this.logout;
	}
	
	public Record getLogin() {
		return this.login; 
	}
	//Comparison based on difference in times. Negative time means this object is before the compared one.
	public int compareTo(Session s) {
		return(int) ( this.getLoginTime().getTime()- s.getLoginTime().getTime());
	}
	//Checks if logins and logout records are equal
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
		String fduration = days + " days, " +  hours + " hours, " + minutes + " minutes, " + seconds + " seconds, ";
		//tostring method. Prints different results for user still logged in and user that has logged out.
		
		
		if (this.getLogoutTime()!=null) {
			
			String statement = "\n" + username + ", terminal " +  terminal + " duration " + duration + fduration + "\n" + " logged in: " + getLoginTime() + "\n" + " logged out: " + getLogoutTime() + "\n\n";
			return statement;
		}
		else {
			String statement =  "\n" + username + " terminal " + terminal +"\n"+ " logged in:" + getLoginTime() + "\n" + " logged out: Still logged in " + "\n\n";
			return statement;
		}
		
	}
}