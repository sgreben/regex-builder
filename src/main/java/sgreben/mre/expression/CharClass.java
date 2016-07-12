package sgreben.mre.expression;

import sgreben.mre.tokens.*;

public class CharClass extends Nullary {
	private final String rawClass; 
	
	public CharClass(String rawClass) {
		this.rawClass = rawClass;
	}
	
	public void compile(java.util.List<TOKEN> output) {
		output.add(new CHAR_CLASS(rawClass));
	}
}
