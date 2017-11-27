import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SendemailTest {

	@Test
	public void test() {

		Sendemail sendEmail = new Sendemail
				("testEmail","new_ziizyy@hotmail.co.th","Cs284");
		
		boolean email = false;
		if(sendEmail.getTextTest().equals("testEmail")){
			email = true;
		}
		assertTrue(email);
	}

}
