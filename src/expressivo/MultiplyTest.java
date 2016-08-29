package expressivo;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;



import static org.junit.Assert.*;
public class MultiplyTest {

	
	
	Number n = new Number(19.2);
	Variable v = new Variable("a");
	Plus pl = new Plus(n,v);
	Multiply bb = new Multiply(n,v);
	Multiply bc1 = new Multiply(bb,n);
	Multiply bc2 = new Multiply(v,pl);
	Multiply cc = new Multiply(pl,bb);
	Multiply mm = new Multiply(bb,cc);
	Multiply same = new Multiply(n,v);
	Multiply same2 = new Multiply(n,v);
	Multiply sameCompound = new Multiply(bb,cc);
	Multiply diffOrder = new Multiply(v,n);
	Multiply group1 = new Multiply(new Plus(bb,new Number(10.0)),new Variable("var"));
	//(n*v + 10)*var != n*(v+10)*var 
	Multiply group2 = new Multiply(new Multiply(n,new Plus(v,new Number(10.0))),new Variable("var"));
	Multiply group2sameMeaning = new Multiply(n,new Multiply(new Plus(v,new Number(10.0)),new Variable("var"))); 
	
	/**
	 * Testing strategy
	 * 
	 * Number, Variable, Plus, Multiply on left and right
	 * Both base case expression
	 * Both compound expressions
	 * Combination of base case and compound
	 * Combination of multiply to test for rearrangement
	 */
	@Test
	public void testToString(){
		assertEquals("(19.2 * a)",bb.toString());
		assertEquals("((19.2 * a) * 19.2)",bc1.toString());
		assertEquals("(a * (19.2 + a))",bc2.toString());
		assertEquals("((19.2 + a) * (19.2 * a))",cc.toString());
		assertEquals("((19.2 * a) * ((19.2 + a) * (19.2 * a)))",mm.toString());
	}
	

    
/*    *//**
     * Testing strategy
     * 
     * Should first test objects not containing Multiply
     * Then those containing Multiply - both on left and right
     *//*
    @Test
    public void testFactors(){
        List<Expression> notMultFactors = bc2.factors();
        List<Expression> multFactors = mm.factors();
        assertEquals(notMultFactors.size(),2);
        assertEquals(notMultFactors.get(0),v);
        assertEquals(notMultFactors.get(1),pl);
        assertEquals(multFactors.size(),5);
        assertEquals(multFactors.get(0),n);
        assertEquals(multFactors.get(1),v);
        assertEquals(multFactors.get(2),pl);
        assertEquals(multFactors.get(3),n);
        assertEquals(multFactors.get(4),v);   
    }
	
    
    //Only need to test whether terms contains just that object
    @Test
    public void testTerms(){
        List<Expression> terms = mm.terms();
        assertEquals(terms.size(),1);
        assertEquals(terms.get(0),mm);
    }*/
	
	
	/*
	 * Testing strategy
	 * 
	 * Commutativity, reflexivity, transitivity for same and diff
	 * Different ordering
	 * Different grouping 
	 * Same value when called at another time
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
		assertEquals(true,mm.equals(sameCompound));
		assertEquals(false,group1.equals(group2));
		assertEquals(false,group2.equals(group2sameMeaning));
		assertEquals(false,group1.equals(group2sameMeaning));
		assertEquals(false,bb.equals(n));
		assertEquals(false,bb.equals(v));
		assertEquals(false,bb.equals(pl));
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
	@Test
	public void testHashCode(){
		assertEquals(bb.hashCode(),same.hashCode());
		assertEquals(bb.hashCode(),same2.hashCode());
		//assertEquals(cc.hashCode(),sameCompound.hashCode());
		//assertEquals(group2.hashCode(),group2sameMeaning.hashCode());
	}
	
}
