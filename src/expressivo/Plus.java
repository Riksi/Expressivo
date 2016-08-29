package expressivo;
import java.util.*;

import expressivo.Number;

class Plus implements Expression{
	//
	// AF: represents the sum of two expressions
	//which are represented by Expression objects
	//Safety from rep exposure
	//all fields are immutable and final
	//
	private final Expression left;
	private final Expression right;

	Plus(Expression left, Expression right){
        this.left = left;
        this.right = right;
	};
	
    /**
     * @return a list containing the terms of left followed by the terms of right
     */
    public List<Expression> terms(){
        List<Expression> terms = new ArrayList<>();
        terms.addAll(this.left.terms());
        terms.addAll(this.right.terms());
        return terms;
    };
    
    /**
     * @return a list containing this Plus object
     */
    public List<Expression> factors(){
        List <Expression> factors = new ArrayList<>();
        factors.add(this);
        return factors;
    };
    
    
    //Uses d(u+v)/dt = du/dt + dv/dt
    @Override public Expression differentiate(Expression var){
        return Expression.sum(left.differentiate(var),right.differentiate(var));
    }
    
    @Override public Expression simplify(Map<Expression,Double> values){
        Expression simpExp = Expression.sum(left.simplify(values),right.simplify(values));
        Number numTerm = new Number(simpExp.numTerm());
        Expression varTerm = simpExp.varTerm();
        return Expression.sum(numTerm,varTerm);
    }


    /**
     * @return the sum of the numerical terms of the left and right expressions
     */
    @Override public double numTerm(){
        return left.numTerm()+right.numTerm();
    }
    
    
    /**
     * @return 1 as this object represents 1*(left + right) and former is numerical term
     */
    @Override public double numFactor(){
        return 1;
    }
    
    /**
     * @return an Expression representing the sum of the non-numerical terms
     */
    @Override public Expression varTerm(){
        return Expression.sum(left.varTerm(),right.varTerm());
    }
    
    
    /**
     * @return (left + right) as this object represents 1*(left + right) and latter is non-numerical term
     */
    @Override public Expression varFactor(){
        return this;
    }
    
    
	/**
	 * @return a string representation of the Plus object, 
	 * in the form with the string representations of the terms
	 * in the expression separated by '+' with the entire 
	 * string enclosed within a pair of parentheses 
	 */
    @Override public String toString(){
        //List <Expression> terms = this.terms();
        //String plusString = "";
        //for(Expression term:terms){
        //    plusString+= " + " + term.toString();
        //}
        //plusString = "(" + plusString.substring(3) + ")";
        //return plusString;
	    return "(" + this.left.toString() + " + " + this.right.toString() + ")";
	}
	
	/**
	 * @return true if both left and right Expressions are same for both objects
	 */
   public boolean equals(Object other){
        if(!(other instanceof Plus)){return false;}
        Plus otherPlus = (Plus)other;
        return (this.left.equals(otherPlus.left) && this.right.equals(otherPlus.right));
    }
	
	public int hashCode(){
	    int result = 43;
	    result = 37*result + left.hashCode();
	    result = 37*result + right.hashCode();
	    return result;
	  }
	
}
