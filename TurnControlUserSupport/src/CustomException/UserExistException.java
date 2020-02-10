package CustomException;

public class UserExistException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExistException(String id) {
		super("The user with id:"+id+" is already register");
	}
}
