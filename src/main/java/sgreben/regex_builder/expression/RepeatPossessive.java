package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;
import sgreben.regex_builder.tokens.PLUS;
import sgreben.regex_builder.tokens.TOKEN;

public class RepeatPossessive extends Repeat {
    public RepeatPossessive(Expression child, Integer lowerBound, Integer upperBound) {
        super(child, lowerBound, upperBound);
    }

    public RepeatPossessive(Expression child, Integer bound) {
        super(child, bound, bound);
    }

    public RepeatPossessive(Expression child) {
        super(child);
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        super.compile(index, output);
        output.add(new PLUS());
    }
}