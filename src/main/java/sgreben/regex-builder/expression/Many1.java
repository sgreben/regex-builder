package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.Expression;

public class Many1 extends Unary {
	public Many1(Expression child) { super(child); }
	
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		output.add(new START_GROUP_NON_CAPTURING());
		for(Expression child : children()) {
			child.compile(index, output);
		}
		output.add(new END_GROUP());
		output.add(new PLUS());
	}
	public Expression possessive() {
		return new Many1Greedy(child());
	}
}