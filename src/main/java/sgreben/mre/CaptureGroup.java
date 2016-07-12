package sgreben.mre;

import java.util.List;
import sgreben.mre.expression.*;
import sgreben.mre.tokens.*;

public class CaptureGroup extends Unary implements Expression {
	private java.util.regex.Matcher rawMatcher;
	
	public CaptureGroup(Expression expression) { super(expression); }
	
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_GROUP());
		for(Expression child : children()) {
			child.compile(output);
		}
		output.add(new END_GROUP());
	}
}