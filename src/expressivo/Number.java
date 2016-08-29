package expressivo;
import java.util.*;

class Number implements Expression{
	
	private final double num;
	
	//Abstraction function
	//	represents the positive number num
	//Rep invariant
	//	num >= 0
	//Safety from rep exposure
	//	all fields are immutable and final
	
	/**
	 * 
	 * @param num number that will be represented as an Expression object,
	 * requires that num >= 0 and num <= double.MAX_VALUE
	 */
	
	private void checkRep(){
		assert num >= 0;
	}
	
	public Number(double n){
		num = n;
		checkRep();
	};
	
	/**
     * @return a list containing this Number object
     */
    public List<Expression> terms(){
        List <Expression> terms = new ArrayList<>();
        terms.add(this);
        return terms;
    };
    
    /**
     * @return a list containing this Number object
     */
    public List<Expression> factors(){
        List <Expression> factors = new ArrayList<>();
        factors.add(this);
        return factors;
    };
	
	

	//public double value(){};
	//public List<Expression> terms(){return null;}
	//public List<Expression> factors(){return null;}
	
	
	/**
	 * @return a string representation of the number represented by the Number object
	 */
	public String toString(){
		return num+"";
		}
	
	/**
	 * @param other any Object
	 * @return true if Object is an instance of Number 
	 * the number it represents is equal to the number represented
	 * by the other Object according to Double.equals
	 * otherwise returns false
	 */
	
	public boolean equals(Object other){
		if(!(other instanceof Number)){return false;}
		Number otherNum = (Number)other;
		return num == otherNum.num;
	}
	
	//Uses d(constant)/dt = 0
	public Expression differentiate(Expression var){
	    return new Number(0);
	}

	public Expression simplify(Map<Expression,Double> values){
	    return this;
	}
	
    public double numTerm(){
        return num;
    }
    
    public double numFactor(){
        return num;
    }
    
    public Expression varTerm(){
        return new Number(0);
    }
    
    public Expression varFactor(){
        return new Number(1);
    }
	
	public int hashCode(){
	    int result = 43;
	    long c = Double.doubleToLongBits(num);
	    return 37*result + (int)(c&(c>>>32));
	}

}


