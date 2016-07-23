package sgreben.regex_builder;

import java.util.LinkedList;

public class Replacement {
	private final LinkedList<ReplacementPart> parts;
	
	public Replacement() {
		parts = new LinkedList<ReplacementPart>();
	}
	
	public void addPart(ReplacementPart part) {
		parts.add(part);
	}
	
	public String toReplacementString(CaptureGroupIndex index) {
		StringBuilder sb = new StringBuilder();
		for(ReplacementPart part : parts) {
			sb.append(part.toReplacementString(index));
		}
		return sb.toString();
	}
}