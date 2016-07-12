package sgreben.mre.expression;

import sgreben.mre.tokens.TOKEN;
import sgreben.mre.tokens.LITERAL;

public class Literal extends Nullary {
	private final String literal; 
	public Literal(String literal) {
		this.literal = literal;
	}
	public String getLiteral() {
		return literal;
	}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new LITERAL(literal));
	}
}