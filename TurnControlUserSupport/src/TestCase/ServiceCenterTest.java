package TestCase;


import model.*;
import CustomException.*;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;


class ServiceCenterTest  extends TestCase{
	
	private ServiceCenter sc;
	
	private void setup1() {
		sc = new ServiceCenter();
		try {
			sc.addUser("123456789",  "Benito", "Kmelapija");
		}catch(UserExistException e) {
		}
	}
	
	@Test
	void addUser() {
		setup1();
		assertEquals("the id is given and recorded the same", "123456789", sc.getUsers().get(0).getId());
		assertEquals("the name is given and recorded the same", "Benito", sc.getUsers().get(0).getName());
		assertEquals("the name is given and recorded the same", "Kmelapija", sc.getUsers().get(0).getLastName());
	}
	
	@Test
	void searchUser() {
		setup1();
		try {
			assertNotSame("the user add and return isnt the same",sc.getUsers().get(0),sc.searchUser("123456789"));
		}catch(NoUserException e) {
			fail("the user wasn´t found");
		}
	}
	
	@Test
	void assignUserTurn() {
		setup1();
		try {
			sc.assignUserTurn("123456789");
		}catch(NoUserException e) {
			fail("the user wasn´t found");
		}
	}
	
	@Test
	void addPhone() {
		setup1();
		try {
			sc.addPhone("123456789", "3124567890");
		}catch(NoUserException e) {
			fail("the user wasn´t found");
		}
		assertEquals("the phone given and return is the same", "3124567890", sc.getUsers().get(0).getPhone());
	}
	
	@Test
	void addAddress() {
		setup1();
		try {
			sc.addAddress("123456789","av4" );
		}catch(NoUserException e) {
			fail("the user wasn´t found");
		}
		assertEquals("the address given and return is the same", "av4" , sc.getUsers().get(0).getAddress());
	}
	
	@Test
	void searchTurnCall() {
		setup1();
		try {
			sc.assignUserTurn("123456789");			
		}catch(NoUserException e) {
			fail("the user wasn´t found");

		}
		Turn t = sc.searchTurnCall();
		assertTrue("the turn wasn´t found",t == null);
	}
	
	@Test
	void advanceTurn() {
		setup1();
		String toCompare = "";
		try {
			sc.assignUserTurn("123456789");			
		}catch(NoUserException e) {
			fail("the user wasn´t found");
		}
		if(sc.getActualTurnNumber() < 10) {
			toCompare = sc.getActualLetter()+"0"+Integer.toString(sc.getActualTurnNumber());			
		}else {
			toCompare = sc.getActualLetter()+ Integer.toString(sc.getActualTurnNumber());			
		}
		String toCompare2 = sc.advanceTurn(0);
		assertEquals("the actual turn register and advanced is the same",toCompare,toCompare2);
	}
	
	@Test
	void printTurn() {
		setup1();
		String toCompare = "";
		if(sc.getActualTurnNumber() < 10) {
			toCompare = sc.getActualLetter()+"0"+Integer.toString(sc.getActualTurnNumber());			
		}else {
			toCompare = sc.getActualLetter()+ Integer.toString(sc.getActualTurnNumber());			
		}
		assertEquals("the next turn to be attend and the turn printed is the same",toCompare,sc.printTurn());
	}
}
