package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class AnyCharacter extends Nullary {
	public AnyCharacter() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new DOT());
	}
}