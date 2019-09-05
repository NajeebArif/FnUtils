package narif.personal.work.fnutils.components;

import narif.personal.work.fnutils.datastructures.Tuple;

import java.util.function.Supplier;

public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {

    private Case(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier){
        super(booleanSupplier,resultSupplier);
    }

    public static <T> Case<T> mcase(Supplier<Boolean> condition, Supplier<Result<T>> value){
        return new Case<>(condition, value);
    }

    public static <T> DefaultCase<T> mcase(Supplier<Result<T>> value){
        return new DefaultCase<>(()->true, value);
    }

    @SafeVarargs
    public static <T> Result<T> match (DefaultCase<T> defaultCase, Case<T>... matchers){
        for (Case<T> aCase: matchers)
            if(aCase.getFirst().get()) return aCase.getSecond().get();
        return defaultCase.getSecond().get();
    }

    private static class DefaultCase<T> extends Case<T>{
        private DefaultCase(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
            super(booleanSupplier, resultSupplier);
        }
    }
}
