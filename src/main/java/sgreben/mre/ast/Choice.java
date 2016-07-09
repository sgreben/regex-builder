package sgreben.mre.ast;

public class Choice extends Nary {
	public Choice(Ast... children) { super(children); }
	public void compile(StringBuilder sb) {
		boolean first = true;
		for(Ast child : children()) {
			if(!first) {
				sb.append("|");
			}
			child.compile(sb);
			first = false;
		}
	}
}