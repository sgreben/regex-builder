package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;
import sgreben.regex_builder.tokens.PLUS;
import sgreben.regex_builder.tokens.TOKEN;

public class RepeatAtLeastPossessive extends RepeatAtLeast {

    public RepeatAtLeastPossessive(Expression child, Integer lowerBound) {
        super(child, lowerBound);
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        super.compile(index, output);
        output.add(new PLUS());
    }
}
