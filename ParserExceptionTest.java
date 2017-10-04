import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Stanley Tian on 9/19/2017.
 */
class ParserExceptionTest {
    @Test
    void verify() {
        boolean thrown1 = false;
        Optional<LocationalToken> token1 = Optional.empty();
        try {
            ParserException.verify(token1);
        }catch (ParserException e) {
            thrown1 = true;
        }
        assertEquals(thrown1, true);

        boolean thrown2 = false;
        Optional<LocationalToken> token2 = Optional.of(LocationalToken.setNewLocToken(Token.Type.AND, null, 1));
        try {
            ParserException.verify(token2);
        }catch (ParserException e) {
            thrown2 = true;
        }
        assertEquals(thrown2, false);
    }

    @Test
    void verifyEnd() {
        boolean thrown1 = false;
        Optional<LocationalToken> token1 = Optional.empty();
        try {
            ParserException.verifyEnd(token1);
        }catch (ParserException e) {
            thrown1 = true;
        }
        assertEquals(thrown1, false);

        boolean thrown2 = false;
        Optional<LocationalToken> token2 = Optional.of(LocationalToken.setNewLocToken(Token.Type.AND, null, 1));
        try {
            ParserException.verifyEnd(token2);
        }catch (ParserException e) {
            thrown2 = true;
        }
        assertEquals(thrown2, true);
    }

}