import tree.Iterator;
import tree.Node;
import tree.TreeNode;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {


    public static void main(String[] args) {
        String s = "d = b + o - 4 * a * 2";
        TreeNode.getTree(s);
        Node node = TreeNode.root;
        Node k = TreeNode.getLengthTree();
        System.out.println(k.getValue());
    }

}
