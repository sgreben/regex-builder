package sgreben.mre.expression;

public class Optional extends Unary {
	public Optional(Expression child) { super(child); }
	public void compile(StringBuilder sb) {
		sb.append("(");
		for(Expression child : children()) {
			child.compile(sb);
		}
		sb.append(")?");
	}
}