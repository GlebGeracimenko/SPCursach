import Tree.Node;
import Tree.TreeNode;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {

    public static void main(String[] args) {
        String s = "d = b - b - 4 * a * 2";
        TreeNode.getTree(s);
        Node node = TreeNode.tree;
        System.out.println();
    }

}
