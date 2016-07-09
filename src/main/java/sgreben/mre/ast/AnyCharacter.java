package sgreben.mre.ast;

public class AnyCharacter extends Nullary {
	public void compile(StringBuilder sb) {
		sb.append(".");
	}
}