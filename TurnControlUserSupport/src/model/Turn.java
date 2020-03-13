package model;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class Turn {
	
	public static int ATTENDED=0; 
	public static int NO_ATTENDED=1;
	
	//atributes
	
	private char letter;
	private int num;
	private int serial;
	private int attended;
	private boolean wasntThere;
	//methods
	
	public boolean isWasntThere() {
		return wasntThere;
	}

	public void setWasntThere(boolean wasntThere) {
		this.wasntThere = wasntThere;
	}

	/**
	* <b>Descrpition:</b> constructor of a turn<br>
	* @param l is the turn's letter <br>
	* @param n is the turn's number <br>
	* @param s is the turn's serial<br>
	*/
	
	public Turn(char l,int n,int s) {
		letter = l;
		num = n;
		serial = s;
		attended = -1;
	}
	
	/**
	* <b>Description:</b> get the attended condition of the turn<br>
	* @return attended is a num of a condition<br>
	*/
	
	public int getAttended() {
		return attended;
	}
	
	/**
	* <b>Description:</b> set the attended condition of the turn<br>
	* @param attended is a num of a condition<br>
	*/
	
	public void setAttended(int attended) {
		this.attended = attended;
	}
	
	/**
	* <b>Description:</b> get the letter of a turn<br>
	* @return letter is the turn's letter<br>
	*/

	public char getLetter() {
		return letter;
	}
	
	/**
	* <b>Description:</b> set the letter of a turn<br>
	* @param letter is the turn's letter<br>
	*/
	
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	/**
	* <b>Description:</b> get the serial of a turn<br>
	* @return serial is the turn's serial <br>
	*/
	
	public int getSerial() {
		return serial;
	}
	
	/**
	* <b>Description:</b> set the serial of a turn<br>
	* @param serial is the turn's serial <br>
	*/
	
	public void setSerial(int serial) {
		this.serial = serial;
	}
	
	/**
	* <b>Description:</b> get the number of a turn<br>
	* @return num is the turn's number <br>
	*/
	
	public int getNum() {
		return num;
	}
	
	/**
	* <b>Description:</b> set the number of a turn<br>
	* @param num is the turn's number <br>
	*/
	
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		String msg = "";
		if(num < 10) {
			msg = letter + "0" + Integer.toString(num);
		}else {
			msg = letter + Integer.toString(num) ;
		}	
		return msg;
	}

	
}