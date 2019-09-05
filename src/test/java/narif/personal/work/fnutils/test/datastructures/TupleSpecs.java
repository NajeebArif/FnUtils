package narif.personal.work.fnutils.test.datastructures;

import narif.personal.work.fnutils.datastructures.Tuple;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tuple Specs:")
class TupleSpecs {

    private Tuple<String, Integer> stringIntegerTuple;

    @BeforeEach
    private void init(){
        stringIntegerTuple = new Tuple<>("One",1);
    }

    @Test
    @DisplayName("The first argument in the tuple constructor gets assigned to the first value.")
    void testFirstValue(){
        assertThat(stringIntegerTuple.getFirst()).isNotEmpty().isEqualTo("One");
    }

    @Test
    @DisplayName("The second argument in the tuple constructor gets assigned to the second value.")
    void testSecondValue(){
        assertThat(stringIntegerTuple.getSecond()).isGreaterThan(0).isEqualTo(1);
    }

    @Test
    @DisplayName("A tuple creation should throw a null pointer exception if any parameter is null.")
    void testTupleCreationWithNullValue(){
        assertThatNullPointerException().isThrownBy(()->new Tuple<>("1", null));
        assertThatNullPointerException().isThrownBy(()->new Tuple<>(null, 1));
        assertThatNullPointerException().isThrownBy(()->new Tuple<>(null, null));

        assertThat(stringIntegerTuple).isNotNull();
    }
}
