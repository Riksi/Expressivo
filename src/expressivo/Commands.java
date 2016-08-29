package expressivo;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Exception;
public class Commands {
    

    
    /**
     * Takes an expression and a string containing a variable with respect to 
     * which the expression should be differentiated and returns the differentiated form
     * Requires that the string only contains the letters a-zA-Z with no spaces
     * @param exp Expression to differentiate 
     * @param v string representing variable of differentiation which must contain letters a-zA-Z only
     * @return a string representing the differentiated form of exp
     */
    public static String differentiate(Expression exp, String v){
        assert v.matches("[a-zA-Z]+");
        Expression var = Expression.parse(v);
        return exp.differentiate(var).toString();
    }
    
    
    /**
     * Takes an expression to simplify and a string containing a mapping of variables to values and 
     * returns a string representing the simplfied expression 
     * @param exp Expression to simplify
     * @param vars which must be in the form (var1=num)( var2=num)*
     * @return a string representing the simplified form of the expression
     */
    public static String simplify(Expression exp, String vars){
        String pattern = "[a-zA-Z]+="
                + "([0-9]+('.'[0-9]*)?|'.'[0-9]+)(('e'|'E')('-'|'+')?[0-9]+)?";
        assert vars.matches(pattern+"(\\s"+pattern+")*");
        Map<Expression,Double> values = new HashMap<>();
           String[] varMap = vars.split("\\s");
            for(String varPair:varMap){
                String[] pair = varPair.split("=");
                values.put(Expression.parse(pair[0]),Double.valueOf(pair[1]));
            }
        return exp.simplify(values).toString();
     
    }
    
}
