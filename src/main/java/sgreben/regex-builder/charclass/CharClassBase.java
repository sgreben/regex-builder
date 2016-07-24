package sgreben.regex_builder.charclass;

import java.lang.Iterable;
import sgreben.regex_builder.CharClass;

public abstract class CharClassBase implements CharClass {
	public void accept(CharClassVisitor visitor) {
		visitor.visitPre(this);
		for(CharClass child : children ()) {
			child.accept(visitor);
		}
		visitor.visitPost(this);
	}
}