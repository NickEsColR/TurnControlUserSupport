package ui;
import model.*;
import java.util.Scanner;

import CustomException.NoUserException;
import CustomException.UserExistException;



/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 22
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class Main {
	private Scanner board;
	private ServiceCenter serviceCenter;
	
	public Main() {
		serviceCenter = new ServiceCenter();
		board = new Scanner(System.in);
		System.out.println("choose an option");
		System.out.println("1. Set user a turn");
		System.out.println("2. Add a new user");
		System.out.println("3.advanceTurn");
		System.out.println("4. add phone");
		System.out.println("5. add address");
		System.out.println("6. exit");
		int option = board.nextInt();
		board.nextLine();
		while(option != 6) {
			switch(option) {
			case 1:
				try {
					System.out.println("Digit the user´s identification");
					String id = board.nextLine();
					serviceCenter.assignUserTurn(id);
				}catch(NoUserException e) {
					System.out.println(e.getMessage() + "please add a new user");
				}
			break;
			case 2:
				try {
					System.out.println("Digit the user´s identification");
					String id = board.nextLine();
					System.out.println("Digit the user´s name");
					String name = board.nextLine();
					System.out.println("Digit the user´s last name");
					String ln = board.nextLine();
					serviceCenter.addUser(id, name, ln);
				}catch(UserExistException e) {
					System.out.println(e.getMessage());
				}
			break;
			case 3:
				System.out.println("Digit number "+ServiceCenter.ATTENDED+" if the previously turn was attended");
				System.out.println("Digit number "+ServiceCenter.NO_ATTENDED+" if the previously turn wasn´t attended");
				int a = board.nextInt();
				board.nextLine();
				System.out.println(serviceCenter.advanceTurn(a));
			break;
			}
		}
	}
	public static void main(String[]args) {
		Main m = new Main();
	}
}
