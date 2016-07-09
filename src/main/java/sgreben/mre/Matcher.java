package sgreben.mre;

public class Matcher {
	private final java.util.regex.Matcher matcher;
	public Matcher(java.util.regex.Matcher matcher) {
		this.matcher = matcher;
	}
	public boolean matches() {
		return false;
	}
	public boolean find() {
		return false;
	}
	public boolean find(int offset) {
		return false;
	}
	public Captured captured() {
		return null;
	}
}