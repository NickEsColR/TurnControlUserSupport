package TestCase;

import static org.junit.jupiter.api.Assertions.*;
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
	void test() {
		setup1();
		fail("Not yet implemented");
	}

}
