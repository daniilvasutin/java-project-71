package hexlet.code;

public class Item {
    public Object oldValue;
    public Object newValue;
    public String status;

    public Item(Object o, String status) {
        this.newValue = o;
        this.oldValue = null;
        this.status = status;
    }

    public Item(Object oldValue, Object newValue, String status) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }
}

