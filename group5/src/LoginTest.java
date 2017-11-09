import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LoginTest {
	
	Login l ;

	@Test
	public void test() {
		String[] textid = {"1","1","11asa","1ss1","1ssa11","1s11","sa","a"};
		l.readfile();
		String s = "" ;
		for (int i = 0; i < textid.length; i++) 
		{
			s += textid[i] + " ";
		}
		boolean b = false ;
		String[] text = s.split(" ");
		for (int i = 0; i < text.length/2; i++) 
		{
			if(l.loginTest(text[i], text[i+1]) == true)
			{
				b = true;
			}
		}
		assertTrue(b);
	}

}
