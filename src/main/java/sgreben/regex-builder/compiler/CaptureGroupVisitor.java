package sgreben.regex_builder.compiler;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.CaptureGroupIndex;
import sgreben.regex_builder.expression.ExpressionVisitor;
import sgreben.regex_builder.Expression;

class CaptureGroupVisitor implements ExpressionVisitor {
	private static class Frame {
		public CaptureGroup group;
		public Frame(CaptureGroup group) {
			this.group = group;
		}
	}
	
	private LinkedList<Frame> stack;
	private CaptureGroupIndex groupIndex;
	private int maxGroupIndex;
	
	public CaptureGroupVisitor() {
		this.stack = new LinkedList<Frame>();
		this.groupIndex = new CaptureGroupIndex();
		this.maxGroupIndex = 1;
	}
		
	public CaptureGroupIndex getIndex() {
		return groupIndex;
	}

	public void visitPre(Expression node) {
		if(node.getClass() == CaptureGroup.class) {
			CaptureGroup group = (CaptureGroup)node;
			groupIndex.setIndex(group, maxGroupIndex);
			stack.addFirst(new Frame(group));
			maxGroupIndex += 1;
		}
	}
	
	public void visitPost(Expression node) {
		if(node.getClass() == CaptureGroup.class) {
			stack.removeFirst();
		}
	}
}