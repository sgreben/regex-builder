package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.END_GROUP;
import com.github.sgreben.regex_builder.tokens.PIPE;
import com.github.sgreben.regex_builder.tokens.START_GROUP_NON_CAPTURING;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class Choice extends Nary {
	public Choice(Expression... children) { super(children); }

	@Override
	public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
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