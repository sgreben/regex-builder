package sgreben.mre.ast;

public class CharClass extends Nullary {
	private final sgreben.mre.charclass.CharClass charClass; 
	public CharClass(sgreben.mre.charclass.CharClass charClass) {
		this.charClass = charClass;
	}
	public String getRegexString() {
		return charClass.getRegexString();
	}
	public void compile(StringBuilder sb) {
		sb.append(charClass.getRegexString());
	}
}
