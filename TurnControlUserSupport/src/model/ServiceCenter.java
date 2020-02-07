package model;

import java.util.ArrayList;

import CustomException.NoUserException;

public class ServiceCenter {
	
	//attributes
	
	private char actualLetter;
	private int actualTurnNumber;
	private char lastLetterGiven;
	private int lastNumGiven;
	
	//relations
	
	private ArrayList<User> users;
	
	//methods
	
	public ServiceCenter() {
		actualLetter = 'A';
		actualTurnNumber = 0;
		lastLetterGiven= 'A';
		lastNumGiven= 0;
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
	
	public void assignUserTurn(String id) throws NoUserException {
		User user = searchUser(id);
		user.setTurn(lastLetterGiven, lastNumGiven);
		lastLetterGiven = lastNumGiven > 99 ? lastLetterGiven++ : lastLetterGiven;
		lastNumGiven = lastNumGiven > 99? 0 : lastNumGiven++;
	}
}
