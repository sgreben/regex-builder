package sgreben.regex_builder;

import java.util.*;

public class CaptureGroupIndex {
	private final Map<CaptureGroup, Integer> groupIndex;
	private final Map<CaptureGroup, List<CaptureGroup>> groupNesting;
	
	public CaptureGroupIndex() {
		this.groupIndex = new HashMap<CaptureGroup, Integer>();
		this.groupNesting = new HashMap<CaptureGroup, List<CaptureGroup>>();
	}
	
	public Integer getIndex(CaptureGroup group) {
		return groupIndex.get(group);
	}
	
	public void setIndex(CaptureGroup group, Integer index) {
		groupIndex.put(group, index);
	}
	
	public void setNested(CaptureGroup group, List<CaptureGroup> nested) {
		groupNesting.put(group, nested);
	}
	
	public List<CaptureGroup> getNested(CaptureGroup group) {
		return groupNesting.get(group);
	}
}