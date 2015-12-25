import reader.ReadCode;
import tree.Iterator;
import tree.TreeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        String s;
        while ((s = ReadCode.nextLine().trim()) != null) {
            if (s.indexOf("else") == -1 && (s.startsWith("import") || s.startsWith("public") || s.startsWith("}") || s.equals(""))) {
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
//        Scanner scanner = new Scanner(new File("txt.java"));
//        while (scanner.hasNextLine()) {
//            System.out.println(scanner.nextLine());
//        }
    }
}
