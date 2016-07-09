package sgreben.mre.ast;

public class Many extends Unary {
	public Many(Ast child) { super(child); }
	public void compile(StringBuilder sb) {
		sb.append("(");
		for(Ast child : children()) {
			child.compile(sb);
		}
		sb.append(")*");
	}
}