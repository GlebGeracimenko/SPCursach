import reader.ReadCode;
import table.TableElement;
import table.TableList;
import tree.Iterator;
import tree.Node;
import tree.TreeNode;

import java.io.IOException;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {

//    String line = "x = -(b / 2 * a)";
//    String s = line.replaceAll("\\-\\(", "(-1) * (");

    public static void main(String[] args) {
        String s;
        while ((s = ReadCode.nextLine().trim()) != null) {
            if (s.indexOf("else") == -1 && (s.startsWith("import") || s.startsWith("public") || s.startsWith("}") || s.equals(""))) {
                continue;
            }

            TreeNode tree = new TreeNode();
            tree.getTree(s.substring(0, s.length() - 1).trim());
            Iterator iterator = tree.iterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
            System.out.println(TableList.list.size() + " => " + s);
        }

//        String s = "int x = (-1 + (int)Math.sqrt(9)) / 2 * 8";//"int d = 10 + 130 - 4 * 8 - 2";
//        TreeNode tree = new TreeNode();
//        tree.getTree(s);
//        Iterator iterator = tree.iterator();
//        while (iterator.hasNext()) {
//            iterator.next();
//        }
    }
}
