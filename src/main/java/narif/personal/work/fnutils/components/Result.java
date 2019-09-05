package narif.personal.work.fnutils.components;

import java.util.Objects;
import java.util.function.Supplier;

public interface Result<T> {

    void bind(Effect<T> success, Effect<String> failure);

    static <T> Result<T> failure(String message){
        return new Failure<>(message);
    }

    static <T> Result<T> failure(Supplier<String> stringSupplier){
        return new Failure<>(stringSupplier);
    }

    static <T> Result<T> success(T value){
        return new Success<>(value);
    }

    class Success<T> implements Result<T> {
        private final T value;

        private Success(T value){
            this.value = value;
        }

        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            success.accept(value);
        }
    }

    class Failure<T> implements Result<T> {

        private final String errorMessage;

        private Failure(String errorMessage) {
            Objects.requireNonNull(errorMessage, "Error message should never be null.");
            this.errorMessage = errorMessage;
        }

        private Failure(Supplier<String> errorMessage) {
            Objects.requireNonNull(errorMessage.get(), "Error message should never be null.");
            this.errorMessage = errorMessage.get();
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            failure.accept(errorMessage);
        }
    }
}
