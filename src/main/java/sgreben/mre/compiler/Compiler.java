package sgreben.mre.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import sgreben.mre.CaptureGroup;
import sgreben.mre.Pattern;
import sgreben.mre.expression.*;

public class Compiler {
	public static Pattern compile(Expression expression) {
		CaptureGroupVisitor visitor = new CaptureGroupVisitor();
		final StringBuilder sb = new StringBuilder();
		expression.accept(visitor);
		String regexString = sb.toString();
		java.util.regex.Pattern rawPattern =
			java.util.regex.Pattern.compile(regexString);
		Set<CaptureGroup> captureGroups = visitor.getCaptureGroups();
		return new Pattern(rawPattern);
	}
	
}
