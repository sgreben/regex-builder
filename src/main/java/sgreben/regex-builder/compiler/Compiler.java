package sgreben.regex_builder.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.CaptureGroupIndex;
import sgreben.regex_builder.Pattern;
import sgreben.regex_builder.expression.*;
import sgreben.regex_builder.tokens.*;

public class Compiler {
	public static Pattern compile(Expression expression) {
		CaptureGroupVisitor visitor = new CaptureGroupVisitor();
		CaptureGroup entireMatch = new CaptureGroup(expression);
		entireMatch.accept(visitor);
		CaptureGroupIndex index = visitor.getIndex();
		LinkedList<TOKEN> tokens = new LinkedList<TOKEN>();
		entireMatch.compile(tokens);
		StringBuilder sb = new StringBuilder();
		for(TOKEN op : tokens) {
			sb.append(op.regexString());
		}
		String regexString = sb.toString();
		java.util.regex.Pattern rawPattern = java.util.regex.Pattern.compile(regexString);
		return new Pattern(rawPattern, index);
	}
	
}
