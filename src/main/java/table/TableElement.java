package table;

/**
 * Created by bogdan on 21.12.2015.
 */
public class TableElement {

    private int priorety;
    private String name;
    private Object value;

    public TableElement(int priorety, String name, Object value) {
        this.priorety = priorety;
        this.name = name;
        this.value = value;
    }

    public int getPriorety() {
        return priorety;
    }

    public void setPriorety(int priorety) {
        this.priorety = priorety;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
