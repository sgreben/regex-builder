package com.github.sgreben.regex_builder.compiler;

import java.util.LinkedList;
import com.github.sgreben.regex_builder.CaptureGroup;
import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.Pattern;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class Compiler {
	public static Pattern compile(Expression expression) {
		return compile(expression, 0);
	}

	public static Pattern compile(Expression expression, final int flags) {
		CaptureGroupVisitor visitor = new CaptureGroupVisitor();
		CaptureGroup entireMatch = new CaptureGroup(expression);
		LinkedList<TOKEN> tokens = new LinkedList<TOKEN>();
		StringBuilder sb = new StringBuilder();
		entireMatch.accept(visitor);
		CaptureGroupIndex index = visitor.get();
		entireMatch.compile(index, tokens);
		for (TOKEN op : tokens) {
			sb.append(op.regexString());
		}
		String regexString = sb.toString();
		java.util.regex.Pattern rawPattern = java.util.regex.Pattern.compile(regexString, flags);
		return new Pattern(rawPattern, index);
	}

}
