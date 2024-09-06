import java.lang.reflect.Field;

public abstract class Identifiable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null && !id.isBlank() && id.length() <= 4) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID should not be empty and must be 4 digits or fewer.");
        }
    }

    public void print() {
        System.out.println(this.toString());
    }

    protected void checkNullableWarnings() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NullableWarning.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(this);
                    if (value == null) {
                        System.out.println("Variable [" + field.getName() + "] is null in [" + this.getClass().getSimpleName() + "]!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
