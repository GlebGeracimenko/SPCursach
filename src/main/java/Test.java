import tree.Iterator;
import Tree.TreeNode;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {


    public static void main(String[] args) throws IOException {
        String s = "d = 10 + 130 - 4 * 8 * 2";
        new IORefactor().scanFile("txt.pas");
        Node node = TreeNode.root;
        Iterator iterator = TreeNode.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getValue());
        }


//        TreeNode.getTree("d = (e + 34 + 1) * 2 * sv;");
//        Node node = TreeNode.tree;
//        System.out.prin     }
    }
}
