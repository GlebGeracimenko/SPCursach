package tree;

import mapPriorety.MapTermanal;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class TreeNode {

    public static Node root = new Node();

    public static void getTree(String line) {
        buildTree1(line);
        mainParent(root);
    }

    private static void buildTree(String line) {
        int index;
        if (line.indexOf("if") != -1) {
            root = new Node(MapTermanal.mapSymbolTerminal.get("()"), "()", null, null, null);
            root.setLeft(new Node(MapTermanal.mapSymbolTerminal.get("if"), "if", root, null, null));
            line = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
            root.setRight(new Node());
            Node node = root;
            root = root.getRight();
            root.setParent(node);
            getTree(line);
        } else if ((index = line.indexOf("==")) != -1) {
            root = new Node(MapTermanal.mapSymbolTerminal.get("=="), "=="
                    , root.getParent() != null ? root.getParent() : null, null, null);
            if (root.getParent() != null) {
                root.getParent().setRight(root);
            }
            String s = line.substring(0, index).trim();
            root.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, root, null, null));
            s = line.substring(index + 2, line.length()).trim();
            root.setRight(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, root, null, null));
        }
    }

    private static void buildTree1(String line) {
        int index;
        String isLine = "";
        Integer isEmpty = null;// MapTermanal.mapSymbolTerminal.get(isLine = line.substring(0, line.indexOf(" ")));
        if (isEmpty == null) {
            if ((index = line.indexOf("=")) != -1) {
                root = new Node(MapTermanal.mapSymbolTerminal.get("="), "=", null, null, null);
                root.setLeft(new Node(0, line.substring(0, index), root, null, null));
                line = line.substring(index + 1, line.length()).trim();
                root.setRight(new Node());
                Node node = root;
                root = root.getRight();
                root.setParent(node);
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

            root = new Node(MapTermanal.mapSymbolTerminal.get(isLine), isLine, root.getParent(), null, null);
            String left = root.getParent().getLeft() == null ? "" : root.getParent().getLeft().getValue();
            String right = root.getParent().getRight() == null ? "" : root.getParent().getRight().getValue();
            if (left == null || left.equals(line) || left.equals("")) {
                root.getParent().setLeft(root);
            } else if (right == null || right.equals(line) || right.equals("")) {
                root.getParent().setRight(root);
            }
            String s = line.substring(0, line.indexOf(isLine)).trim();
            root.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, root, null, null));
            Node node = root;
            root = root.getLeft();
            root.setParent(node);
            buildTree1(s);
            while (!root.getValue().equals(isLine))
                root = root.getParent();
            s = line.substring(line.indexOf(isLine) + 1, line.length()).trim();
            root.setRight(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, root, null, null));
            node = root;
            root = root.getRight();
            root.setParent(node);
            buildTree1(s);
//            if (isLine.equals("*") || isLine.equals("/")) {
//                multPriorety(isLine, line);
//            }
        } else {
            root = new Node(isEmpty, isLine,
                    root.getParent() != null ? root.getParent() : null, null, null);
            if (root.getParent() != null) {
                root.getParent().setRight(root);
            }
            root.setRight(new Node());
            Node node = root;
            root = root.getRight();
            root.setParent(node);
            buildTree1(line.substring(line.indexOf(" "), line.length()).trim());
        }
    }

