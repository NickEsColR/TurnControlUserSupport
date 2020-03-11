package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import CustomException.NoUserException;
import CustomException.UserExistException;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class ServiceCenter {
	
	//constants
	
	public static int ATTENDED=0; 
	public static int NO_ATTENDED=1;
	public static String IDS = "data"+System.getProperty(File.separator)+"ids.turn";
	public static String NAMES = "data"+System.getProperty(File.separator)+"names.turn";
	public static String LAST_NAMES = "data"+System.getProperty(File.separator)+"lastn.turn";
	
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
		users = new ArrayList<User>();
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

	public void addUser(String id, String n, String l) throws UserExistException {
		User u = null;
		try{
			u = searchUser(id);
		}catch(NoUserException e) {
			users.add(new User(id,n,l));
		}
		if(u  != null) {	
			throw new UserExistException(id);
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
		lastSerialGiven =lastNumGiven == 99 && lastLetterGiven == 'Z' ? ++lastSerialGiven : lastSerialGiven;
		searchUser(id).setTurn(lastLetterGiven, lastNumGiven,lastSerialGiven);
		if(lastNumGiven == 99) {
			lastLetterGiven= lastLetterGiven == 'Z' ? 'A' : ++lastLetterGiven;
		}
		lastNumGiven = lastNumGiven == 99? 0 : ++lastNumGiven;
	}
	
	public void addPhone(String id,String p) throws NoUserException {
		searchUser(id).setPhone(p);
	}
	
	public void addAddress(String id,String a) throws NoUserException {
		searchUser(id).setAddress(a);
	}
	
	public Turn searchTurnCall() {
		Turn userTurn = null;
		boolean centinel = true;
		for(int i = 0;i < users.size()&&centinel;i++) {
			ArrayList<Turn> t = users.get(i).getTurns();
			for(int j = 0;j < t.size();j++) {
				if(t.get(j).getLetter()==actualLetter &&t.get(j).getNum()==actualTurnNumber &&t.get(j).getSerial()==actualSerial) {
					userTurn = users.get(i).getTurns().get(j);
					centinel = false;
				}
			}
		}
		return userTurn;
	}
	
	public String advanceTurn(int r) {
		String msg = "";
		Turn t= searchTurnCall();
		if(t!=null) {
			t.setAttended(r);
			actualSerial =actualTurnNumber == 99 && actualLetter == 'Z' ? ++actualSerial : actualSerial;
			if( actualTurnNumber == 99) {
				actualLetter= actualLetter == 'Z' ? 'A' : ++actualLetter;
			}
			actualTurnNumber = actualTurnNumber == 99? 0 : ++actualTurnNumber;
			if(actualTurnNumber < 10) {
				msg = actualLetter + "0"+actualTurnNumber;
			}
			else {
				msg = actualLetter + Integer.toString(actualTurnNumber);
			}
		}else {
			msg = "No user with the next turn";
		}
		return msg;
	}
	
	public String printTurn() {
		String msg = "";
		if(actualTurnNumber < 10) {
			msg = actualLetter + "0"+actualTurnNumber;
		}
		else {
			msg = actualLetter + Integer.toString(actualTurnNumber);
		}
		return msg;
	}
	
	/**
	 * <b>Descrpition:</b> generate random user from a file<br>
	 * <b>pre:</b> the quantity is low than the ids in the file ids<br>
	 * @param q is the quantity of users to be created<br>
	 * @throws IOException is an error with BufferedReader <br>
	 */
	public void generateRandomUsers(int q) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(IDS));
		String [] ids = br.readLine().split(" ");
		br.close();
		br = new BufferedReader(new FileReader(NAMES));
		String[] names = br.readLine().split(" ");
		br.close();
		br = new BufferedReader(new FileReader(LAST_NAMES));
		String[] lastn = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < q;i++) {
			Random randomNum = new Random(ids.length);
			String id = ids[randomNum.nextInt()];
			randomNum.setSeed(names.length);
			String name = names[randomNum.nextInt()];
			randomNum.setSeed(lastn.length);
			String last = lastn[randomNum.nextInt()];
			try {
				addUser(id,name,last);
			}catch(UserExistException e) {
				i--;
			}
		}
	}
}
