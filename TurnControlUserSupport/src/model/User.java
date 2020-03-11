package model;

import java.util.ArrayList;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

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
	
	/**
	* <b>Descrpition:</b> constructor of a user<br>
	* @param id is the user's identification <br>
	* @param n is the user's name <br>
	* @param l is the user´s last name<br>
	*/
	
	public User(String id, String n, String l) {
		this.id = id;
		name = n;
		lastName = l;
		address = "";
		phone = "";
		turns = new ArrayList<Turn>();
	}

	/**
	* <b>Description:</b> get the address  of a user<br>
	* @return address is the address of a user<br>
	*/
	
	public String getAddress() {
		return address;
	}

	/**
	* <b>Description:</b> ser the address  of a user<br>
	* @param address is the address of a user<br>
	*/
	
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	* <b>Description:</b> get the phone of a user<br>
	* @return phone is a phone number of a user<br>
	*/
	
	public String getPhone() {
		return phone;
	}

	/**
	* <b>Description:</b> ser the phone of a user<br>
	* @param phone is a phone number of a user<br>
	*/
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	* <b>Description:</b> get the turns of a user<br>
	* @return turns is a list of turns <br>
	*/
	
	public ArrayList<Turn> getTurns() {
		return turns;
	}
	
	/**
	* <b>Descrpition:</b> add a new turn of a user<br>
	* @param l is the turn's letter <br>
	* @param n is the turn's number <br>
	* @param s is the turn's serial<br>
	*/
	
	public void setTurn(char l,int n, int s) {
		turns.add( new Turn(l,n,s));
	}

	/**
	* <b>Description:</b> get the id of a user<br>
	* @return id is the user's identification<br>
	*/
	
	public String getId() {
		return id;
	}
	
	/**
	* <b>Description:</b> get the name of a user<br>
	* @return name is the user´s name<br>
	*/
	
	public String getName() {
		return name;
	}

	/**
	* <b>Description:</b> get the last name of a user<br>
	* @return lastName is the user´s last name<br>
	*/	
	
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * <b> Description: </b> look if an user has the same turn given on parameters<br>
	 * @param l is the letter of the turn<br>
	 * @param n is the number of the turn<br>
	 * @return has is a boolean says if the user has a turn or doesn't<br>
	 */
	public Boolean hasTurn(char l,int n) {
		boolean has = false;
		for(int i = 0; i < turns.size() && !has;i++) {
			if(turns.get(i).getLetter() == l && turns.get(i).getNum() == n) {
				has = true;
			}
		}
		return has;
	}
	/**
	 * <b>Description:</b> use method compareTo on id attribute<br>
	 * @param u is a user for compare with the actual<br>
	 * @return a number of the difference between both<br>
	 */
	public int compareTo(User u) {
		return id.compareTo(u.id);
	}
	@Override
	public String toString() {
		return "User name " + name + ", lastName " + lastName;
	}
	
}
