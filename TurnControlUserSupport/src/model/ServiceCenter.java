package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import CustomException.NoUserException;
import CustomException.UserExistException;
import CustomException.UserNameNotFoundException;
import CustomException.UserNotFoundException;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class ServiceCenter implements Serializable{
	
	//constants
	
	
	public static String IDS = "data"+System.getProperty(File.separator)+"ids.text";
	public static String NAMES = "data"+System.getProperty(File.separator)+"names.text";
	public static String LAST_NAMES = "data"+System.getProperty(File.separator)+"lastn.text";
	
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
	
	public ServiceCenter()   {
		actualLetter = 'A';
		actualTurnNumber = 0;
		lastLetterGiven= 'A';
		lastNumGiven= 0;
		actualSerial = 1;
		lastSerialGiven = 1;
		users = new User[0];
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
	
	public void generateTurns(int turnsDay,int days) throws NoUserException, NoTurnTypeException, UserNotFoundException {
		if(users != null) {
			for(int i = 0;i < (turnsDay*days);i++) {	
				Random randomUser = new Random(users.length);
				assignUserTurn(users[randomUser.nextInt()].getId());
			}
		}else {
			throw new UserNotFoundException("no users yet");
		}
	}
	public void assignUserTurn(String id) throws NoUserException, NoTurnTypeException{
		lastSerialGiven =lastNumGiven == 99 && lastLetterGiven == 'Z' ? ++lastSerialGiven : lastSerialGiven;
		if(tt == null) {
			throw new NoTurnTypeException();
		}
		sortTurnTypeByTime();
		Random r = new Random(tt.size());
		searchUser(id).setTurn(lastLetterGiven, lastNumGiven,lastSerialGiven,tt.get(r.nextInt()));
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
	
	/**
	 * <b>Description:</b> search the turn to be called<br>
	 * @return the next turn on the call<br> 
	 */
	
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
	
	/**
	 * <b>Description:</b> the method advance the turn according with the update date<br>
	 */
	public void advanceTurn() {
		sortTurnTypeByName();
		Turn next = searchTurnCall();
		Random randomNum = new Random(1);
		float time = pd.getTimeInMinutes();
		while(next.getTT().getTime() < time) {
			time -= next.getTT().getTime();
			next.setAttended(randomNum.nextInt());
			actualSerial =actualTurnNumber == 99 && actualLetter == 'Z' ? ++actualSerial : actualSerial;
			if( actualTurnNumber == 99) {
				actualLetter= actualLetter == 'Z' ? 'A' : ++actualLetter;
			}
			actualTurnNumber = actualTurnNumber == 99? 0 : ++actualTurnNumber;
	
		}
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
				addUser(id,name,last,i);
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
	 * @throws UserNotFoundException if there is no users yet <br>
	 */
	public String generateReportSameTurns(String c) throws UserNotFoundException{
		String msg = "";
			for(int i = 0; i < users.length;i++) {
				if(users[i].hasTurn(c)) {
					msg += users[i];
					msg += "\n";
				}
			}
			if(msg == "") {
				throw new UserNotFoundException("theres no users yet");
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
	
	/**
	 * <b>Description:</b> sort turn type with name<br>
	 */
	public void sortTurnTypeByName() {
		for(int i = 0; i < tt.size()-1;i++) {
			for(int j = 0; j < tt.size()-1;j++) {
				if(tt.get(j).getName().compareTo(tt.get(j++).getName())>0) {
					TurnType temp = tt.get(j);
					tt.add(j, tt.get(j++));
					tt.add(j++,temp);
				}
			}
		}
	}
	
	/**
	 * <b>Description:</b> search all turns of an user by the id<br>
	 * @param id is the id of the user wanna know the turns<br>
	 * @return the turns of an user<br>
	 * @throws UserNameNotFoundException if the id doesn't exist<br>
	 */
	public ArrayList<Turn> getTurnsWithUserId(String id) throws NoUserException{
		sortUserById();
		ArrayList<Turn> t = null;
		for(int i  = 0;i < users.length && users[i].getId().equals(id);i++) {
			t = users[i].getTurns();
		}
		if(t == null) {
			throw new NoUserException(id);
		}
		return t;
	}
	/**
	 * <b>Description:</b> search all turns of an user by the name<br>
	 * @param na is the name of the user for find the turns<br>
	 * @return the turns of an user<br>
	 * @throws UserNotFoundException if the name doesn't exist<br>
	 */
	public ArrayList<Turn> getTurnsWithUserName(String na) throws UserNotFoundException{
		sortUserByName();
		ArrayList<Turn> t = null;
		int min = 0;
		int max = users.length;
		boolean find = false;
		while(min != max && !find) {
			int mid = (min+max)/2;
			if(users[mid].getName().equalsIgnoreCase(na)) {
				find = true;
				t = users[mid].getTurns();
			}else if(users[mid].getName().compareTo(na) > 0) {
				max = mid-1;
			}else {
				min = mid+1;
			}
		}
		if(t == null) {
			throw new UserNotFoundException("the user with name "+na+ " wasn�t found");
		}
		return t;
	}
	
	/**
	 * <b>Description:</b> search all turns of an user by the last name<br>
	 * @param ln is the last name of the user of get the turns<br>
	 * @return is the turns of a user<br>
	 * @throws UserNotFoundException if the last name doesn't exist<br>
	 */
	public ArrayList<Turn> getTurnsWithUserLastName(String ln)throws UserNotFoundException{
		sortUserByLastName();
		ArrayList<Turn> t = null;
		int min = 0;
		int max = users.length;
		boolean find = false;
		while(min != max && !find) {
			int mid = (min+max)/2;
			if(users[mid].getLastName().equalsIgnoreCase(ln)) {
				find = true;
				t = users[mid].getTurns();
			}else if(users[mid].getLastName().compareTo(ln) > 0) {
				max = mid-1;
			}else {
				min = mid+1;
			}
		}
		if(t == null) {
			throw new UserNotFoundException("the user with last name "+ln+ " wasn�t found");
		}
		return t;
	}
	
	public boolean banUser(String id) {
		sortUserByIdDescending();
		boolean ban = false;
		int min = 0;
		int max = users.length;
		boolean find = false;
		while(min != max && !find) {
			int mid = (max+min)/2;
			if(users[mid].getId().equals(id)) {
				find = true;
				users[mid].verifyBan();
				ban = users[mid].isBan();
			}else if(users[mid].getId().compareTo(id)>0){
				min = mid+1;
			}else {
				max = mid-1;
			}
		}
		return ban;
	}
	
	public void addUser(String id, String n, String l,int pos) throws UserExistException {
		try {
			searchUser(id);
			throw new UserExistException(id);
		} catch (NoUserException e) {
			users[pos] = new User(id,n,l);
		}
	}
	
}
