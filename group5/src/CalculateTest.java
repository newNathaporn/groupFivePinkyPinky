import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CalculateTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String[] s = {"5909650099,Brian,Swiss,10,,30,10",
		"5909650100,Bruno,Goes,20,,30,60"};
		Double[] d = {4.0,8.0};
		assertArrayEquals(d, Calculatescore.calculate(null, 0, s, "cs211"));
	}

}