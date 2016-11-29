package com.github.sgreben.regex_builder.compiler;

import com.github.sgreben.regex_builder.CaptureGroup;
import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.expression.ExpressionVisitor;
import com.github.sgreben.regex_builder.Expression;

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