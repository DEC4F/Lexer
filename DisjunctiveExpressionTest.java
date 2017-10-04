import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Created by Stanley Tian on 9/27/2017.
 */
class DisjunctiveExpressionTest {
    @Test
    void build() {
        // test normal case
        DisjunctiveExpression expression1 = null;
        DisjunctiveLexer lexer1 = new DisjunctiveLexer("not (a and b) and c");
        boolean thrown1 = false;
        try {
            expression1 = DisjunctiveExpression.Builder.build(lexer1.nextValid().get(), lexer1);
        } catch (ParserException e) {
            thrown1 = true;
        }

        assertEquals(expression1.toString(), "DisjunctiveExpression{factor=CompoundFactor{leftExpression=DisjunctiveExpression{factor=Identifier{id='a'}, positive=true}, rightExpression=DisjunctiveExpression{factor=Identifier{id='b'}, positive=true}}, positive=false}");
        assertEquals(thrown1, false);

        // test throwing exception
        DisjunctiveExpression expression2 = null;
        LocationalToken token2 = LocationalToken.setNewLocToken(Token.Type.OPEN, null, 2);
        DisjunctiveLexer lexer2 = new DisjunctiveLexer("(()))((())");
        boolean thrown2 = false;
        try {
            expression2 = DisjunctiveExpression.Builder.build(token2, lexer2);
        } catch (ParserException e) {
            thrown2 = true;
        }

        assertEquals(thrown2, true);
    }

    @Test
    void negate() {
        // test normal case
        DisjunctiveExpression expression1 = null;
        LocationalToken token1 = LocationalToken.setNewLocToken(Token.Type.OPEN, null, 1);
        DisjunctiveLexer lexer1 = new DisjunctiveLexer("a and b) and c");
        boolean thrown1 = false;
        try {
            expression1 = DisjunctiveExpression.Builder.build(token1, lexer1);
        } catch (ParserException e) {
            thrown1 = true;
        }

        DisjunctiveExpression expression2 = expression1.negate();
        assertEquals(expression2.toString(), "DisjunctiveExpression{factor=CompoundFactor{leftExpression=DisjunctiveExpression{factor=Identifier{id='a'}, positive=true}, rightExpression=DisjunctiveExpression{factor=Identifier{id='b'}, positive=true}}, positive=false}");
    }
}