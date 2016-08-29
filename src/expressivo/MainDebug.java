package expressivo;

//Notes:
//For the moment, will not consider subtract, divide and power
//Will do nothing for the methods related to power
//Will not consider the possibility of '-' in sum and '/' in product

import java.util.List;
import java.util.Stack;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;

public class MainDebug {
    public static void main(String[] args) {
       // String exp1 = "a * 12 * (a + 12)";
        String exp2 = "e*5e9";
       // System.out.println(parse(exp1));
        System.out.println(parse(exp2));
    }

    /**
     * Parse a string into an integer arithmetic expression, displaying various
     * debugging output.
     */
    public static Expression parse(String string) {
       // Create a stream of characters from the string
       CharStream stream = new ANTLRInputStream(string);

       // Make a parser
       ExpressionParser parser = makeParser(stream);
       
       // Generate the parse tree using the starter rule.
       // root is the starter rule for this grammar.
       // Other grammars may have different names for the starter rule.
       ParseTree tree = parser.root();
       
       // *** Debugging option #1: print the tree to the console
       System.err.println(tree.toStringTree(parser));

       // *** Debugging option #2: show the tree in a window
       Trees.inspect(tree, parser);
       
       // *** Debugging option #3: walk the tree with a listener
       //new ParseTreeWalker().walk(new PrintEverything(), tree);
       
       MakeExpressionDebug exprMaker = new MakeExpressionDebug();
       new ParseTreeWalker().walk(exprMaker,tree);
       return exprMaker.getExpression();
   }
    
    private static ExpressionParser makeParser(CharStream stream) {
        // Make a lexer.  This converts the stream of characters into a 
        // stream of tokens.  A token is a character group, like "<i>"
        // or "</i>".  Note that this doesn't start reading the character stream yet,
        // it just sets up the lexer to read it.
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Make a parser whose input comes from the token stream produced by the lexer.
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.reportErrorsAsExceptions();
        
        return parser;
    }
    
}

class MakeExpressionDebug implements ExpressionListener{
    private Stack<Expression> stack = new Stack<>();
    //Invariant: stack contains the Expression value of each parse subtree
    //that has been fully-walked so far, but whose parent has not yer been exited
    //by the walk. The stack is ordered by recency of visit, so that 
    //the top of the stack is the Expression for the most recently walked 
    //subtree.
    //
    //At the start of the walk, the stack is empty as no subtrees have 
    //been fully walked.
    //
    //Whenever a node is exited by the walk, the Expression values of its 
    //children are  on top of the stack, with the order that the last
    //child is on top.
    //
    //To preserve the invariant, we pop the Expression values from the stack,
    //combine them with the appropriate Expression producer, and
    //push back an Expression value representing the entire subtree
    //under the node
    //
    //At the end of the walk, after all subtrees have been walked and the root
    //has been exited, only the entire tree satisfied the invariant property 
    //so the top fof the stack is the Expression representing the entire parse tree
    
    /**
     * Returns the epxression constructed by this listener object
     * Requires that the listener has completely walked over an Expression
     * parse tree using ParseTreeWalker
     * @return IntegerExpression for the parse tree that was walked
     */
    public Expression getExpression(){
        return stack.get(0);
    }
    
    @Override public void enterRoot(ExpressionParser.RootContext context) {
        System.err.println("entering root");
    }
    
    @Override public void exitRoot(ExpressionParser.RootContext context) {
        System.err.println("exiting root");
    }

    @Override public void enterSum(ExpressionParser.SumContext context) {
        System.err.println("entering sum");
    }
    
    @Override public void exitSum(ExpressionParser.SumContext context) {
        System.err.println("exiting sum");
        //matched the product ('+' product)* rule
        List<ExpressionParser.ProductContext> terms = context.product();
        assert stack.size() >= terms.size();
        
        //the pattern must have at least 1 child 
        //pop the last child
        assert terms.size() > 0;
        Expression sum = stack.pop();
        
        //pop the older children, one by one, and add them on
        for(int i = 1; i < terms.size(); ++i){
            sum = new Plus(stack.pop(),sum);
        }
        
        // the result is this subtree's Expression
        stack.push(sum);
    }
    
    @Override public void enterProduct(ExpressionParser.ProductContext context) {
        System.err.println("entering product");
    }
    
    @Override public void exitProduct(ExpressionParser.ProductContext context) {
        System.err.println("exiting product");
        //matched the product ('+' product)* rule
        
        List<ExpressionParser.PrimitiveContext> factors = context.primitive();
        assert stack.size() >= factors.size();
        
        //the pattern must have at least 1 child 
        //pop the last child
        assert factors.size() > 0;
        Expression product = stack.pop();
        
        //pop the older children, one by one, and multiply them on
        for(int i = 1; i < factors.size(); ++i){
            product = new Multiply(stack.pop(),product);
        }
        
        // the result is this subtree's Expression
        stack.push(product);
    }
    
    @Override public void enterPrimitive(ExpressionParser.PrimitiveContext context) {
        System.err.println("entering primitive");
    }
    
    @Override public void exitPrimitive(ExpressionParser.PrimitiveContext context) {
        System.err.println("exiting primitive");
        if(context.NUMBER() != null){
            //matched the number alternative
            double n = Double.valueOf(context.NUMBER().getText());
            Expression number = new Number(n);
            stack.push(number);
        } else if(context.VAR() != null){
            //matched the VAR alternative
            String v = context.VAR().getText();
            Expression var = new Variable(v);
            stack.push(var);
        }
        else{
            //matched the '(' sum ')' alternative
            //do nothing, because sum's value is already on the stack
        }
    }
    

    @Override public void visitTerminal(TerminalNode terminal) {
        System.err.println("terminal " + terminal.getText());  
    }
    
    // don't need these here, so just make them empty implementations
    @Override public void enterEveryRule(ParserRuleContext context) { 
    }
    
    @Override public void exitEveryRule(ParserRuleContext context) {
    }
    
    @Override public void visitErrorNode(ErrorNode node) { }       
}


class PrintEverything implements ExpressionListener {
    
    @Override public void enterRoot(ExpressionParser.RootContext context) {
        System.err.println("entering root");
    }
    @Override public void exitRoot(ExpressionParser.RootContext context) {
        System.err.println("exiting root");
    }

    @Override public void enterSum(ExpressionParser.SumContext context) {
        System.err.println("entering sum");
    }
    @Override public void exitSum(ExpressionParser.SumContext context) {
        System.err.println("exiting sum");
    }
    
    @Override public void enterProduct(ExpressionParser.ProductContext context) {
        System.err.println("entering product");
    }
    
    @Override public void exitProduct(ExpressionParser.ProductContext context) {
        System.err.println("exiting product");
    }

    @Override public void enterPrimitive(ExpressionParser.PrimitiveContext context) {
        System.err.println("entering primitive");
    }
    
    @Override public void exitPrimitive(ExpressionParser.PrimitiveContext context) {
        System.err.println("exiting primitive");
    }
    

    @Override public void visitTerminal(TerminalNode terminal) {
        System.err.println("terminal " + terminal.getText());            
    }
    
    // don't need these here, so just make them empty implementations
    @Override public void enterEveryRule(ParserRuleContext context) { 
    }
    
    @Override public void exitEveryRule(ParserRuleContext context) {
    }
    
    @Override public void visitErrorNode(ErrorNode node) { }         
}
