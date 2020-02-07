package CustomException;

public class NoUserException extends Exception{
	public NoUserException(String id) {
		super("The user with id:"+id+" wasnt found");
	}
}
