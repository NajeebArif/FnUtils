package narif.personal.work.fnutils.functions;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface Fn<T, U> extends Function<T, U> {

    /**
     * Remember: Function composition is a binary operation on functions, just as
     * addition is a binary operation on Numbers.
     * @see #compose(Function)
     */
    default <V> Fn<V, U> compose(Fn<V, T> before){
        Objects.requireNonNull(before);
        return (V v)-> apply(before.apply(v));
    }

    /**
     * Remember: Function composition is a binary operation on functions, just as
     * addition is a binary operation on Numbers.
     * @see #andThen(Function)
     */
    default <V> Fn<T, V> andThen(Fn<U, V> after){
        Objects.requireNonNull(after);
        return (T t)-> after.apply(apply(t));
    }

    static <T> Fn<T, T> identity(){
        return t->t;
    }

    /**
     * When we compose two functions which result in another function then we will
     * have to make sure that the return type of the function which is applied
     * first, is the input for the other function.
     * @param tuFnAfter a function from T -> U, applied after first
     * @param vtFnBefore a function from V -> T, is applied first
     * @param <T> Type T
     * @param <U> Type U
     * @param <V> Type V
     * @return a function from V -> U
     * check test cases for examples on how to use compose and higher order compose.
     */
    static <T, U, V> Fn<V, U> compose(Fn<T, U> tuFnAfter, Fn<V, T> vtFnBefore){
        return (V v)-> tuFnAfter.apply(vtFnBefore.apply(v));
    }

    static <T, U, V> Fn<T,V> andThen(Fn<T, U> tuFnBefore, Fn<U, V> uvFnAfter){
        return (T t)-> uvFnAfter.apply(tuFnBefore.apply(t));
    }

    static <T, U, V> Fn<Fn<T, U>, Fn<Fn<U, V>, Fn<T, V>>> compose(){
        return (Fn<T, U> tuFn) -> (Fn<U, V> uvFn) -> uvFn.compose(tuFn);
    }

    static <T, U, V> Fn<Fn<T, U>, Fn<Fn<V, T>, Fn<V, U>>> andThen(){
        return tuFn -> vtFn -> vtFn.andThen(tuFn);
    }

    /**
     * Higher order andThen function when one constant value is given.
     * @param <T> Parameter of Type T
     * @param <U> Parameter of Type U
     * @param <V> Parameter of Type V
     * @return a function of, a function, to, a function of, function to function
     */
    static <T, U, V> Fn<Fn<T, U>, Fn<Fn<U, V>, Fn<T, V>>> higherOrderAndThen(){
        return tuFn -> uvFn -> (T t) -> uvFn.apply(tuFn.apply(t));
    }

    static <T, U, V> Fn<Fn<U, V>, Fn<Fn<T, U>, Fn<T, V>>> higherOrderCompose(){
        return uvFn -> tuFn -> (T t) -> uvFn.apply(tuFn.apply(t));
    }

}

