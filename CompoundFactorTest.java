import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompoundFactorTest {
    @Test
    void build() {
        // test normal case
        CompoundFactor compoundFactor1 = null;
        String input1 = "(a and b)";
        DisjunctiveLexer lexer1 = new DisjunctiveLexer(input1);
        boolean thrown1 = false;
        try {
            compoundFactor1 = CompoundFactor.Builder.build(lexer1.nextValid().get(), lexer1);
        }catch (ParserException e) {
            thrown1 = true;
        }

        assertEquals(compoundFactor1.toString(), "CompoundFactor{leftExpression=DisjunctiveExpression{factor=Identifier{id='a'}, positive=true}, rightExpression=DisjunctiveExpression{factor=Identifier{id='b'}, positive=true}}");
        assertEquals(thrown1, false);

        // test throwing
        CompoundFactor compoundFactor2 = null;
        String input2 = "not a and b)";
        DisjunctiveLexer lexer2 = new DisjunctiveLexer(input2);
        boolean thrown2 = false;
        try {
            compoundFactor2 = CompoundFactor.Builder.build(lexer2.nextValid().get(), lexer2);
        }catch (ParserException e) {
            thrown2 = true;
        }
        assertEquals(thrown2, true);

        // test throwing
        CompoundFactor compoundFactor3 = null;
        String input3 = "( and b)";
        DisjunctiveLexer lexer3 = new DisjunctiveLexer(input3);
        boolean thrown3 = false;
        try {
            compoundFactor3 = CompoundFactor.Builder.build(lexer3.nextValid().get(), lexer3);
        }catch (ParserException e) {
            thrown3 = true;
        }
        assertEquals(thrown3, true);
    }

    @Test
    void conjunctiveRepresentation() {
        // test normal case
        // testing DisjunctiveExpression.conjunctiveRepresentation() and Identifier.conjunctiveRepresentation() at the same time
        CompoundFactor compoundFactor1;
        String input1 = "(a and b)";
        DisjunctiveLexer lexer1 = new DisjunctiveLexer(input1);
        boolean thrown1 = false;
        String output1 = null;
        try {
            compoundFactor1 = CompoundFactor.Builder.build(lexer1.nextValid().get(), lexer1);
            output1 = compoundFactor1.conjunctiveRepresentation().getRepresentation();
        } catch (ParserException e) {
            thrown1 = true;
        }

        assertEquals(output1, "not ( not a or not b )");

        // test very complex case
        // testing DisjunctiveExpression.conjunctiveRepresentation() and Identifier.conjunctiveRepresentation() at the same time
        CompoundFactor compoundFactor2;
        String input2 = "(a and (b and (c and (d and e))))";
        DisjunctiveLexer lexer2 = new DisjunctiveLexer(input2);
        boolean thrown2 = false;
        String output2 = null;
        try {
            compoundFactor2 = CompoundFactor.Builder.build(lexer2.nextValid().get(), lexer2);
            output2 = compoundFactor2.conjunctiveRepresentation().getRepresentation();
        } catch (ParserException e) {
            thrown2 = true;
        }
        assertEquals(output2, "not ( not a or not ( not b or not ( not c or not ( not d or not e ))))");
    }

}