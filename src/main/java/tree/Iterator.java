package tree;

/**
 * Created by Gleb Geracimenko on 21.12.2015.
 */
public interface Iterator {
    boolean hasNext();
    Node next();
    Node next1();
    int getLength();
    void setMaxLength(int maxLength);
}
