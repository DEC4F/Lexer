import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {
    @Test
    void build() {
        Identifier identifier1 = null;
        Identifier identifier2 = null;
        LocationalToken token1 = LocationalToken.setNewLocToken(Token.Type.ID, "a", 1);
        LocationalToken token2 = LocationalToken.setNewLocToken(Token.Type.NUMBER, "123", 2);
        boolean thrown1 = false;
        boolean thrown2 = false;
        try {
            identifier1 = Identifier.Builder.build(token1);
        }catch (ParserException e) {
            thrown1 = true;
        }
        try {
            identifier2 = Identifier.Builder.build(token2);
        }catch (ParserException e) {
            thrown2 = true;
        }
        assertEquals(identifier1.toString(), "Identifier{id='a'}");
        assertEquals(thrown1, false);
        assertEquals(thrown2, true);
    }
}