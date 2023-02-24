package hexlet.code;

public final class Item {
    private Object oldValue;
    private Object newValue;
    private Status status;

    public Item(Object o, Status newStatus) {
        this.setNewValue(o);
        this.setOldValue(null);
        this.setStatus(newStatus);
    }

    public Item(Object o1, Object o2, Status newStatus) {
        this.setOldValue(o1);
        this.setNewValue(o2);
        this.setStatus(newStatus);
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



