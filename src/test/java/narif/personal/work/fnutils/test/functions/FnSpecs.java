package narif.personal.work.fnutils.test.functions;

import narif.personal.work.fnutils.functions.Fn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Function specification:")
class FnSpecs {

    private Fn<Integer, Long> integerLongFn;
    private Fn<Long, String> longStringFn;

    @BeforeEach
    void init(){
        integerLongFn = Integer::longValue;
        longStringFn = String::valueOf;
    }

    @Test
    @DisplayName("Higher order compose function")
    void testHigherOrderCompose(){
        String val = Fn.<Integer,Long,String>higherOrderCompose()
                .apply(longStringFn).apply(integerLongFn).apply(1);
        assertThat(val).isNotNull().isNotEmpty().isEqualTo("1");
    }

    @Test
    @DisplayName("Higher order And Then function")
    void testFnHigherOrderCompose(){
        String val = Fn.<Integer, Long, String>higherOrderAndThen()
                .apply(integerLongFn).apply(longStringFn).apply(1);
        assertThat(val).isNotNull().isNotEmpty().isEqualTo("1");
    }

    @Test
    @DisplayName("Compose method taking two Fn arguments")
    void testFnCompose(){
        String val = Fn.compose(longStringFn,integerLongFn).apply(1);
        assertThat(val).isNotNull().isNotEmpty().isEqualTo("1");
    }

    @Test
    @DisplayName("And Then method taking two fn arguments")
    void testAndThen(){
        String val = Fn.andThen(integerLongFn,longStringFn).apply(1);
        assertThat(val).isNotNull().isNotEmpty().isEqualTo("1");
    }

}
