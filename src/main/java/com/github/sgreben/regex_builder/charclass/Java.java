package com.github.sgreben.regex_builder.charclass;

import java.util.List;
import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.CHAR_CLASS_NAMED;
import com.github.sgreben.regex_builder.tokens.TOKEN;

/**
 * Created by Sergey on 04.10.2016.
 */
public class Java extends Nullary {
    private final String name;

    public Java(String name) {
        this.name = name;
    }

    @Override
    public CharClass complement() {
        return new RawComplement(this);
    }

    @Override
    public void compile(List<TOKEN> output) {
        output.add(new CHAR_CLASS_NAMED(name));
    }
}
