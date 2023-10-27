package project3;
import java.util.NoSuchElementException;

public class RecordList extends SortedLinkedList<Record>

{
	public Session getFirstSession(String user) throws IllegalArgumentException, NoSuchElementException{
		if (user == null || user.equals(""))
			throw new IllegalArgumentException();
		boolean found = false;
		Record login = null;
		Record logout = null;
		//loop that iterates from start to finish to find equal logins and terminals
		for (Record x : this) {
			if (x.getUsername().equals(user)) {
				if (x.isLogin() && found == false)
				{
					found = true;
					login = x;
				}
				else if (x.isLogout() && found == true && login.getTerminal()==x.getTerminal()) {
					logout = x;
					break;
					//Checks for login then logout, then breaks the loop once both of them are found.
				}
			}
		}
		if (login == null) 
			throw new NoSuchElementException();
		return new Session(login,logout);
		
	} 
	public Session getLastSession(String user) throws IllegalArgumentException, NoSuchElementException {
		if (user == null || user.equals(" "))
			throw new IllegalArgumentException();
		boolean found = false;
		Record login = null;
		Record logout = null;
		//loop that iterates from back to front for equal terminals and usernames
		
		for (int i=this.size()-1; i>=0; i--) {
			if (this.get(i).getUsername().equals(user)){
				if (this.get(i).isLogout() && found == false) {
					logout = this.get(i);
					found = true;
				}
				else if (this.get(i).isLogin()) {
					if (found == true) {
						if (this.get(i).getTerminal()!=logout.getTerminal())
							logout = null;
					}
					login = this.get(i);
					break;
				}//Similar to getFirstSession, but works backward annd breaks once a login is found.
			}
		}
		if (login == null) 
			throw new NoSuchElementException();
		return new Session(login,logout);
	}
	
	public long getTotalTime(String user) throws NoSuchElementException, IllegalArgumentException {
		
		if (user == null || user.trim().equals(""))
			throw new IllegalArgumentException();
		boolean found = false;
		long totalTime = 0;
		for (int x = 0; x<this.size(); x++) {
			if (this.get(x).isLogin()&& this.get(x).getUsername().equals(user)) {
				for (int i=0; i<this.size(); i++) {
					if (this.get(i).isLogout() && this.get(i).getTerminal()==this.get(x).getTerminal() && this.get(i).getUsername().equals(user)){
						totalTime += this.get(i).getTime().getTime() - this.get(x).getTime().getTime();
						found = true;
						break;
					}
				}
			}
		}
		if (found == false) {
			throw new NoSuchElementException();
		}
		return totalTime;
		
	}
	public SortedLinkedList<Session> getAllSessions(String user) throws NoSuchElementException, IllegalArgumentException {
		SortedLinkedList<Session> sessions = new SortedLinkedList<Session>();
		try {
		if (user == null || user.trim().equals(""))
			throw new IllegalArgumentException();
		for (int x=0; x<this.size(); x++) {
			if (this.get(x).isLogin() && this.get(x).getUsername().equals(user)){
				Record login = this.get(x);
				Record logout = null;
				for (int i = 0; i<this.size(); i++) {
					if (this.get(i).getUsername().equals(user) && this.get(i).isLogout() && this.get(i).getTerminal()==this.get(x).getTerminal()) {
						logout = this.get(i);
						break;
					}
				}
				sessions.add(new Session(login,logout));
			}
		}
		if (sessions.size() == 0) {
			throw new NoSuchElementException();
		}
		}
		catch(Exception e) {
			System.out.println("Null value detected");
		}
		return sessions;
	}
	
	public String toString() {
		for (int x = 0; x<this.size(); x++) {
			System.out.println(get(x));
		}
		return "";
	}
}