package TestCase;
import model.Turn;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 06/02/2020
*/

public class TurnTest extends TestCase {
	private Turn turn;
	
	@Test
	void Turn() {
		turn = new Turn('A',11,1);
		assertFalse("the letter given and register isnt the same", 'A'==turn.getLetter());
		assertFalse("the number given and register isnt the same", 11 ==turn.getNum());
		assertFalse("the serial given and register isnt the same", 1==turn.getSerial());
	}
	
}
