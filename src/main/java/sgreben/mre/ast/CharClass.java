package sgreben.mre.ast;

public class CharClass extends Nullary {
	private final sgreben.mre.CharClass charClass; 
	public CharClass(sgreben.mre.CharClass charClass) {
		this.charClass = charClass;
	}
	public String getRegexString() {
		return charClass.getRegexString();
	}
}
