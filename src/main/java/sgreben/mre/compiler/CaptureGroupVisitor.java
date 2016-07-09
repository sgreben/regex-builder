package sgreben.mre.compiler;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

import sgreben.mre.CaptureGroup;
import sgreben.mre.ast.AstVisitor;
import sgreben.mre.ast.Ast;

class CaptureGroupVisitor implements AstVisitor {
	private static class Frame {
		public CaptureGroup group;
		public LinkedList<CaptureGroup> nested;
		public Frame(CaptureGroup group) {
			this.group = group;
			this.nested = new LinkedList();
		}
	}
	
	private LinkedList<Frame> stack;
	private HashSet<CaptureGroup> groups;
	private int groupIndex;
	
	public Set<CaptureGroup> getCaptureGroups() {
		return Collections.unmodifiableSet(groups);
	}
	
	public CaptureGroupVisitor() {
		this.stack = new LinkedList();;
		this.groupIndex = 0;
	}
	
	public void visitPre(Ast node) {
		if(node.getClass() == CaptureGroup.class) {
			CaptureGroup group = (CaptureGroup)node;
			group.setIndex(groupIndex);
			stack.addFirst(new Frame(group));
			groupIndex += 1;
		}
	}
	
	public void visitPost(Ast node) {
		if(node.getClass() == CaptureGroup.class) {
			Frame top = stack.removeFirst();
			top.group.setNested(top.nested); 
			if(stack.size() > 0) {
				stack.peekFirst().nested.add(top.group);
			}
		}
	}
}