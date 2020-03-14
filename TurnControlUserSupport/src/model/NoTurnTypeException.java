package model;

public class NoTurnTypeException extends Exception {
	
	public NoTurnTypeException() {
		super("there's no turn types created yet, please created at least 1");
	}
}
