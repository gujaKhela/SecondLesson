import java.lang.reflect.Field;

public class NullableWarningChecker {
    public static void check(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NullableWarning.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value == null) {
                        System.out.println("Variable [" + field.getName() + "] is null in [" + obj.getClass().getSimpleName() + "]!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
