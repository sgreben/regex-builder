package sgreben.mre.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import sgreben.mre.CaptureGroup;
import sgreben.mre.CaptureGroupIndex;
import sgreben.mre.Pattern;
import sgreben.mre.expression.*;
import sgreben.mre.tokens.*;

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
		java.util.regex.Pattern rawPattern =
			java.util.regex.Pattern.compile(regexString);
		Set<CaptureGroup> captureGroups = visitor.getCaptureGroups();
		return new Pattern(rawPattern, entireMatch, index);
	}
	
}
