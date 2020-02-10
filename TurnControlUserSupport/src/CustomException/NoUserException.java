package CustomException;

public class NoUserException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUserException(String id) {
		super("The user with id:"+id+" wasnt found");
	}
}
