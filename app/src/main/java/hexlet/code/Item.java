package hexlet.code;

public final class Item {
    private Object oldValue;
    private Object newValue;
    private Status status;

    public Item(Object newValue1, Status newStatus) {
        this.newValue = newValue1;
        this.status = newStatus;
    }

    public Item(Object oldValue1, Object newValue1, Status newStatus) {
        this.oldValue = oldValue1;
        this.newValue = newValue1;
        this.status = newStatus;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Status getStatus() {
        return status;
    }
}



