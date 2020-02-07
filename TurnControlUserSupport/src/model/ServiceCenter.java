package model;

import java.util.ArrayList;

import CustomException.NoUserException;

public class ServiceCenter {
	
	//attributes
	
	private char actualLetter;
	private int actualTurnNumber;
	private String lastTurnGive;
	
	//relations
	
	private ArrayList<User> users;
	
	//methods
	
	public ServiceCenter() {
		actualLetter = 'A';
		actualTurnNumber = 0;
		lastTurnGive = " ";
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

	public String getLastTurnGive() {
		return lastTurnGive;
	}

	public void setLastTurnGive(String lastTurnGive) {
		this.lastTurnGive = lastTurnGive;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void addUser(String id, String n, String l) {
		users.add(new User(id,n,l));
	}
	
	public User searchUser(String id) throws NoUserException {
		User findUser = null;
		for(int i = 0;i < users.size();i++) {
			findUser = users.get(i).getId().contentEquals(id) ?  users.get(i) : null;
		}
		if(findUser == null) {
			throw new NoUserException(id);
		}
		return findUser;
	}
	
}
