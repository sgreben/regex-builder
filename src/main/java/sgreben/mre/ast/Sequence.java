package sgreben.mre.expression;

public class Sequence extends Nary {
	public Sequence(Expression... children) { super(children); }
	
	public void compile(StringBuilder sb) {
		for(Expression child : children()) {
			child.compile(sb);
		}
	}
}