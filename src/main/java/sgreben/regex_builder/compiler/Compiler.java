package sgreben.regex_builder.compiler;

import java.util.LinkedList;

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.CaptureGroupIndex;
import sgreben.regex_builder.Pattern;
import sgreben.regex_builder.Expression;
import sgreben.regex_builder.tokens.*;

public class Compiler {
	public static Pattern compile(Expression expression) {
		CaptureGroupVisitor visitor = new CaptureGroupVisitor();
		CaptureGroup entireMatch = new CaptureGroup(expression);
		LinkedList<TOKEN> tokens = new LinkedList<TOKEN>();
		StringBuilder sb = new StringBuilder();
		entireMatch.accept(visitor);
		CaptureGroupIndex index = visitor.get();
		entireMatch.compile(index, tokens);
		for(TOKEN op : tokens) {
			sb.append(op.regexString());
		}
		String regexString = sb.toString();
		java.util.regex.Pattern rawPattern = java.util.regex.Pattern.compile(regexString);
		return new Pattern(rawPattern, index);
	}
	
}
