package sgreben.mre.ast;

public class Literal extends Nullary {
	private final String literal; 
	public Literal(String literal) {
		this.literal = literal;
	}
	public String getLiteral() {
		return literal;
	}
}