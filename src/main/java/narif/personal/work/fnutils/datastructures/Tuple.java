package narif.personal.work.fnutils.datastructures;

import java.util.Objects;

public class Tuple<T, U> {

    private final T first;
    private final U second;

    /**
     * Creates an object of tuple which can hold a pair of values of types T and U
     *
     * @param first type of first object
     * @param second type of second object
     * @throws NullPointerException if any of the parameter to the constructor is null.
     */
    public Tuple(T first, U second) {
        validateNonNullValues(first, second);
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(getFirst(), tuple.getFirst()) &&
                Objects.equals(getSecond(), tuple.getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond());
    }

    private void validateNonNullValues(T first, U second) {
        Objects.requireNonNull(first, "Parameter first can never be null while creating a tuple.");
        Objects.requireNonNull(second, "Parameter second can never be null while creating a tuple.");
    }
}
