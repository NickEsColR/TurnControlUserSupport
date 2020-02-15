package TestCase;
import model.*;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class UserTest extends TestCase {
	private User user;
	
	private void setup2() {
		user = new User("123456799",  "Elve", "Noso");
	}
	
	@Test
	void User() {
		setup2();
		assertEquals("the id is given and recorded the same", "123456799", user.getId());
		assertEquals("the name is given and recorded the same", "Elve", user.getName());
		assertEquals("the name is given and recorded the same", "Noso", user.getLastName());
		
	}
	
	@Test
	void addTurn() {
		setup2();
		user.setTurn('A', 11, 1);
		assertFalse("the letter given and recorder isnt the same",'A' == user.getTurns().get(0).getLetter());
		assertFalse("the turn number given and recorder isnt the same",11 == user.getTurns().get(0).getNum());
		assertFalse("the serial turn given and recorder isnt the same",1 == user.getTurns().get(0).getSerial());

	}
	
}
