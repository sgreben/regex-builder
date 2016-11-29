package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.tokens.TOKEN;
import com.github.sgreben.regex_builder.tokens.RAW;

/* Raw regex string (for regex syntax not yet modeled in the builder).
 *
 */
public class Raw extends Nullary {
    private final String rawClass;

    public Raw(String rawClass) {
        this.rawClass = rawClass;
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new RAW(rawClass));
    }
}
