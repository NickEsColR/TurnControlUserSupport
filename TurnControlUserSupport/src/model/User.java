package model;

public class User {
	
	//attributes
	
	private String id;
	private String name;
	private String lastName;
	private String address;
	private String phone;
	
	//relations
	
	private Turn turn;

	
	//methods
	
	public User(String id, String n, String l) {
		this.id = id;
		name = n;
		lastName = l;
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


	public Turn getTurn() {
		return turn;
	}


	public void setTurn(char l,int n) {
		turn = new Turn(l,n);
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
		return "User name " + name + ", lastName=" + lastName + ", with turn=" + turn;
	}
	
}
