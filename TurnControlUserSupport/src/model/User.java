package model;

import java.util.ArrayList;

public class User {
	
	//attributes
	
	private String id;
	private String name;
	private String lastName;
	private String address;
	private String phone;
	
	//relations
	
	private ArrayList<Turn> turns;

	
	//methods
	
	public User(String id, String n, String l) {
		this.id = id;
		name = n;
		lastName = l;
		turns = new ArrayList<Turn>();
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public ArrayList<Turn> getTurns() {
		return turns;
	}


	public void setTurn(char l,int n, int s) {
		turns.add( new Turn(l,n,s));
	}


	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getLastName() {
		return lastName;
	}


	@Override
	public String toString() {
		return "User name " + name + ", lastName=" + lastName;
	}
	
}
