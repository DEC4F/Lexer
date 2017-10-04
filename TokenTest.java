import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Stanley Tian on 9/10/2017.
 */
class TokenTest {

    @Test
    void of() {
        Token t0 = Token.of(Token.Type.NOT, null);
        assertEquals(t0.getTokenType(), Token.Type.NOT);
        assertEquals(t0.getData(), Optional.empty());

        Token t1 = Token.of(Token.Type.AND, null);
        assertEquals(t1.getTokenType(), Token.Type.AND);
        assertEquals(t1.getData(), Optional.empty());

        Token t2 = Token.of(Token.Type.OR, null);
        assertEquals(t2.getTokenType(), Token.Type.OR);
        assertEquals(t2.getData(), Optional.empty());

        Token t3 = Token.of(Token.Type.OPEN, null);
        assertEquals(t3.getTokenType(), Token.Type.OPEN);
        assertEquals(t3.getData(), Optional.empty());

        Token t4 = Token.of(Token.Type.CLOSE, null);
        assertEquals(t4.getTokenType(), Token.Type.CLOSE);
        assertEquals(t4.getData(), Optional.empty());

        Token t5 = Token.of(Token.Type.ID, "boiiiiiii");
        assertEquals(t5.getTokenType(), Token.Type.ID);
        assertEquals(t5.getData().toString(), "Optional[boiiiiiii]");

        Token t6 = Token.of(Token.Type.NUMBER, "7785258");
        assertEquals(t6.getTokenType(), Token.Type.NUMBER);
        assertEquals(t6.getData().toString(), "Optional[7785258]");

        Token t7 = Token.of(Token.Type.BINARYOP, "*");
        assertEquals(t7.getTokenType(), Token.Type.BINARYOP);
        assertEquals(t7.getData().toString(), "Optional[*]");

        Token t8 = Token.of(Token.Type.WHITESPACE, null);
        assertEquals(t8.getTokenType(), Token.Type.WHITESPACE);
        assertEquals(t8.getData(), Optional.empty());
    }

    @Test
    void patternChecker() {
        Token t0 = Token.of(Token.Type.NOT, null);
        assertEquals(t0.getData(), Optional.empty());

        Token t1 = Token.of(Token.Type.AND, null);
        assertEquals(t1.getData(), Optional.empty());

        Token t2 = Token.of(Token.Type.OR, null);
        assertEquals(t2.getData(), Optional.empty());

        Token t3 = Token.of(Token.Type.OPEN, null);
        assertEquals(t3.getData(), Optional.empty());

        Token t4 = Token.of(Token.Type.CLOSE, null);
        assertEquals(t4.getData(), Optional.empty());

        Token t5 = Token.of(Token.Type.ID, "boiii");
        assertEquals(t5.getData().toString(), "Optional[boiii]");

        Token t6 = Token.of(Token.Type.NUMBER, "1995");
        assertEquals(t6.getData().toString(), "Optional[1995]");

        Token t7 = Token.of(Token.Type.BINARYOP, "/");
        assertEquals(t7.getData().toString(), "Optional[/]");

        Token t8 = Token.of(Token.Type.WHITESPACE, null);
        assertEquals(t8.getData(), Optional.empty());
    }

    @Test
    void getIsComplex() {
        String input = "(a and b) or not c";
        Lexer lexer = new Lexer(input);
        int complexityCount = 0;
        List<LocationalToken> tokenList = new ArrayList<>();
        try {
            while (lexer.hasNext())
                tokenList.add(lexer.next());
        } catch (ParserException e) {
            System.out.println("Error: token expected!");
        }
        for (LocationalToken token : tokenList) {
            if (token.getTokenType().getIsComplex())
                complexityCount++;
        }
        assertEquals(complexityCount, 2);
    }
}