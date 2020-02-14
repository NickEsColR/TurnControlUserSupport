package TestCase;
import model.Turn;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TurnTest extends TestCase {
	private Turn turn;
	
	@Test
	void TurnTest() {
		turn = new Turn('A',11,1);
		assertFalse("the letter given and register isnt the same", 'A'==turn.getLetter());
		assertFalse("the number given and register isnt the same", 11 ==turn.getNum());
		assertFalse("the serial given and register isnt the same", 1==turn.getSerial());
	}
	
}
