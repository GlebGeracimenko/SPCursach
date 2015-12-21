package tree;

import mapPriorety.MapTermanal;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class TreeNode {

    public static Node tree = new Node();

    public static void getTree(String line) {
        buildTree1(line);
        mainParent(tree);
    }

    private static void buildTree(String line) {
        int index;
        if (line.indexOf("if") != -1) {
            tree = new Node(MapTermanal.mapSymbolTerminal.get("()"), "()", null, null, null);
            tree.setLeft(new Node(MapTermanal.mapSymbolTerminal.get("if"), "if", tree, null, null));
            line = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
            tree.setRight(new Node());
            Node node = tree;
            tree = tree.getRight();
            tree.setParent(node);
            getTree(line);
        } else if ((index = line.indexOf("==")) != -1) {
            tree = new Node(MapTermanal.mapSymbolTerminal.get("=="), "=="
                    , tree.getParent() != null ? tree.getParent() : null, null, null);
            if (tree.getParent() != null) {
                tree.getParent().setRight(tree);
            }
            String s = line.substring(0, index).trim();
            tree.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, tree, null, null));
            s = line.substring(index + 2, line.length()).trim();
            tree.setRight(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, tree, null, null));
        }
    }

    private static void buildTree1(String line) {
        int index;
        String isLine = "";
        Integer isEmpty = null;// MapTermanal.mapSymbolTerminal.get(isLine = line.substring(0, line.indexOf(" ")));
        if (isEmpty == null) {
            if ((index = line.indexOf("=")) != -1) {
                tree = new Node(MapTermanal.mapSymbolTerminal.get("="), "=", null, null, null);
                tree.setLeft(new Node(0, line.substring(0, index), tree, null, null));
                line = line.substring(index + 1, line.length()).trim();
                tree.setRight(new Node());
                Node node = tree;
                tree = tree.getRight();
                tree.setParent(node);
                TreeLineHelper.FIRST_LINE = line;
                buildTree1(line);
            }
            if (line.equals(TreeLineHelper.FIRST_LINE) && TreeLineHelper.CHECK) {
                return;
            }

            isLine = firstSymvol(line);
            if (isLine == null) {
                return;
            }
            TreeLineHelper.CHECK = true;

            tree = new Node(MapTermanal.mapSymbolTerminal.get(isLine), isLine, tree.getParent(), null, null);
            String left = tree.getParent().getLeft() == null ? "" : tree.getParent().getLeft().getValue();
            String right = tree.getParent().getRight() == null ? "" : tree.getParent().getRight().getValue();
            if (left == null || left.equals(line) || left.equals("")) {
                tree.getParent().setLeft(tree);
            } else if (right == null || right.equals(line) || right.equals("")) {
                tree.getParent().setRight(tree);
            }
            String s = line.substring(0, line.indexOf(isLine)).trim();
            tree.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, tree, null, null));
            Node node = tree;
            tree = tree.getLeft();
            tree.setParent(node);
            buildTree1(s);
            while (!tree.getValue().equals(isLine))
                tree = tree.getParent();
            s = line.substring(line.indexOf(isLine) + 1, line.length()).trim();
            tree.setRight(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, tree, null, null));
            node = tree;
            tree = tree.getRight();
            tree.setParent(node);
            buildTree1(s);
//            if (isLine.equals("*") || isLine.equals("/")) {
//                multPriorety(isLine, line);
//            }
        } else {
            tree = new Node(isEmpty, isLine,
                    tree.getParent() != null ? tree.getParent() : null, null, null);
            if (tree.getParent() != null) {
                tree.getParent().setRight(tree);
            }
            tree.setRight(new Node());
            Node node = tree;
            tree = tree.getRight();
            tree.setParent(node);
            buildTree1(line.substring(line.indexOf(" "), line.length()).trim());
        }
    }

//    private static void multPriorety(String value, String line) {
//        tree = new Node(MapTermanal.mapSymbolTerminal.get(value), value,
//                tree.getParent() != null ? tree.getParent() : null, null, null);
//        if (tree.getParent() != null) {
//            tree.getParent().setRight(tree);
//        }
//        int index = line.indexOf(value);
//        String s = line.substring(0, index).trim();
//        tree.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
//                ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, tree, null, null));
//        line = line.substring(index + 1, line.length()).trim();
//        String[] mas = line.split(" ");
//        if (mas.length <= 1) {
//            tree.setRight(new Node(MapTermanal.mapSymbolTerminal.get(line) == null
//                    ? 0 : MapTermanal.mapSymbolTerminal.get(line), line, tree, null, null));
//            return;
//        }
//        if (!mas[1].equals(value)) {
//            tree.setRight(new Node(MapTermanal.mapSymbolTerminal.get(mas[0]) == null
//                    ? 0 : MapTermanal.mapSymbolTerminal.get(mas[0]), mas[0], tree, null, null));
//            tree.getRight().setRight(new Node());
//            Node node = tree.getRight();
//            tree = tree.getRight().getRight();
//            tree.setParent(node);
//            StringBuilder builder = new StringBuilder();
//            for (int i = 1; i < mas.length; i++) {
//                builder.append(mas[i] + " ");
//            }
//            buildTree1(builder.toString());
//        } else {
//            tree.setRight(new Node());
//            Node node = tree;
//            tree = tree.getRight();
//            tree.setParent(node);
//            buildTree1(line);
//        }
//    }

    private static void mainParent(Node node) {
        if (node.getParent() != null) {
            mainParent(node.getParent());
        } else {
            tree = node;
            return;
        }
    }

    private static String firstSymvol(String line) {
        //ignore
        int index1 = line.indexOf("-");
        index1 = index1 == -1 ? Integer.MAX_VALUE : index1;
        int index2 = line.indexOf("+");
        index2 = index2 == -1 ? Integer.MAX_VALUE : index2;
        if (index1 < index2) {
            return  "-";
        } else if (index2 < index1) {
            return  "+";
        } else {
            index1 = line.indexOf("*");
            index1 = index1 == -1 ? Integer.MAX_VALUE : index1;
            index2 = line.indexOf("/");
            index2 = index2 == -1 ? Integer.MAX_VALUE : index2;
            if (index1 < index2) {
                return "*";
            } else if (index2 < index1) {
                return "/";
            }
        }
        return null;
    }

//    public static void showTree(Node node) {
//        if (node.getParent() == null) {
//            System.out.println("MainParent == " + node.getValue() + "  left == " + node.getLeft().getValue()
//                    + "  right == " + node.getRight().getValue());
//        } else {
//            System.out.println("parent == " + node.getValue() + "  left == " + node.getLeft().getValue()
//                    + "  right == " + node.getRight().getValue());
//        }
//        if (node.getLeft() != null)
//            showTree(node.getLeft());
//        else if (node.getRight() != null)
//            showTree(node.getRight());
//        else
//            showTree(node.getParent());
//    }

}
