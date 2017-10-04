import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Stanley on 9/19/2017.
 */
class LexerTest {
    @Test
    void hasNext() {
        String input0 = "and";
        Lexer lex0 = new Lexer(input0);
        assertEquals(lex0.hasNext(), true);
        String input1 = "123";
        Lexer lex1 = new Lexer(input1);
        assertEquals(lex1.hasNext(), true);
        String input2 = "boiii";
        Lexer lex2 = new Lexer(input2);
        assertEquals(lex2.hasNext(), true);
        String input3 = "USSR+USA=PEACE";
        Lexer lex3 = new Lexer(input3);
        assertEquals(lex3.hasNext(), true);
        String input4 = "KANSAS_CITY=KANSAS'S_CITY";
        Lexer lex4 = new Lexer(input4);
        assertEquals(lex4.hasNext(), false);
    }

    @Test
    void next() {
        String input = "(a COMMENT and \t b) or c";
        Lexer lexer = new Lexer(input);
        List<String> tokenToStringList = new ArrayList<>();
        boolean thrown = false;

        try {
            while (lexer.hasNext()){
                tokenToStringList.add(lexer.next().toString());
            }
        }catch (ParserException e) {
            thrown = true;
        }
        assertEquals(tokenToStringList.get(0), "LocationalToken{type = OPEN, data = Optional.empty, location=1}");
        assertEquals(tokenToStringList.get(1), "LocationalToken{type = ID, data = Optional[a], location=2}");
        assertEquals(tokenToStringList.get(2), "LocationalToken{type = WHITESPACE, data = Optional.empty, location=3}");
        assertEquals(tokenToStringList.get(3), "LocationalToken{type = WHITESPACE, data = Optional.empty, location=11}");
        assertEquals(tokenToStringList.get(4), "LocationalToken{type = AND, data = Optional.empty, location=14}");
        assertEquals(tokenToStringList.get(5), "LocationalToken{type = WHITESPACE, data = Optional.empty, location=17}");
        assertEquals(tokenToStringList.get(6), "LocationalToken{type = ID, data = Optional[b], location=18}");
        assertEquals(tokenToStringList.get(7), "LocationalToken{type = CLOSE, data = Optional.empty, location=19}");
        assertEquals(tokenToStringList.get(8), "LocationalToken{type = WHITESPACE, data = Optional.empty, location=20}");
        assertEquals(tokenToStringList.get(9), "LocationalToken{type = OR, data = Optional.empty, location=22}");
        assertEquals(tokenToStringList.get(10), "LocationalToken{type = WHITESPACE, data = Optional.empty, location=23}");
        assertEquals(tokenToStringList.get(11), "LocationalToken{type = ID, data = Optional[c], location=24}");
        assertEquals(thrown, false);
    }

    @Test
    void nextValid() {
        String input = "(a COMMENT and \t b) or c";
        Lexer lexer = new Lexer(input);
        List<String> tokenToStringList = new ArrayList<>();
        boolean thrown = false;

        Set<Token.Type> valid = new HashSet<>();
        valid.add(Token.Type.OPEN);
        valid.add(Token.Type.ID);
        valid.add(Token.Type.AND);
        Set<Token.Type> invalid = new HashSet<>();
        invalid.add(Token.Type.NUMBER);
        invalid.add(Token.Type.OR);
        invalid.add(Token.Type.BINARYOP);

        try {
            while (lexer.hasNext()){
                tokenToStringList.add(lexer.nextValid(valid, invalid).toString());
            }
        }catch (ParserException e) {
            thrown = true;
        }
        assertEquals(tokenToStringList.get(0), "Optional[LocationalToken{type = OPEN, data = Optional.empty, location=1}]");
        assertEquals(tokenToStringList.get(1), "Optional[LocationalToken{type = ID, data = Optional[a], location=2}]");
        assertEquals(tokenToStringList.get(2), "Optional[LocationalToken{type = AND, data = Optional.empty, location=14}]");
        assertEquals(tokenToStringList.get(3), "Optional[LocationalToken{type = ID, data = Optional[b], location=18}]");
        assertEquals(thrown, true);
    }

}