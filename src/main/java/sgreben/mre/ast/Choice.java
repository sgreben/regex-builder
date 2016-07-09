package sgreben.mre.expression;

public class Choice extends Nary {
	public Choice(Expression... children) { super(children); }
	public void compile(StringBuilder sb) {
		boolean first = true;
		for(Expression child : children()) {
			if(!first) {
				sb.append("|");
			}
			child.compile(sb);
			first = false;
		}
	}
}