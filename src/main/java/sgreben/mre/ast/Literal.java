package sgreben.mre.expression;

public class Literal extends Nullary {
	private final String literal; 
	public Literal(String literal) {
		this.literal = literal;
	}
	public String getLiteral() {
		return literal;
	}
	public void compile(StringBuilder sb) {
		sb.append(literal);
	}
}