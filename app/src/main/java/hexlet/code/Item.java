package hexlet.code;

public final class Item {
    private Object oldValue;
    private Object newValue;
    private Status status;

    public Item(Object newValue, Status newStatus) {
        this.newValue = newValue;
        this.status = newStatus;
    }

    public Item(Object oldValue, Object newValue, Status newStatus) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = newStatus;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object o) {
        this.oldValue = o;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object o) {
        this.newValue = o;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }
}



