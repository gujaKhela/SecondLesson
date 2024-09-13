import java.util.LinkedList;

public class CustomHashSet<T> {
    private LinkedList<T>[] buckets;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public CustomHashSet() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
    }

    // Put
    public void put(T element) {
        if (contains(element)) {
            return;
        }

        if ((double) size / buckets.length >= LOAD_FACTOR) {
            resize();
        }

        int index = getBucketIndex(element);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        buckets[index].add(element);
        size++;
    }

    public boolean contains(T element) {
        int index = getBucketIndex(element);
        LinkedList<T> bucket = buckets[index];
        if (bucket == null) return false;
        return bucket.contains(element);
    }

    public void delete(T element) {
        int index = getBucketIndex(element);
        LinkedList<T> bucket = buckets[index];
        if (bucket != null && bucket.remove(element)) {
            size--;
        }
    }

    public void iterate() {
        for (LinkedList<T> bucket : buckets) {
            if (bucket != null) {
                for (T element : bucket) {
                    System.out.println(element);
                }
            }
        }
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(T element) {
        return Math.abs(element.hashCode() % buckets.length);
    }

    private void resize() {
        LinkedList<T>[] oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];
        size = 0;

        for (LinkedList<T> bucket : oldBuckets) {
            if (bucket != null) {
                for (T element : bucket) {
                    put(element);
                }
            }
        }
    }

}
