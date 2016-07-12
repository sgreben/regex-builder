package sgreben.mre.expression;

import sgreben.mre.tokens.*;

public class Sequence extends Nary {
	public Sequence(Expression... children) { super(children); }
	
	public void compile(java.util.List<TOKEN> output) {
		for(Expression child : children()) {
			child.compile(output);
		}
	}
}