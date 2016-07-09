package sgreben.mre.ast;

public class Optional extends Unary {
	public Optional(Ast child) { super(child); }
	public void compile(StringBuilder sb) {
		sb.append("(");
		for(Ast child : children()) {
			child.compile(sb);
		}
		sb.append(")?");
	}
}