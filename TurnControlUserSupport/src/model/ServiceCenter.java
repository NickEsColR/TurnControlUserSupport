package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
	
	private User[] users;
	private ArrayList<TurnType> tt;
	private ProgramDatee pd;

	
	//methods
	
	public ServiceCenter(int q) throws IOException {
		actualLetter = 'A';
		actualTurnNumber = 0;
		lastLetterGiven= 'A';
		lastNumGiven= 0;
		actualSerial = 1;
		lastSerialGiven = 1;
		users = new User[q];
		generateRandomUsers(q);
		tt = new ArrayList<TurnType>();
		pd = new ProgramDatee();
	}

	public ArrayList<TurnType> getTt() {
		return tt;
	}
	
	public void setTt(String na, float t) {
		tt.add(new TurnType(na,t));
	}
	public ProgramDatee getPd() {
		return pd;
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

	public User[] getUsers() {
		return users;
	}

	
	
	public User searchUser(String id) throws NoUserException {
		User findUser = null;
		boolean centinel = true;
		for(int i = 0;i < users.length&&centinel;i++) {
			findUser = users[i].getId().equals(id) ?  users[i] : null;
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
		for(int i = 0;i < users.length&&centinel;i++) {
			ArrayList<Turn> t = users[i].getTurns();
			for(int j = 0;j < t.size();j++) {
				if(t.get(j).getLetter()==actualLetter &&t.get(j).getNum()==actualTurnNumber &&t.get(j).getSerial()==actualSerial) {
					userTurn = users[i].getTurns().get(j);
					centinel = false;
				}
			}
		}
		return userTurn;
	}
	
	public String advanceTurn() {
		String msg = "";
		
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
	/**
	 * <b> Description: <br> generate a report of all users have the turn of parameters <br>
	 * @param l is the letter of the turn for search<br>
	 * @param n is the number of the turn for search, if n < 10 only appear 1 digit <br>
	 * @return msg is a message of all users that have that code<br>
	 */
	public String generateReportSameTurns(char l, int n) {
		String msg = "";
			for(int i = 0; i < users.length;i++) {
				if(users[i].hasTurn(l, n)) {
					msg += users[i];
					msg += "\n";
				}
			}
		return msg;
	}
	
	/**
	 * <b>Description:</b> sort the users on ascending order with id<br>
	 * <b>Pre:</b> users != null<br>
	 * <b>Pos:</b> the users will be sort successfully<br>
	 */
	public void sortUserById() {
		Arrays.sort(users);
	}
	/**
	 * <b>Description:</b> sort the users on ascending order with last name<br>
	 * <b>Pre:</b> users != null<br>
	 * <b>Pos:</b> the users will be sort successfully<br>
	 */
	public void sortUserByLastName() {
		Comparator<User> userComparator = new UserLastNameComparator();
		Arrays.sort(users,userComparator);
	}
	/**
	 * <b>Description:</b> sort the users on descending order with id<br>
	 * <b>Pre:</b> users != null<br>
	 * <b>Pos:</b> the users will be sort successfully<br>
	 */
	public void sortUserByIdDescending() {
		Arrays.sort(users,Collections.reverseOrder());
	}
	/**
	 * <b>Description:</b> sort the users on ascending order with name<br>
	 * <b>Pre:</b> users != null<br>
	 * <b>Pos:</b> the users will be sort successfully<br>
	 */
	public void sortUserByName() {
		Arrays.sort(users, new Comparator <User>() {

			@Override
			public int compare(User u1, User u2) {
				return u1.getName().compareTo(u2.getName());
			}
			
		});
	}
	
	/**
	 * <b>Description:</b> show the program date<br>
	 * @return the date of the program<br>
	 */
	
	public String showDate() {
		return pd.ShowDate();
	}
	
	/**
	 * <b>Description:</b> modify the date of the program<br>
	 * @param d is the day <br>
	 * @param m is the month<br>
	 * @param y is the year<br>
	 * @param h is the hour<br>
	 * @param min is the minutes<br>
	 * @param s is the seconds<br>
	 */
	
	public void modifyDate(int d, int m, int y, int h, int min, int s) {
		pd.ModifyDate(d, m, y, h, min, s);
	}
	
	/**
	 * <d>Description:</b> sort turn type with time<br>
	 */
	
	public void sortTurnTypeByTime() {
		for(int i = 0; i < tt.size();i++) {
			TurnType min = tt.get(i);
			int posMin = 0;
			for(int j = i; i < tt.size()-1;i++) {
				if(tt.get(j).getTime()< min.getTime()) {
					min = tt.get(j);
					posMin = j;
				}
			}
			tt.add(posMin, tt.get(i));
			tt.add(i,min);
		}
	}

}
