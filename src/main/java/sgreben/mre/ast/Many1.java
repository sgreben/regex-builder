package sgreben.mre.expression;

public class Many1 extends Unary {
	public Many1(Expression child) { super(child); }
	
	public void compile(StringBuilder sb) {
		sb.append("(");
		for(Expression child : children()) {
			child.compile(sb);
		}
		sb.append(")+");
	}
}