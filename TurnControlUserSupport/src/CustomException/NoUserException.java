package CustomException;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class NoUserException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUserException(String id) {
		super("The user with : "+id+" wasnt found");
	}
}
