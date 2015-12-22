import tree.Iterator;
import tree.Node;
import tree.TreeNode;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        String s = "int d = 10 + 130 - 4 * 8 * 2";
        TreeNode.getTree(s);
        Node node = TreeNode.root;
        Iterator iterator = TreeNode.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getValue());
        }
    }

}
