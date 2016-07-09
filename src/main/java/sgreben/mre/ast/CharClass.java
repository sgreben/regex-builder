package sgreben.mre.expression;

public class CharClass extends Nullary {
	private final String rawClass; 
	public CharClass(String rawClass) {
		this.rawClass = rawClass;
	}
	public void compile(StringBuilder sb) {
		sb.append(rawClass);
	}
}
