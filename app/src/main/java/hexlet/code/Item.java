package hexlet.code;

public class Item<K, V> {
    public K key;
    public V currentValue;
    public V oldValue;
    public String status;

    public Item(K item, V o, String status) {
        this.key = item;
        this.currentValue = o;
        this.status = status;
    }

    public Item(K item, V newValue, V oldValue, String status) {
        this.key = item;
        this.currentValue = newValue;
        this.oldValue = oldValue;
        this.status = status;
    }


    public K getKey() {
        return key;
    }
}
