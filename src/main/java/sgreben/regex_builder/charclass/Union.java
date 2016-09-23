package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.CharClass;

public class Union extends Nary {
	public Union(CharClass... children) { super(children); }

	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		for(CharClass child : children()) {
			child.compile(output);
		}
		output.add(new END_CHAR_CLASS());
	}
}