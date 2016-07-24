package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.Expression;

public class Choice extends Nary {
	public Choice(Expression... children) { super(children); }

	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		boolean first = true;
		output.add(new START_GROUP_NON_CAPTURING());
		for(Expression child : children()) {
			if(first) {
				first = false;
			} else {
				output.add(new PIPE());
			}
			child.compile(index, output);
		}
		output.add(new END_GROUP());
	}
}