package expressivo;
import java.util.*;

class Variable implements Expression{
	private final String var;
	//Abstraction function
	//	represents the variable whose name is given by the string var
	//Rep invariant
	//	var only contains the characters a-zA-Z
	//Safety from rep exposure
	//	all fields are immutable and final
	
	
	private void checkRep(){
	    assert var.matches("[a-zA-Z]+");
	}
	
	/**
	 * 
	 * @param var variable that will be represented as an Expression object
	 * requires that var only contains the characters a-z,A-Z
	 */
	Variable(String v){
		var = v;
		checkRep();
	};

	
    /**
     * @return a list containing this Variable object
     */
    public List<Expression> terms(){
        List <Expression> terms = new ArrayList<>();
        terms.add(this);
        return terms;
    };
    

    //Returns 1 if the variable is the same as this otherwise returns zero
    public Expression differentiate(Expression var){
        if(this.equals(var)){
            return new Number(1);
        }
        else{
            return new Number(0);
        }
    }
    
    
    //Substitute if map contains this variable, otherwise just return the variable
    public Expression simplify(Map<Expression,Double> values){
        for(Expression var:values.keySet()){
            if(this.equals(var)){
                return new Number(values.get(var));
            }
        }
        return this;
    }
    
    
    public double numTerm(){
        return 0;
    }
    
    public double numFactor(){
        return 1;
    }
    
    public Expression varTerm(){
        return this;
    }
    
    public Expression varFactor(){
        return this;
    }
    

    
    
    /**
     * @return a list containing this Variable object
     */
    public List<Expression> factors(){
        List <Expression> factors = new ArrayList<>();
        factors.add(this);
        return factors;
    };
	/**
	 * @return a string containing the name of the variable represented by the Variable object
	 */
	public String toString(){return var;}
	
	/**
	 * @param other any Object
	 * @return true if other is an instance of Variable and 
	 * if the name of the variable it represents is the same
	 * as the name of variable represented by the other, 
	 * according to String.equals,
	 * otherwise return false
	 */
	public boolean equals(Object other){
		if(!(other instanceof Variable)){return false;}
		Variable otherVar = (Variable) other;
		return var.equals(otherVar.var);
		}
	public int hashCode(){
	    int result = 43;
	    int c = var.hashCode();
	    return 37*result + c;
	}
}
























































































































































































































































































































































































































































































































































