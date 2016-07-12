package sgreben.mre.expression;

import sgreben.mre.tokens.*;

public class Choice extends Nary {
	public Choice(Expression... children) { super(children); }

	public void compile(java.util.List<TOKEN> output) {
		boolean first = true;
		output.add(new START_GROUP_NON_CAPTURING());
		for(Expression child : children()) {
			if(first) {
				first = false;
			} else {
				output.add(new PIPE());
			}
			child.compile(output);
		}
		output.add(new END_GROUP());
	}
}