package sgreben.regex_builder;

import java.util.*;

public class CaptureGroupIndex {
	private final Map<CaptureGroup, Integer> groupIndex;
	
	public CaptureGroupIndex() {
		this.groupIndex = new HashMap<CaptureGroup, Integer>();
	}
	
	public Integer getIndex(CaptureGroup group) {
		return groupIndex.get(group);
	}
	
	public void setIndex(CaptureGroup group, Integer index) {
		groupIndex.put(group, index);
	}
}