package sgreben.regex_builder.compiler;

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.CaptureGroupIndex;
import sgreben.regex_builder.expression.ExpressionVisitor;
import sgreben.regex_builder.Expression;

class CaptureGroupVisitor implements ExpressionVisitor {
	private CaptureGroupIndex groupIndex;
	private int maxGroupIndex;
	
	public CaptureGroupVisitor() {
		this.groupIndex = new CaptureGroupIndex();
		this.maxGroupIndex = 1;
	}
		
	public CaptureGroupIndex get() {
		return groupIndex;
	}

	public void visitPre(Expression node) {
		if(node.getClass() == CaptureGroup.class) {
			CaptureGroup group = (CaptureGroup)node;
			groupIndex.put(group, maxGroupIndex);
			maxGroupIndex += 1;
		}
	}
	
	public void visitPost(Expression node) {}
}