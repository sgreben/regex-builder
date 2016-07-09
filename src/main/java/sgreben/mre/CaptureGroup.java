package sgreben.mre;

import sgreben.mre.ast.*;

public class CaptureGroup extends Unary implements Ast {
	
	public CaptureGroup(Ast ast) { super(ast); }
	
	void clearCaptured() {}
	
	public Captured getCaptured() {
		return null;
	}
}