//    private static void multPriorety(String value, String line) {
//        root = new Node(MapTermanal.mapSymbolTerminal.get(value), value,
//                root.getParent() != null ? root.getParent() : null, null, null);
//        if (root.getParent() != null) {
//            root.getParent().setRight(root);
//        }
//        int index = line.indexOf(value);
//        String s = line.substring(0, index).trim();
//        root.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
//                ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, root, null, null));
//        line = line.substring(index + 1, line.length()).trim();
//        String[] mas = line.split(" ");
//        if (mas.length <= 1) {
//            root.setRight(new Node(MapTermanal.mapSymbolTerminal.get(line) == null
//                    ? 0 : MapTermanal.mapSymbolTerminal.get(line), line, root, null, null));
//            return;
//        }
//        if (!mas[1].equals(value)) {
//            root.setRight(new Node(MapTermanal.mapSymbolTerminal.get(mas[0]) == null
//                    ? 0 : MapTermanal.mapSymbolTerminal.get(mas[0]), mas[0], root, null, null));
//            root.getRight().setRight(new Node());
//            Node node = root.getRight();
//            root = root.getRight().getRight();
//            root.setParent(node);
//            StringBuilder builder = new StringBuilder();
//            for (int i = 1; i < mas.length; i++) {
//                builder.append(mas[i] + " ");
//            }
//            buildTree1(builder.toString());
//        } else {
//            root.setRight(new Node());
//            Node node = root;
//            root = root.getRight();
//            root.setParent(node);
//            buildTree1(line);
//        }
//    }

    private static void mainParent(Node node) {
        if (node.getParent() != null) {
            mainParent(node.getParent());
        } else {
            root = node;
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
            return "-";
        } else if (index2 < index1) {
            return "+";
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

    public static Node getLengthTree() {
        Iterator iterator = TreeNode.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        int length = iterator.getLength();
        iterator = iterator();
        iterator.setMaxLength(length);
        return iterator.next1();
    }

    public static Iterator iterator() {
        return new Iter();
    }

    private static class Iter implements Iterator {
        Node node = root;
        Node previous;
        int treeLength = 0;
        int maxLength = 0;

        public boolean hasNext() {
            return node != null;
        }

        public Node next() {
            if (previous != null && previous == node.getLeft()) {
                previous = node;
                node = node.getRight() != null ? node.getRight() : node.getParent();
                return previous;
            }
            if (node.getRight() != null && previous != node.getRight()) {
                node = node.getRight();
                return next();
            } else {
                if (previous == null) {
                    previous = node;
                    node = node.getParent();
                    return next();
                }
                if (previous == node.getParent()) {
                    previous = node;
                    Node node1 = null;
                    if (previous.getLeft() == null && previous.getRight() == null) {
                        node1 = previous;
                        while (node.getParent() != null && node.getParent().getRight() == node1) {
                            node = node.getParent();
                            node1 = node1.getParent();
                        }
                    }
                    node = node.getParent();
                    if (node != null && (node.getLeft() != previous || node.getRight() != previous)) {
                        Node previous1 = previous;
                        previous = node1;
                        return previous1;
                    }
                    return previous;
                }
                while (node.getLeft() != null && previous != node.getLeft())
                    node = node.getLeft();
                previous = node;
                node = node.getParent();
                return previous;
            }
        }

//        if (node.getRight() != null && previous != node.getRight()) {
//            node = node.getRight();
//            treeLength++;
//            if (treeLength > maxLength)
//                maxLength = treeLength;
//            return next();
//        } else {
//            if (node.getLeft() != null) {
//                treeLength++;
//                if (treeLength > maxLength)
//                    maxLength = treeLength;
//                previous = node;
//                node = node.getLeft();
//                return previous;
//            } else {
//                previous = node;
//                Node node1 = null;
//                if (previous.getLeft() == null && previous.getRight() == null) {
//                    node1 = previous;
//                    while (node.getParent() != null && node.getParent().getLeft() == node1) {
//                        treeLength--;
//                        node = node.getParent();
//                        node1 = node1.getParent();
//                    }
//                }
//                treeLength--;
//                node = node.getParent();
//                if (node != null && (node.getLeft() != previous || node.getRight() != previous)) {
//                    Node previous1 = previous;
//                    previous = node1;
//                    return previous1;
//                }
//                return previous;
//            }
//        }

        @Override
        public Node next1() {
            if (node.getRight() != null && previous != node.getRight()) {
                node = node.getRight();
                maxLength--;
                if (maxLength == 0)
                    return node;
                else
                    return next1();
            } else {
                if (node.getLeft() != null) {
                    maxLength--;
                    previous = node;
                    node = node.getLeft();
                    if (maxLength == 0)
                        return node;
                    else
                        return next1();
                } else {
                    previous = node;
                    Node node1 = null;
                    if (previous.getLeft() == null && previous.getRight() == null) {
                        node1 = previous;
                        while (node.getParent() != null && node.getParent().getLeft() == node1) {
                            maxLength++;
                            node = node.getParent();
                            node1 = node1.getParent();
                        }
                    }
                    maxLength++;
                    node = node.getParent();
                    if (node != null && (node.getLeft() != previous || node.getRight() != previous)) {
                        Node previous1 = previous;
                        previous = node1;
                        if (maxLength == 0)
                            return node;
                        else
                            return next1();
                    }
                    if (maxLength == 0)
                        return node;
                    else
                        return next1();
                }

            }
            //return new Node();
        }

        @Override
        public int getLength() {
            return maxLength;
        }

        @Override
        public void setMaxLength(int maxLength) {
            this.maxLength = maxLength;
        }

        //        if (node.getRight() != null && previous != node.getRight()) {
//            node = node.getRight();
//            return next();
//        } else {
//            if (node.getLeft() != null) {
//                previous = node;
//                node = node.getLeft();
//                return previous;
//            } else {
//                previous = node;
//                Node node1 = null;
//                if (previous.getLeft() == null && previous.getRight() == null) {
//                    node1 = previous;
//                    while (node.getParent() != null && node.getParent().getLeft() == node1) {
//                        node = node.getParent();
//                        node1 = node1.getParent();
//                    }
//                }
//                node = node.getParent();
//                if (node != null && (node.getLeft() != previous || node.getRight() != previous)) {
//                    Node previous1 = previous;
//                    previous = node1;
//                    return previous1;
//                }
//                return previous;
//            }
//
//        }

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
