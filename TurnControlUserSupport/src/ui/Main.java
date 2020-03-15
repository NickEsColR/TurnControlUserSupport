package ui;
import model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import CustomException.NoUserException;
import CustomException.UserExistException;
import CustomException.UserNotFoundException;



/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class Main {
	public static String PATH_TURNS_USER = "data"+System.getProperty(File.separator)+"user turns.turn";
	private Scanner board;
	private ServiceCenter sc;
	
	/**
	* <b>Description:</b> constructor of main<br>
	* <b>Pos:</b> allow make whatever the program can do<br>
	*/
	
	public Main() {
		boolean userExist = false;
		FileInputStream istream = new FileInputStream("turnProgram.text");
		ObjectInputStream in = new ObjectInputStream(istream);
		sc = (ServiceCenter)in.readObject();
		istream.close();
		if(sc == null) {
			sc = new ServiceCenter();
		}
		System.out.println("An error ocurred");
		board = new Scanner(System.in);
		menu();
		int option = board.nextInt();
		board.nextLine();
		String id = "";
		int number = 0;
		long time = 0;
		while(option != 12) {
			switch(option) {
			case 1:
				System.out.println("digit 1 for search by id");
				System.out.println("digit 2 for search by name");
				System.out.println("digit 3 for search by lastName");
				number= board.nextInt();
				switch(number){
				case 1:
					System.out.println("digit 1 if the report will show in console, digit 0 if will show in a file");
					number = board.nextInt();
					board.nextLine();
					System.out.println("digit the id");
					id = board.nextLine();
					time = System.currentTimeMillis();
		
					try {
						ArrayList<Turn> t = sc.getTurnsWithUserId(id);
						if(number == 0) {
							try {
								BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TURNS_USER));
								bw.write("User id "+id);
								for(int i = 0;i < t.size();i++) {
									bw.write(t.get(i).toString());
								}
								bw.flush();
								bw.close();
							} catch (IOException e) {
								System.out.println("an error ocurred");						
							}
						}else
							System.out.println("User id "+id);
							for(int i = 0;i < t.size();i++) {
								System.out.println(t.get(i));
							}
					} catch (NoUserException e1) {
						System.out.println("an error ocurred "+ e1.getMessage());
					}
				break;
				case 2:
					System.out.println("digit 1 if the report will show in console, digit 0 if will show in a file");
					number = board.nextInt();
					board.nextLine();
					System.out.println("digit the name");
					String name = board.nextLine();
					time = System.currentTimeMillis();
		
					try {
						ArrayList<Turn> t = sc.getTurnsWithUserName(name);
						if(number == 0) {
							try {
								BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TURNS_USER));
								bw.write("User name "+name );
								for(int i = 0;i < t.size();i++) {
									bw.write(t.get(i).toString());
								}
								bw.flush();
								bw.close();
							} catch (IOException e) {
								System.out.println("an error ocurred");						
							}
						}else
							System.out.println("User name "+name);
							for(int i = 0;i < t.size();i++) {
								System.out.println(t.get(i));
							}
					} catch (NoUserException e1) {
						System.out.println("an error ocurred "+ e1.getMessage());
					}
				break;
				case 3:
					System.out.println("digit 1 if the report will show in console, digit 0 if will show in a file");
					number = board.nextInt();
					board.nextLine();
					System.out.println("digit the last name");
					String lastName = board.nextLine();
					time = System.currentTimeMillis();
		
					try {
						ArrayList<Turn> t = sc.getTurnsWithUserLastName(lastName);
						if(number == 0) {
							try {
								BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TURNS_USER));
								bw.write("User last name "+lastName );
								for(int i = 0;i < t.size();i++) {
									bw.write(t.get(i).toString());
								}
								bw.flush();
								bw.close();
							} catch (IOException e) {
								System.out.println("an error ocurred");						
							}
						}else
							System.out.println("User last name "+lastName);
							for(int i = 0;i < t.size();i++) {
								System.out.println(t.get(i));
							}
					} catch (NoUserException e1) {
						System.out.println("an error ocurred "+ e1.getMessage());
					}
				break;
				}
				
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 2:
				System.out.println("For advance turn first generate turns");
				System.out.println("After update the date and all turns will advance until that date");
			break;
			case 3:
				System.out.println("Digit the turn name");
				String name = board.nextLine();
				System.out.println("Digit the turn duration keeping in mind that 1 unit means a minute and 0.5 means middle minute");
				float min = board.nextFloat();
				board.nextLine();
				time = System.currentTimeMillis();
				sc.setTt(name, min);
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 4:
				System.out.println("Digit the user´s identification");
				 id = board.nextLine();
				System.out.println("Digit the user´s phone");
				String p = board.nextLine();
				try {
					time = System.currentTimeMillis();
					sc.addPhone(id, p);
				}catch(NoUserException e) {
					System.out.println(e.getMessage());
				}
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 5:
				System.out.println("Digit the user´s identification");
				 id = board.nextLine();
				System.out.println("Digit the user´s address");
				String a1 = board.nextLine();
				try {
					time = System.currentTimeMillis();
					sc.addAddress(id, a1);
				}catch(NoUserException e) {
					System.out.println(e.getMessage());
				}
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 6:
				time = System.currentTimeMillis();
				System.out.println(sc.printTurn());
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 7:
				time = System.currentTimeMillis();
				number = board.nextInt();
				board.nextLine();
				try {
					sc.generateRandomUsers(number);
				} catch (IOException e) {
					System.out.println("an error ocurred reading the files");
					e.printStackTrace();
				}
				System.out.println("the users been generated");
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 8:
				time = System.currentTimeMillis();
				System.out.println("digit 1 if you want to write a new date");
				number = board.nextInt();
				if(number == 1) {
					System.out.println("digit the day of the new date");
					int d = board.nextInt();
					System.out.println("digit the month of the new date");
					int m = board.nextInt();
					System.out.println("digit the year of the new date");
					int y = board.nextInt();
					System.out.println("digit the hour of the new date");
					int h = board.nextInt();
					System.out.println("digit the minute of the new date");
					int min = board.nextInt();
					System.out.println("digit the seconds of the new date");
					int s = board.nextInt();
					sc.modifyDate(d, m, y, h, min, s);
				}
				sc.showDate();
				board.nextLine();
				sc.advanceTurn();
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 9:
				System.out.println("digit the quantity of turns per day");
				int turnsDay = board.nextInt();
				System.out.println("digit the quantity of days for generate turns");
				int days = board.nextInt();
				board.nextLine();
				time = System.currentTimeMillis();
				try {
					sc.generateTurns(turnsDay, days);
					System.out.println("the turns was set succesfully");
				}catch(NoTurnTypeException e1) {
					System.out.println(e1.getMessage());
				}catch(UserNotFoundException e2) {
					System.out.println(e2.getMessage());
				}
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 10:
				System.out.println("digit the id of the user for prove if should be ban");
				id = board.nextLine();
				time = System.currentTimeMillis();
				if(sc.banUser(id)) {
					System.out.println("the user was ban");
				}else {
					System.out.println("the user can´t be ban");
				}
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 11:
				System.out.println("digit the turn; for example: 'A15'");
				String turn = board.nextLine();
				time = System.currentTimeMillis();
				try {
					System.out.println(sc.generateReportSameTurns(turn));
				}catch(UserNotFoundException e) {
					System.out.println("there is no users yet, please create users");
				}
			break;
			case 12:
				System.out.println("thanks for use the program came back soon");
				time = System.currentTimeMillis();
				FileOutputStream ostream = new FileOutputStream("turnProgram.text");
				ObjectOutputStream os = new ObjectOutputStream(ostream);
				os.writeObject(sc);
				os.flush();
				ostream.close();
				System.out.println(System.currentTimeMillis()-time);
			break;
			}
			menu();
			option = board.nextInt();
			board.nextLine();
		}
		board.close();
		
	}
	
	private void menu() {
		System.out.println("choose an option");
		System.out.println("1. Make a report of all turns of a person");
		System.out.println("2. advance turns");
		System.out.println("3. add a new turn type");
		System.out.println("4. add phone");
		System.out.println("5. add address");
		System.out.println("6. print turn");
		System.out.println("7. generate users");
		System.out.println("8. update system date");
		System.out.println("9. generate turns");
		System.out.println("10. ban a user is not present");
		System.out.println("11. make a report of all people have been a specific turn");
		System.out.println("12. exit");
		System.out.println("--------------------------");
		
		
	}

	/**
	* <b>Description:</b> is a constant method that can execute the program<br>
	* @param args is the arguments that user will introduce<br>
	*/
	
	public static void main(String[]args) {
		Main m = new Main();
	}
}
