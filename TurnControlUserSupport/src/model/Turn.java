package model;

public class Turn {
	
	//atributes
	
	private char letter;
	private int num;
	private int serial;
	//methods
	
	public Turn(char l,int n,int s) {
		letter = l;
		num = n;
		serial = s;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return letter + Integer.toString(num) ;
	}

	
}