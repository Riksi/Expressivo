package expressivo;

public class CompoundTest {

	
	
	String exp1;
	String exp2;
	Number n = new Number(19.2);
	Variable v = new Variable("var");
	Expression ot = this.generateOtherExpression(n,v);
	Expression bb = this.generateThisExpression(n,v);
	Expression bc1 = this.generateThisExpression(bb,n);
	Expression bc2 = this.generateThisExpression(v,ot);
	Expression cc = this.generateThisExpression(ot,bb);
	Expression ee = this.generateThisExpression(bb,cc);
	Expression same = this.generateThisExpression(n,v);
	Expression same2 = this.generateThisExpression(n,v);
	Expression diffOrder = this.generateThisExpression(v,n);
	Expression group1 = this.generateThisExpression(this.generateOtherExpression(bb,new Number(10.0)),new Variable("var"));
	//(n*v + 10)*var != n*(v+10)*var 
	Expression group2 = this.generateThisExpression(this.generateThisExpression(n,
			this.generateOtherExpression(v,new Number(10.0))),new Variable("var"));
	Expression group2sameMeaning = this.generateThisExpression(n,this.generateOtherExpression(this.generateOtherExpression(v,new Number(10.0)),new Variable("var")));
	
	
	
	private CompoundTest(String exp1, String exp2){
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public Expression generateThisExpression(Expression e1, Expression e2){
		if(this.exp1 =="Plus"){
			return new Plus(e1,e2);
		}
		else return new Multiply(e1,e2);
	}
	
	public Expression generateOtherExpression(Expression e1, Expression e2){
		if(this.exp1 =="Plus"){
			return new Multiply(e1,e2);
		}
		else return new Plus(e1,e2);
	}


}
