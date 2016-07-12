package sgreben.mre.expression;

import sgreben.mre.tokens.*;

public class AnyCharacter extends Nullary {
	public void compile(java.util.List<TOKEN> output) {
		output.add(new DOT());
	}
}