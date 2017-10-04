import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisjunctiveLexerTest {
    @Test
    void nextValid() {
        String input = "(a and b)";
        DisjunctiveLexer lexer = new DisjunctiveLexer(input);
        List<String> tokenToStringList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                tokenToStringList.add(lexer.nextValid().toString());
            }
        assertEquals(tokenToStringList.get(0), "Optional[LocationalToken{type = OPEN, data = Optional.empty, location=1}]");
        assertEquals(tokenToStringList.get(1), "Optional[LocationalToken{type = ID, data = Optional[a], location=2}]");
        assertEquals(tokenToStringList.get(2), "Optional[LocationalToken{type = AND, data = Optional.empty, location=6}]");
        assertEquals(tokenToStringList.get(3), "Optional[LocationalToken{type = ID, data = Optional[b], location=8}]");
        assertEquals(tokenToStringList.get(4), "Optional[LocationalToken{type = CLOSE, data = Optional.empty, location=9}]");
    }
}