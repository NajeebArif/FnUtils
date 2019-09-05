package narif.personal.work.fnutils.components;

import java.util.function.Consumer;

@FunctionalInterface
public interface Effect<T> extends Consumer<T> {

}
