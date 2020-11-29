package bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class calcultateurTest {

    Calculateur calculateur = new Calculateur();

    @Test
    void testAdd() {
        assertEquals( calculateur.add( 2, 3 ), 5 );
        assertThat( calculateur.add( 2, 4 ) ).isEqualTo( 6 );
    }

}
