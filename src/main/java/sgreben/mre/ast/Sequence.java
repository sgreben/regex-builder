package sgreben.mre.ast;

public class Sequence extends Nary {
	public Sequence(Ast... children) { super(children); }
	
	public void compile(StringBuilder sb) {
		for(Ast child : children()) {
			child.compile(sb);
		}
	}
}