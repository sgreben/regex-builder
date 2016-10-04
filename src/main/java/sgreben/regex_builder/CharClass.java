package sgreben.regex_builder;

import sgreben.regex_builder.charclass.*;
import sgreben.regex_builder.tokens.TOKEN;

public abstract class CharClass {
    public static CharClass range(char from, char to) {
        return new Range(from, to);
    }

    public static CharClass range(char... ranges) {
        return new Range(ranges);
    }

    public static CharClass union(CharClass... cs) {
        return new Union(cs);
    }

    public static CharClass union(Object... cs) {
        return union(convertStrings(cs));
    }

    public static CharClass intersection(CharClass... cs) {
        return new Intersection(cs);
    }

    public static CharClass intersection(Object... cs) {
        return intersection(convertStrings(cs));
    }

    public static CharClass complement(CharClass cs) {
        return new Complement(cs);
    }

    public static CharClass anyChar() {
        return new AnyCharacter();
    }

    public static CharClass digit() {
        return new Digit();
    }

    public static CharClass nonDigit() {
        return new NonDigit();
    }

    public static CharClass hexDigit() {
        return range('a', 'f', 'A', 'F', '0', '9');
    }

    public static CharClass nonHexDigit() {
        return complement(hexDigit());
    }

    public static CharClass wordChar() {
        return new WordCharacter();
    }

    public static CharClass nonWordChar() {
        return new NonWordCharacter();
    }

    public static CharClass wordBoundary() {
        return new WordBoundary();
    }

    public static CharClass nonWordBoundary() {
        return new NonWordBoundary();
    }

    public static CharClass whitespaceChar() {
        return new Whitespace();
    }

    public static CharClass nonWhitespaceChar() {
        return new NonWhitespace();
    }

    public static CharClass verticalWhitespaceChar() {
        return new VerticalWhitespace();
    }

    public static CharClass nonVerticalWhitespaceChar() {
        return new NonVerticalWhitespace();
    }

    public static CharClass horizontalWhitespaceChar() {
        return new HorizontalWhitespace();
    }

    public static CharClass nonHorizontalWhitespaceChar() {
        return new NonHorizontalWhitespace();
    }

    public static CharClass beginInput() {
        return new BeginInput();
    }

    public static CharClass endInput() {
        return new EndInput();
    }

    public static CharClass endInputBeforeFinalTerminator() {
        return new EndInputBeforeFinalTerminator();
    }

    public static CharClass oneOf(String chars) {
        return new OneOf(chars);
    }

    private static CharClass[] convertStrings(Object[] os) {
        CharClass[] charClasses = new CharClass[os.length];
        for (int i = 0; i < os.length; ++i) {
            Object o = os[i];
            if (o instanceof Character) {
                charClasses[i] = oneOf("" + (Character) o);
            }
            if (o instanceof String) {
                charClasses[i] = oneOf((String) o);
            }
            if (o instanceof CharClass) {
                charClasses[i] = (CharClass) o;
            }
        }
        return charClasses;
    }

    public abstract java.lang.Iterable<CharClass> children();

    public abstract void accept(CharClassVisitor visitor);

    public abstract void compile(java.util.List<TOKEN> output);

    public static class Posix {
        public static CharClass Lower() {
            return new sgreben.regex_builder.charclass.Posix("Lower");
        }

        public static CharClass Upper() {
            return new sgreben.regex_builder.charclass.Posix("Upper");
        }

        public static CharClass ASCII() {
            return new sgreben.regex_builder.charclass.Posix("ASCII");
        }

        public static CharClass Alpha() {
            return new sgreben.regex_builder.charclass.Posix("Alpha");
        }

        public static CharClass Digit() {
            return new sgreben.regex_builder.charclass.Posix("Digit");
        }

        public static CharClass Alnum() {
            return new sgreben.regex_builder.charclass.Posix("Alnum");
        }

        public static CharClass Punct() {
            return new sgreben.regex_builder.charclass.Posix("Punct");
        }

        public static CharClass Graph() {
            return new sgreben.regex_builder.charclass.Posix("Graph");
        }

        public static CharClass Print() {
            return new sgreben.regex_builder.charclass.Posix("Print");
        }

        public static CharClass Blank() {
            return new sgreben.regex_builder.charclass.Posix("Blank");
        }

        public static CharClass Cntrl() {
            return new sgreben.regex_builder.charclass.Posix("Cntrl");
        }

        public static CharClass XDigit() {
            return new sgreben.regex_builder.charclass.Posix("XDigit");
        }

        public static CharClass Space() {
            return new sgreben.regex_builder.charclass.Posix("Space");
        }
    }

    public static class Java {
        public static CharClass LowerCase() {
            return new sgreben.regex_builder.charclass.Java("javaLowerCase");
        }

        public static CharClass UpperCase() {
            return new sgreben.regex_builder.charclass.Java("javaUpperCase");
        }

        public static CharClass Whitespace() {
            return new sgreben.regex_builder.charclass.Java("javaWhitespace");
        }

        public static CharClass Mirrored() {
            return new sgreben.regex_builder.charclass.Java("javaMirrored");
        }
    }
}