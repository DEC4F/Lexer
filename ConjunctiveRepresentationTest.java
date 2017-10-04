import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjunctiveRepresentationTest {
    @Test
    void getRepresentation() {
        String rep = "not ( not a or not b )";
        ConjunctiveRepresentation conjRep = new ConjunctiveRepresentation(rep, true);
        assertEquals(conjRep.getRepresentation(), "not ( not a or not b )");
    }

    @Test
    void isNegated() {
        String rep = "( not a or not b )";
        ConjunctiveRepresentation conjRep = new ConjunctiveRepresentation(rep, false);
        assertEquals(conjRep.isNegated(), false);
    }

}