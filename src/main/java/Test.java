import Tree.Node;
import Tree.TreeNode;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {

    public static void main(String[] args) {
        String s = "dad = b - asdb - 4234 * a * 2;";
        new SplitString().tokenazer(s);
        TreeNode.getTree(s);
        Node node = TreeNode.tree;
        System.out.println();
    }

}
