package model;

import java.util.ArrayList;

import CustomException.NoUserException;
import CustomException.UserExistException;

public class ServiceCenter {
	
	//constants
	
	public static int ATTENDED=0; 
	public static int NO_ATTENDED=1;
	//attributes
	
	private char actualLetter;
	private int actualTurnNumber;
	private char lastLetterGiven;
	private int lastNumGiven;
	private int actualSerial;
	private int lastSerialGiven;
	
	//relations
	
	private ArrayList<User> users;

	
	//methods
	
	public ServiceCenter() {
		actualLetter = 'A';
		actualTurnNumber = 0;
		lastLetterGiven= 'A';
		lastNumGiven= 0;
		actualSerial = 1;
		lastSerialGiven = 1;
	}

	public int getLastSerialGiven() {
		return lastSerialGiven;
	}

	public void setLastSerialGiven(int lastSerialGiven) {
		this.lastSerialGiven = lastSerialGiven;
	}

	public int getActualSerial() {
		return actualSerial;
	}

	public void plusActualSerial() {
		actualSerial++;
	}

	public char getActualLetter() {
		return actualLetter;
	}

	public void setActualLetter(char actualLetter) {
		this.actualLetter = actualLetter;
	}

	public int getActualTurnNumber() {
		return actualTurnNumber;
	}

	public void setActualTurnNumber(int actualTurnNumber) {
		this.actualTurnNumber = actualTurnNumber;
	}

	public char getLastLetterGiven() {
		return lastLetterGiven;
	}

	public int getLastNumGiven() {
		return lastNumGiven;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void addUser(String id, String n, String l) throws UserExistException,NoUserException {
		if(searchUser(id)!=null) {
			throw new UserExistException(id);
		}
		else {
			users.add(new User(id,n,l));
		}
	}
	
	public User searchUser(String id) throws NoUserException {
		User findUser = null;
		boolean centinel = true;
		for(int i = 0;i < users.size()&&centinel;i++) {
			findUser = users.get(i).getId().contentEquals(id) ?  users.get(i) : null;
			centinel = findUser != null ? false : true;
		}
		if(findUser == null) {
			throw new NoUserException(id);
		}
		return findUser;
	}
	
	public void assignUserTurn(String id) throws NoUserException {
		lastSerialGiven =lastNumGiven > 99 && lastLetterGiven == 'Z' ? lastSerialGiven++ : lastSerialGiven;
		searchUser(id).setTurn(lastLetterGiven, lastNumGiven,lastSerialGiven);
		lastLetterGiven = lastNumGiven > 99 ? lastLetterGiven++ : lastLetterGiven;
		lastNumGiven = lastNumGiven > 99? 0 : lastNumGiven++;
	}
	
	public void addPhone(String id,String p) throws NoUserException {
		searchUser(id).setPhone(p);
	}
	
	public void addAddress(String id,String a) throws NoUserException {
		searchUser(id).setAddress(a);
	}
	
	public User searchTurnCall() {
		User userTurn = null;
		boolean centinel = true;
		for(int i = 0;i < users.size()&&centinel;i++) {
			ArrayList<Turn> t = users.get(i).getTurns();
			for(int j = 0;j < t.size();j++) {
				if(t.get(j).getLetter()==actualLetter &&t.get(j).getNum()==actualTurnNumber &&t.get(j).getSerial()==actualSerial) {
					userTurn = users.get(i);
					centinel = false;
				}
			}
		}
		return userTurn;
	}
}
