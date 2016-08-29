package expressivo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PlusTest {
	/**
	 * Testing strategy
	 * 
	 * Number, Variable, Plus, Multiply on left and right
	 * Both base case expression
	 * Both compound expressions
	 * Combination of base case and compound
	 * Combination of multiply to test for rearrangement
	 */
	Number n = new Number(19.2);
	Variable v = new Variable("a");
	Multiply mu = new Multiply(n,v);
	Plus bb = new Plus(n,v);
	Plus bc1 = new Plus(bb,n);
	Plus bc2 = new Plus(v,mu);
	Plus cc = new Plus(mu,bb);
	Plus pp = new Plus(bb,cc);
	Plus same = new Plus(n,v);
	Plus same2 = new Plus(n,v);
	Plus sameCompound = new Plus(bb,cc);
	Plus diffOrder = new Plus(v,n);
	Plus group1 = new Plus(new Multiply(bb,new Number(10.0)),new Variable("var"));
	//(n+v * 10)+var != n+(v*10)+var 
	Plus group2 = new Plus(new Plus(n,new Multiply(v,new Number(10.0))),new Variable("var"));
	Plus group2sameMeaning = new Plus(n,new Plus(new Multiply(v,new Number(10.0)),new Variable("var"))); 
	
	/**
	 * Testing strategy
	 * 
	 * Number, Variable, Multiply, Plus on left and right
	 * Both base case expression
	 * Both compound expressions
	 * Combination of base case and compound
	 * Combination of Plus to test for rearrangement
	 */
	@Test
	public void testToString(){
		assertEquals("(19.2 + a)",bb.toString());
		assertEquals("((19.2 + a) + 19.2)",bc1.toString());
		assertEquals("(a + (19.2 * a))",bc2.toString());
		assertEquals("((19.2 * a) + (19.2 + a))",cc.toString());
		assertEquals("((19.2 + a) + ((19.2 * a) + (19.2 + a)))",pp.toString());
	}
	
    
	
	/*
	 * Testing strategy
	 * 
	 * Commutativity, reflexivity, transitivity for same and diff
	 * Different ordering
	 * Different grouping 
	 * Same value when called at another time
	 * Does not equal null
	 * Does not equal other Expressions, other class, null
	 * 
	 */
	@Test
	public void testEquals(){
		assertEquals(true,bb.equals(bb));
		assertEquals(true,bb.equals(same));
		assertEquals(true,same.equals(bb));
		assertEquals(true,bb.equals(same2));
		assertEquals(true,same.equals(same2));
		assertEquals(false,bb.equals(diffOrder));
		assertEquals(false,bb.equals(diffOrder));
		assertEquals(false,same.equals(diffOrder));
		assertEquals(true,pp.equals(sameCompound));
		assertEquals(false,group1.equals(group2));
		assertEquals(false,group2.equals(group2sameMeaning));
		assertEquals(false,group1.equals(group2sameMeaning));
		assertEquals(false,bb.equals(n));
		assertEquals(false,bb.equals(v));
		assertEquals(false,bb.equals(mu));
		assertEquals(false,bb.equals("19.2 * alpha"));
		assertEquals(true,bb.equals(same));
		assertEquals(false,bb.equals(null));
	}
	
	/*
	 * Testing strategy
	 * 
	 * Same partitioning as for testEquals but tests only 
	 * for objects which are equal according to the equals method 
	 * because no requirement that unequal objects should have 
	 * different hash codes
	 *
	 */
	
/*	//Only need to test whether factors contains just that object
	@Test
	public void testFactors(){
	    List<Expression> factors = pp.factors();
	    assertEquals(factors.size(),1);
	    assertEquals(factors.get(0),pp);
	}
	
	*//**
	 * Testing strategy
	 * 
	 * Should first test objects not containing Plus
	 * Then those containing Plus - both on left and right
	 *//*
	@Test
    public void testTerms(){
	    List<Expression> notPlusTerms = bc2.terms();
	    List<Expression> plusTerms = pp.terms();
        assertEquals(notPlusTerms.size(),2);
        assertEquals(notPlusTerms.get(0),v);
        assertEquals(notPlusTerms.get(1),mu);
        assertEquals(plusTerms.size(),5);
        assertEquals(plusTerms.get(0),n);
        assertEquals(plusTerms.get(1),v);
        assertEquals(plusTerms.get(2),mu);
        assertEquals(plusTerms.get(3),n);
        assertEquals(plusTerms.get(4),v);
        
    }
	*/
	@Test
	public void testHashCode(){
		assertEquals(bb.hashCode(),same.hashCode());
		assertEquals(bb.hashCode(),same2.hashCode());
		//assertEquals(cc.hashCode(),sameCompound.hashCode());
		//assertEquals(group2.hashCode(),group2sameMeaning.hashCode());
	}
	
	
	
}
