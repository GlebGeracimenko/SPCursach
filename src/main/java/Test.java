import tree.Iterator;
import tree.Node;
import tree.TreeNode;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {


    public static void main(String[] args) {
        String s = "d = b + o - 4 * a - 2";
        char c = '\u0030';
        System.out.println(c);
        c = '9';
        System.out.println(c);
        TreeNode.getTree(s);
        Node node = TreeNode.root;
        Iterator iterator = TreeNode.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getValue());
        }
        System.out.println();
    }

}
