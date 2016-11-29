package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.tokens.CHAR_CLASS_NAMED;
import com.github.sgreben.regex_builder.tokens.TOKEN;

import java.util.List;

public class Posix extends Nullary {
    private final String name;
    public Posix(String name) {
        this.name = name;
    }

    @Override
    public void compile(List<TOKEN> output) {
        output.add(new CHAR_CLASS_NAMED(name));
    }
}
