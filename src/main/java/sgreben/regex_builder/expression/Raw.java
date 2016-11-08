package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.RAW;
import sgreben.regex_builder.tokens.TOKEN;

/* Raw regex string (for regex syntax not yet modeled in the builder).
 *
 */
public class Raw extends Nullary {
    private final String rawClass;

    public Raw(String rawClass) {
        this.rawClass = rawClass;
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new RAW(rawClass));
    }
}
