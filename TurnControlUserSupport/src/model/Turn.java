package model;

public class Turn {
	
	//atributes
	
	private char letter;
	private int num;
	
	//methods
	
	public Turn(char l,int n) {
		letter = l;
		num = n;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
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