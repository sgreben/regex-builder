package sgreben.mre.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import sgreben.mre.CaptureGroup;
import sgreben.mre.ast.*;

public class Compiler {
	public void compile(Ast ast) {
		CaptureGroupVisitor visitor = new CaptureGroupVisitor();
		final StringBuilder expression = new StringBuilder();
		ast.accept(visitor);
		
		final Set<CaptureGroup> captureGroups;
	}
	
}
