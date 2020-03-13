package ui;
import model.*;

import java.io.IOException;
import java.util.Scanner;

import CustomException.NoUserException;
import CustomException.UserExistException;



/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class Main {
	private Scanner board;
	private ServiceCenter sc;
	
	/**
	* <b>Description:</b> constructor of main<br>
	* <b>Pos:</b> allow make whatever the program can do<br>
	*/
	
	public Main() {
		sc = new ServiceCenter();
		System.out.println("An error ocurred");
		board = new Scanner(System.in);
		menu();
		int option = board.nextInt();
		board.nextLine();
		String id = "";
		int number = 0;
		long time = 0;
		while(option != 7) {
			switch(option) {
			case 1:
				time = System.currentTimeMillis();
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 2:
				
			break;
			case 3:
				
			break;
			case 4:
				time = System.currentTimeMillis();
				System.out.println("Digit the user큦 identification");
				 id = board.nextLine();
				System.out.println("Digit the user큦 phone");
				String p = board.nextLine();
				try {
					sc.addPhone(id, p);
				}catch(NoUserException e) {
					System.out.println(e.getMessage());
				}
				System.out.println(System.currentTimeMillis()-time);
			break;
			case 5:
				time = System.currentTimeMillis();
				System.out.println("Digit the user큦 identification");
				 id = board.nextLine();
				System.out.println("Digit the user큦 address");
				String a1 = board.nextLine();
				try {
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
				int number = board.nextInt();
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
		System.out.println("1. Set user a turn");
		System.out.println("2. Make a report of all turns of a person");
		System.out.println("3. advance turn");
		System.out.println("4. add phone");
		System.out.println("5. add address");
		System.out.println("6. print turn");
		System.out.println("7. generate users");
		System.out.println("8. update system date");
		System.out.println("9. add a new turn type");
		System.out.println("10. generate turns");
		System.out.println("11. ban a user is not present");

		System.out.println("7. exit");
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
