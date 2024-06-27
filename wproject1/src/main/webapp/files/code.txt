package debugtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import debug.Calculator;

public class CalculatorTest {
	
	Calculator calculator; 
	//calculator 클래스의 메소드를 test하기 위해 객체 선언
	
	@Before
	public void setup() {
		calculator = new Calculator();

	}
	@Test
	public void testPlus() {
		assertTrue(calculator.plus(8, 4) == 12);
		
		int a = 8, b = 8;
				assertEquals(a, b);
				
				int [] arr = {0, 0};
				int arr2[] = new int[2];
				assertArrayEquals(arr, arr2);
	}
	@Ignore // 아래 test를 사용하지 않을 경우 @Ignore를 걸어주면 된다.
	@Test
	public void testMulti() {
		fail("Not yet implemented");
	}

	@Test(timeout = 1000) // 1초 단위로 수행시간 검사
	public void testDivide() {
		assertTrue(calculator.divide(8, 4) == 2);
	//	assertTrue(calculator.divide(10, 4) == 2.5);
		
		for (int i = 0; i< 10; i++) { 
			System.out.println(i + " ");
		}
	}

}
