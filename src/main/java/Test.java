import reader.ReadCode;
import tree.Iterator;
import tree.TreeNode;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        String s;
        while ((s = ReadCode.nextLine().trim()) != null) {
            if (s.indexOf("else") == -1 && (s.startsWith("}") || s.equals(""))) {
                continue;
            } else if (s.startsWith("END")) {
                break;
            }

            TreeNode tree = new TreeNode();
            tree.getTree(s.substring(0, s.length() - 1).trim());
            Iterator iterator = tree.iterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
        }
    }
}
