package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.CharClass;

public class Intersection extends Nary {
	public Intersection(CharClass... children) { super(children); }

	public void compile(java.util.List<TOKEN> output) {
		boolean first = true;
		output.add(new START_CHAR_CLASS());
		for(CharClass child : children()) {
			if(!first) {
				output.add(new CHAR_CLASS_INTERSECTION());
			}
			first = false;
			output.add(new START_CHAR_CLASS());
			child.compile(output);
			output.add(new END_CHAR_CLASS());

		}
		output.add(new END_CHAR_CLASS());
	}
}