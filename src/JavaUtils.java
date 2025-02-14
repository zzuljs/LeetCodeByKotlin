import kotlin.jvm.functions.Function0;

public class JavaUtils {

    private void useFunction() {
        Object obj = new Function0<Integer>() {
            @Override
            public Integer invoke() {
                return null;
            }
        };
    }
}
