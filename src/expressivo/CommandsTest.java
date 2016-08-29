package expressivo;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import expressivo.Number;



public class CommandsTest {
    Number constant = new Number(28.08);
    Variable phi = new Variable("phi");
    Variable psi = new Variable("psi");
    @Test
    public void testSimple(){
        assertEquals(Commands.differentiate(constant,"phi"),"0.0");
        assertEquals(Commands.differentiate(psi,"phi"),"0.0");
        assertEquals(Commands.differentiate(phi,"phi"),"1.0");
    }
    
    Plus p1 = new Plus(constant,phi);
    Plus p2 = new Plus(psi,constant);
    Multiply m1 = new Multiply(constant,phi);
    Multiply m2 = new Multiply(psi,constant);
    Plus twoPhi = new Plus(phi,phi);
    Multiply phiSq = new Multiply(phi,phi);
    Plus powPlus = new Plus(new Multiply(phi,phiSq),phi); 
    Multiply prodMult = new Multiply(new Plus(phi,twoPhi),phiSq);
    
    
    //Same testing strategy as for Expression but with strings as output
    //Also ensure that single letters, capital, words all work
    @Test
    public void testCompound(){
        assertEquals(Commands.differentiate(new Variable("x"),"x"),"1.0");
        assertEquals(Commands.differentiate(new Variable("x"),"X"),"0.0");
        assertEquals(Commands.differentiate(new Variable("xmas"),"xmas"),"1.0");
        assertEquals(Commands.differentiate(p1,"phi"),"1.0");
        assertEquals(Commands.differentiate(p2,"phi"),"0.0");
        assertEquals(Commands.differentiate(m1,"phi"),"28.08");
        assertEquals(Commands.differentiate(m2,"phi"),"0.0");
        assertEquals(Commands.differentiate(powPlus,"phi"),"(((phi * (phi + phi)) + (phi * phi))"
                + " + 1.0)");
        assertEquals(Commands.differentiate(prodMult,"phi"), "(((phi + (phi + phi)) * (phi + phi))"+
                                                                            " + ((1.0 + (1.0 + 1.0)) * (phi * phi)))");
    }
    
    //Same testing strategy as for Expression but in addition
    //ensuring that it works for one and more than one var=val mappings for a given expression
    //Also test the regex parsing of the (var=val (var=val)*)* input for floats and scientific notation
    //Also test simplify without mapping
    //Also test single letter, word, caps
    @Test
    public void testSimplify(){
        String vars = "phi=5.0";
        Plus test1 = new Plus(new Number(28),new Plus(new Number(0.08),phi));
        Multiply test2 = new Multiply(new Plus(constant,new Number(7)),test1);
        Variable x = new Variable("x");
        assertEquals(Commands.simplify(x, "x=16.47e-9"),"1.647E-8");
        assertEquals(Commands.simplify(x, "x=16.47E+9"),"1.647E10");
        assertEquals(Commands.simplify(x, "x=1.4E19"),"1.4E19");
        assertEquals(Commands.simplify(x, "X=16.47e-9"),"x");
        assertEquals(Commands.simplify(test1,""),"(28.08 + phi)");
        assertEquals(Commands.simplify(test2,""),"(35.08 * (28.08 + phi))");
        assertEquals(Commands.simplify(constant,vars),"28.08");
        assertEquals(Commands.simplify(phi,vars),"5.0");
        assertEquals(Commands.simplify(psi,vars),"psi");
        assertEquals(Commands.simplify(p1,vars),"33.08");
        Expression p2rev = Expression.sum(constant,psi);
        assertEquals(Commands.simplify(p2,vars),"(28.08 + psi)");
        assertEquals(Commands.simplify(phiSq,vars),"25.0");
        assertEquals(Commands.simplify(new Multiply(p2,phiSq),vars),"(25.0 * (28.08 + psi))");
        Double n = (28.08*28.08*5);
        assertEquals(Commands.simplify(Expression.times(m1, m2),vars),"("+n.toString() + " * psi)");
        assertEquals(Commands.simplify(Expression.times(phiSq, psi),vars),"(25.0 * psi)");
        vars = "phi=5.0 psi=6";
        assertEquals(Commands.simplify(p1,vars),"33.08");
        assertEquals(Commands.simplify(Expression.sum(psi,twoPhi),vars),"16.0");
    }
    
}
