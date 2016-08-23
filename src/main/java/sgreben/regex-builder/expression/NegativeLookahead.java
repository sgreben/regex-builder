package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.Expression;

public class NegativeLookahead extends Unary {
	public NegativeLookahead(Expression child) { super(child); }
	
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		output.add(new START_NEGATIVE_LOOKAHEAD());
		child().compile(index, output);
		output.add(new END_GROUP());
	}
}