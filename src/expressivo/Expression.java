package expressivo;
import java.util.*;
public interface Expression {
	//Expression = Number(n: int) 
	//+ Variable(s: String) 
	//+ Plus(left: Expression, right: Expression) 
	//+ Multiply (left: Expression, right: Expression)


    
    // Safety from rep exposure
    //    all fields are immutable and final
    
    
    
    /**    
     * Parses a string into an Expression object.
     * Strings must satisfy the follow requirements 
     * Allowed operations are addition and multiplication represented by '+' and '*'
     * Variable names must be composed only of [a-zA-Z] are case sensitive
     * All parentheses must be closed
     * Whitespace and redundant parentheses are allowed
     * Numbers should be positive and in the decimal form. They be represented as integers and floating
     * point numbers. For floating point numbers there only needs to be a number 
     * on one side of the floating point e.g. 19. as well as .19 are allowed* 
     * Sensitive to ordering and grouping so that a + 1 != 1 + a, a + (1 + b) ! = (a + 1) + b  
     * @param input string containing a valid polynomial expression, as described below:
     * @return an Expression object representing the input expression
     */
    public static Expression parse(String input){
        return Parser.parse(input);
  
    }
    
    public static Expression make(double num){
        return new Number(num);
    }
    
    public static Expression make(String var){
        return new Variable(var);
    }
    
    /**
     * Strips unnecessary zeroes 
     * @param left
     * @param right
     * @return an Expression representing left + right, returning only one of the inputs
     * if one of them happens to be zero
     */
    public static Expression sum(Expression left, Expression right){
        Number zero = new Number(0);
        if(left.equals(zero)){
            return right;
        }
        if(right.equals(zero)){
            return left;
        }
        return new Plus(left,right);
    }
    

    /**
     * Strips unnecessary ones and zeroes 
     * @param left
     * @param right
     * @return an Expression representing left * right, returning 
     * a representation of zero if one of the inputs is zero, returning only the other 
     * input if one of the inputs represents one
     */
   public static Expression times(Expression left, Expression right){
       Number zero = new Number(0);
       Number one = new Number(1);
       if(left.equals(zero)||right.equals(zero)){
           return new Number(0);
       }
       if(left.equals(one)){
           return right;
       }
       if(right.equals(one)){
           return left;
       }
       return new Multiply(left,right);
   }
    
    /**
    * Constructs an expression by simplifying this expression
    * by evaluating it using a mapping of variable names to 
    * numerical values. If the expression only contains numerical values
    * as a result of simplification returns an Expression representing a single number.
    * @param values a mapping of variables to numerical values 
    * at which they should be evaluated. Requires that the numerical
    * values are positive 
    * @return an Expression object represented the simplified form
    * of this object with 
    */
   public Expression simplify(Map<Expression,Double> values);
    
   
    /**
     * 
     * @return an expression representing the sum of the numbers in the expression
     */
    public double numTerm();
    
    
    /**
     * 
     * @return an expression representing the product of the numbers in the expression
     */
    public double numFactor();
    
    
    /**
     * 
     * @return an expression representing the sum of the variables in the expression
     */
    public Expression varTerm();
    
    /**
     * 
     * @return an expression representing the product of the variables in the expression
     */
    public Expression varFactor();
    
    /**
     * @return true if the expression represents a zero,
     
    public boolean isZero();
   */
    
    
    /**
     * Constructs an expression representing the derivative 
     * of this expression with respect to a given variable 
     * @param var the variable represented by an Expression object with respect to 
     * which the expression is differentiated
     * @return an Expression object representing the differentiated form
     * of this object
     */
    public Expression differentiate(Expression var);
    

    
    
    public List<Expression> terms();
    
    public List<Expression> factors();

	@Override public String toString();
	
	/**
	 * 
	 * @param other
	 * @return true if and only if the other object is an Expression
	 * of the same type and it represents a  
	 */
	@Override public boolean equals(Object other);
	
	/*
	 * Satisfies the object contract by returning the same value
	 * for two objects for which the equals method returns true
	 * The method follows the guidelines in the Stack Overflow post
	 * http://stackoverflow.com/questions/113511/best-implementation-for-hashcode-method
	 * 
	 * @return a hash code value for the Expression object
	 */
	@Override public int hashCode();
}
