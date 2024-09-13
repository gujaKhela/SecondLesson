import java.util.Arrays;

public class CustomArrayList<Table> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    // Add
    public void put(Table element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    // Get
    @SuppressWarnings("unchecked")
    public Table get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return (Table) elements[index];
    }

    // Delete
    public void delete(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
    }

    // Get the current size
    public int size() {
        return size;
    }

    // Resize
    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }
}
