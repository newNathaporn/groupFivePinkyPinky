import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class SendemailTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		Sendemail sendEmail = new Sendemail
				("testEmail","new_ziizyy@hotmail.co.th","Cs284");
		
		boolean email = false;
		if(sendEmail.getTextTest().equals("testEmail#")){
			email = true;
		}
		assertTrue(email);
	}

}
