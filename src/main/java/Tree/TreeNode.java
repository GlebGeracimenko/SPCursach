package Tree;

import MapPriorety.MapTermanal;

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
        String isLine;
        Integer isEmpty = MapTermanal.mapSymbolTerminal.get(isLine = line.substring(0, line.indexOf(" ")));
        if (isEmpty == null) {
            if ((index = line.indexOf("=")) != -1) {
                tree = new Node(MapTermanal.mapSymbolTerminal.get("="), "=", null, null, null);
                tree.setLeft(new Node(0, line.substring(0, index), tree, null, null));
                line = line.substring(index + 1, line.length()).trim();
                tree.setRight(new Node());
                Node node = tree;
                tree = tree.getRight();
                tree.setParent(node);
                buildTree1(line);
            } else if ((index = line.indexOf("*")) != -1) {
                tree = new Node(MapTermanal.mapSymbolTerminal.get("*"), "*",
                        tree.getParent() != null ? tree.getParent() : null, null, null);
                if (tree.getParent() != null) {
                    tree.getParent().setRight(tree);
                }
                String s = line.substring(0, index).trim();
                tree.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                        ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, tree, null, null));
                line = line.substring(index + 1, line.length()).trim();
                String[] mas = line.split(" ");
                if (mas.length <= 1) {
                    tree.setRight(new Node(MapTermanal.mapSymbolTerminal.get(line) == null
                            ? 0 : MapTermanal.mapSymbolTerminal.get(line), line, tree, null, null));
                    return;
                }
                if (!mas[1].equals("*")) {
                    tree.setRight(new Node(MapTermanal.mapSymbolTerminal.get(mas[0]) == null
                            ? 0 : MapTermanal.mapSymbolTerminal.get(mas[0]), mas[0], tree, null, null));
                    tree.getRight().setRight(new Node());
                    Node node = tree.getRight();
                    tree = tree.getRight().getRight();
                    tree.setParent(node);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < mas.length; i++) {
                        builder.append(mas[i] + " ");
                    }
                    buildTree1(builder.toString());
                } else {
                    tree.setRight(new Node());
                    Node node = tree;
                    tree = tree.getRight();
                    tree.setParent(node);
                    buildTree1(line);
                }

            }
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

    private static void mainParent(Node node) {
        if (node.getParent() != null) {
            mainParent(node.getParent());
        } else {
            tree = node;
            return;
        }
    }

    public static void showTree(Node node) {
        if (node.getParent() == null) {
            System.out.println("MainParent == " + node.getValue() + "  left == " + node.getLeft().getValue()
                    + "  right == " + node.getRight().getValue());
        } else {
            System.out.println("parent == " + node.getValue() + "  left == " + node.getLeft().getValue()
                    + "  right == " + node.getRight().getValue());
        }
        if (node.getLeft() != null)
            showTree(node.getLeft());
        else if (node.getRight() != null)
            showTree(node.getRight());
        else
            showTree(node.getParent());
    }

    public static String refactor(String line) {

        String  newName = line.replaceAll(" ", "");
        String temp = "";
        boolean prinak = false;

        char [] mas = newName.toCharArray();

        for (int i = 0; i < mas.length; i++) {
            if(String.valueOf(mas[i]).equals("(")) {
                prinak = true;

                if (String.valueOf(mas[i]).equals(")")) {
                    prinak = false;
                }
            } else {
                if(prinak == true){
                    temp += String.valueOf(mas[i]);
                }
            }
        }

        return temp;
    }

}
