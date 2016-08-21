package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.Expression;

public class PositiveLookbehind extends Unary {
	public PositiveLookbehind(Expression child) { super(child); }
	
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		output.add(new START_POSITIVE_LOOKBEHIND());
		for(Expression child : children()) {
			child.compile(index, output);
		}
		output.add(new END_GROUP());
	}
}