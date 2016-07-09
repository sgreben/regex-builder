package sgreben.mre.ast;

public class Many1 extends Unary {
	public Many1(Ast child) { super(child); }
	
	public void compile(StringBuilder sb) {
		sb.append("(");
		for(Ast child : children()) {
			child.compile(sb);
		}
		sb.append(")+");
	}
}