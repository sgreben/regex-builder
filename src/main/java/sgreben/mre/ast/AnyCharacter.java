package sgreben.mre.expression;

public class AnyCharacter extends Nullary {
	public void compile(StringBuilder sb) {
		sb.append(".");
	}
}