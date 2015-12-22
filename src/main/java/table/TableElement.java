package table;

/**
 * Created by bogdan on 21.12.2015.
 */
public class TableElement {

    private Class aClass;
    private String name;
    private Object value;

    public TableElement(Class aClass, String name, Object value) {
        this.aClass = aClass;
        this.name = name;
        this.value = value;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
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
