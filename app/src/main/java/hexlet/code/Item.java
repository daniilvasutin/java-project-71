package hexlet.code;

public class Item<K, V> {
    public K key;
    public V value;
    public String status;

    public Item(K item, V o, String status) {
        this.key = item;
        this.value = o;
        this.status = status;
    }

    public K getKey() {
        return key;
    }
}
