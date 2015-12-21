package Tree;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Node {

    private int priorety;
    private String value;
    private Node parent;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(int priorety, String value, Node parent, Node left, Node right) {
        this.priorety = priorety;
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public int getPriorety() {
        return priorety;
    }

    public void setPriorety(int priorety) {
        this.priorety = priorety;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
