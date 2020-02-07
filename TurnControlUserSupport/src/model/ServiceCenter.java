package model;

import java.util.ArrayList;

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

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
}
