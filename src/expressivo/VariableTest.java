package expressivo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
public class VariableTest {
	
	public String[] makeStringList(){
		String oneLower = "n";
		String oneUpper = "N";
		String upperStart = "Var";
		String capsOnly = "VAR";
		String smallOnly = "var";
		String[] tstrs= {oneLower,oneUpper,upperStart,capsOnly,smallOnly};
		return tstrs;
	}
	
	Variable var = new Variable("alpha");
	Variable varCaps = new Variable("AlphA");
	Variable varSame = new Variable("alpha");
	Variable varSame2 = new Variable("alpha");
	Variable varDiff = new Variable("phi");
	Number num = new Number(8.12);
	Plus plu = new Plus(var,varDiff);
	Multiply mult = new Multiply(var,varDiff);
	String st = "alpha";
	
	/**
	 * Testing strategy
	 * 
	 * Commutativity, reflexivity, transitivity for same and diff
	 * Same letters different case not equal
	 * Same value when called at another time
	 * Does not equal other Expressions, other class, null
	 * 
	 */
	
	
	@Test
	public void testEquals(){
		assertEquals(true,var.equals(var));
		assertEquals(true,var.equals(varSame));
		assertEquals(true,varSame.equals(var));
		assertEquals(true,var.equals(varSame2));
		assertEquals(true,varSame.equals(varSame2));
		assertEquals(false,var.equals(varDiff));
		assertEquals(false,varDiff.equals(var));
		assertEquals(false,varSame.equals(varDiff));
		assertEquals(false,var.equals(num));
		assertEquals(false,var.equals(plu));
		assertEquals(false,var.equals(mult));
		assertEquals(false,var.equals(st));
		assertEquals(false,var.equals(varCaps));
		assertEquals(true,var.equals(varSame));
		assertEquals(false,var.equals(null));
		
	}
	
	
	/**
	 * Testing strategy
	 * 
	 * 1 letter - capital
	 * 1 letter - small
	 * > 1 letter
	 * Mixture of capital and small
	 */
	
	@Test
	public void testToString(){
		String[] testStrings = makeStringList();
		for(String s: testStrings){
			assertEquals(s,new Variable(s).toString());
		}
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
		assertEquals(var.hashCode(),varSame.hashCode());
		assertEquals(var.hashCode(),varSame2.hashCode());
	}
	
    //For terms and factors only need to ensure that a list
    //containing only the Variable object is returned
    @Test
    public void testFactors(){
        assertEquals(var.factors().size(),1);
        assertEquals(var.factors().get(0),var);
    }
    
    
    @Test
    public void testTerms(){
        assertEquals(var.terms().size(),1);
        assertEquals(var.terms().get(0),var);
    }
}
