package hexlet.code;

public class Item {
    private Object oldValue;
    private Object newValue;
    private String status;

    public Item(Object o, String status) {
        this.setNewValue(o);
        this.setOldValue(null);
        this.setStatus(status);
    }

    public Item(Object oldValue, Object newValue, String status) {
        this.setOldValue(oldValue);
        this.setNewValue(newValue);
        this.setStatus(status);
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

