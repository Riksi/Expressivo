package expressivo;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class NumberTest {

	Number zero = new Number(0);
	Number num = new Number(8.26);
	Number numSame  = new Number(8.26);
	Number numSame2 = new Number(8.26);
	Number numDiff = new Number(56.37);
	Number asInt = new Number(16);
	Number asDouble = new Number(16.0);
	Variable var = new Variable("theta");
	Plus plu = new Plus(num,numDiff);
	Multiply mult = new Multiply(num,numDiff);
	Double db = 8.26;
	

	
	/**
	 * Testing strategy
	 *
	 * Partition inputs as follows:
	 * n = Double of 0, an integer > 0, a non-integer > 0,
	 * Number entered as integer e.g. 8 may return as 8.0
	 * Double.MIN_VALUE and Double.MAX_VALUE
	 * Numbers with positive and with negative exponents
	 * 
	 */
	@Test
	public void testToString(){
		assertEquals("8.26",num.toString());
		assertEquals("16.0",asInt.toString());
		assertEquals("16.0",new Number(160e-1).toString());
		assertEquals("16.0",new Number(1.6E1).toString());
		assertEquals("2338.0",new Number(2.338e+3).toString());
		assertEquals("0.0",zero.toString());
		assertEquals("1.7976931348623157E308",(new Number(Double.MAX_VALUE)).toString());
		assertEquals("4.9E-324", (new Number(Double.MIN_VALUE).toString()));
	}
	
	
	/**
	 * Testing strategy
	 * 
	 * Test equals relying on constructor
	 * Partition as follows
	 * -Structural
	 * num is input as integer versus as double e.g 1, 1.0
	 * test that it is reflexive, commutative and transitive
	 * using same and different numbers 
	 * different Expressions
	 * other Objects which are not Expressions
	 * Same value when called at another time
	 * Does not equal null
	 * 
	 */
	@Test
	public void testEquals(){
		assertEquals(true,num.equals(num));
		assertEquals(true,num.equals(numSame));
		assertEquals(true,numSame.equals(num));
		assertEquals(true,num.equals(numSame2));
		assertEquals(true,numSame.equals(numSame2));
		assertEquals(false,num.equals(numDiff));
		assertEquals(false,numDiff.equals(num));
		assertEquals(false,numSame.equals(numDiff));
		assertEquals(true,asInt.equals(asDouble));
		assertEquals(false,num.equals(zero));
		assertEquals(false,num.equals(var));
		assertEquals(false,num.equals(plu));
		assertEquals(false,num.equals(mult));
		assertEquals(false,num.equals(db));
		assertEquals(true,num.equals(numSame));
		assertEquals(false,num.equals(null));
		assertEquals(new Number(2338),new Number(2.338e+3));
	}
	/**
	 * Testing strategy
	 * 
	 * Same partitioning as for testEquals but tests only 
	 * for objects which are equal according to the equals method 
	 * because no requirement that unequal objects should have 
	 * different hash codes
	 *
	 */
	@Test
	public void testHashCode(){
		assertEquals(num.hashCode(),numSame.hashCode());
		assertEquals(num.hashCode(),numSame2.hashCode());
		assertEquals(asInt.hashCode(),asDouble.hashCode());
		assertEquals(new Number(2338).hashCode(),new Number(2.338e+3).hashCode());
	}
	
	
    //For terms and factors only need to ensure that a list
    //containing only the Number object is returned
    @Test
    public void testFactors(){
        assertEquals(num.factors().size(),1);
        assertEquals(num.factors().get(0),num);
    }
    
    @Test
    public void testTerms(){
        assertEquals(num.terms().size(),1);
        assertEquals(num.terms().get(0),num);
    }
}
