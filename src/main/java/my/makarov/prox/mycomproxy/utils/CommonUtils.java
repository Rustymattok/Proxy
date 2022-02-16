package my.makarov.prox.mycomproxy.utils;

import java.util.function.Supplier;

public class CommonUtils {

    public static <T> T nullsafe(Supplier<T> getFunction) {
        return nullsafe(getFunction, null);
    }

    public static <T> T nullsafe(Supplier<T> getFunction, T defaultValue) {
        try {
            return getFunction.get();
        } catch (NullPointerException ex) {
            return defaultValue;
        }
    }
}
