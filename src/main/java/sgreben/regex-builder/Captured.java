package sgreben.regex_builder;

import java.util.HashMap;

public interface Captured {
	/*private final CaptureGroup group;
	private final HashMap<CaptureGroup, Captured> nested;
	
	public Captured(CaptureGroup group, int index) {
		this.group = group;
		this.nested = null;
	}*/
	
	Captured get(int index);
	Captured getNested(CaptureGroup group);
	String getString(int index);
	String getString();
	int size();
	int start();
	int end();
}