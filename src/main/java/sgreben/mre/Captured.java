package sgreben.mre;

public interface Captured {
	Captured get(int index);
	Captured getNested(CaptureGroup group);
	String getString(int index);
	int length();
}