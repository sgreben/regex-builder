package sgreben.mre;

import java.util.List;
import sgreben.mre.ast.*;

public class CaptureGroup extends Unary implements Ast {
	private List<CaptureGroup> nested;
	private int index;
	private boolean seen;
	
	public CaptureGroup(Ast ast) { super(ast); }
	
	public void clear() {
		this.nested = null;
		this.index = -1;
		this.seen = false;
	}
	
	public void setNested(List<CaptureGroup> nested) {
		this.nested = nested;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	public boolean getSeen() {
		return seen;
	}
	
	public Captured getCaptured() {
		return null;
	}
	
	public void compile(StringBuilder sb) {
		for(Ast child : children()) {
			child.compile(sb);
		}
	}
}