import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Stanley Tian on 9/10/2017.
 */
class LocationalTokenTest {
    @Test
    void setNewLocToken() {

        LocationalToken locationalToken0 = LocationalToken.setNewLocToken(Token.Type.OR, null, 0000);
        assertEquals(locationalToken0.getTokenType(), Token.Type.OR);
        assertEquals(locationalToken0.getTokenData(), Optional.empty());
        assertEquals(locationalToken0.getLocation(), 0000);

        LocationalToken locationalToken1 = LocationalToken.setNewLocToken(Token.Type.AND, null, 1111);
        assertEquals(locationalToken1.getTokenType(), Token.Type.AND);
        assertEquals(locationalToken1.getTokenData(), Optional.empty());
        assertEquals(locationalToken1.getLocation(), 1111);

        LocationalToken locationalToken2 = LocationalToken.setNewLocToken(Token.Type.ID, "higherbrothers", 2222);
        assertEquals(locationalToken2.getTokenType(), Token.Type.ID);
        assertEquals(locationalToken2.getTokenData().toString(),"Optional[higherbrothers]");
        assertEquals(locationalToken2.getLocation(), 2222);

        LocationalToken locationalToken3 = LocationalToken.setNewLocToken(Token.Type.NUMBER, "123456", 3333);
        assertEquals(locationalToken3.getTokenType(), Token.Type.NUMBER);
        assertEquals(locationalToken3.getTokenData().toString(), "Optional[123456]");
        assertEquals(locationalToken3.getLocation(), 3333);

        LocationalToken locationalToken4 = LocationalToken.setNewLocToken(Token.Type.BINARYOP, "-", 4444);
        assertEquals(locationalToken4.getTokenType(), Token.Type.BINARYOP);
        assertEquals(locationalToken4.getTokenData().toString(), "Optional[-]");
        assertEquals(locationalToken4.getLocation(), 4444);

        LocationalToken locationalToken5 = LocationalToken.setNewLocToken(Token.Type.WHITESPACE, null, 5555);
        assertEquals(locationalToken5.getTokenType(), Token.Type.WHITESPACE);
        assertEquals(locationalToken5.getTokenData(), Optional.empty());
        assertEquals(locationalToken5.getLocation(), 5555);

    }

}