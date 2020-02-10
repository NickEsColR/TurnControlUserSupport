package ui;
import model.*;
import java.util.Scanner;


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
	}
	public static void main(String[]args) {
		Main m = new Main();
	}
}
