package sgreben.mre.expression;

public class Many extends Unary {
	public Many(Expression child) { super(child); }
	public void compile(StringBuilder sb) {
		sb.append("(");
		for(Expression child : children()) {
			child.compile(sb);
		}
		sb.append(")*");
	}
}