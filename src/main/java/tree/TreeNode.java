package tree;

import mapPriorety.MapTermanal;
import reader.ReadCode;
import table.TableElement;
import table.TableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gleb Geracimenko on 16.12.2015.
 */
public class TreeNode {

    private Node root;

    public void getTree(String line) {
        buildTree(line);
        if (root != null)
            mainParent(root);
    }

    private void buildTree(String line) {
        if (line.indexOf("System.out.println") != -1) {
            line += ";";
            String s1 = "System.out.println(";
            line = line.substring(line.indexOf(s1) + s1.length(), line.lastIndexOf(");"));
            int index = TableList.list.indexOf(line);
            TableElement element = null;
            if (index > -1)
                element = TableList.list.get(index);
            System.out.println("================>>>>>>>>>>" + (element != null ? element : line));
            return;
        }
        int index;
        int isLine = -1;
        if (line.indexOf("if") != -1) {
            ReadCode.ifCount++;
            root = new Node(MapTermanal.mapSymbolTerminal.get("()"), "()", null, null, null);
            root.setLeft(new Node(MapTermanal.mapSymbolTerminal.get("if"), "if", root, null, null));
            line = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")")).trim();
            root.setRight(new Node());
            Node node = root;
            root = root.getRight();
            root.setParent(node);
            buildTree(line);
        } else if (line.indexOf("while") != -1) {
            root = new Node(MapTermanal.mapSymbolTerminal.get("()"), "()", null, null, null);
            root.setLeft(new Node(MapTermanal.mapSymbolTerminal.get("while"), "while", root, null, null));
            line = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")")).trim();
            root.setRight(new Node());
            Node node = root;
            root = root.getRight();
            root.setParent(node);
            buildTree(line);
        } else if ((index = line.indexOf("==")) != -1) {
            builtIfTree(line.trim(), "==", index);
        } else if ((index = line.indexOf("!=")) != -1) {
            builtIfTree(line.trim(), "!=", index);
        } else if ((index = line.indexOf(">=")) != -1) {
            builtIfTree(line.trim(), ">=", index);
        } else if ((index = line.indexOf(">")) != -1) {
            builtIfTree(line.trim(), ">", index);
        } else if ((index = line.indexOf("<=")) != -1) {
            builtIfTree(line.trim(), "<=", index);
        } else if ((index = line.indexOf("<")) != -1) {
            builtIfTree(line.trim(), "<", index);
        } else if ((index = line.indexOf("=")) != -1) {
            root = new Node(MapTermanal.mapSymbolTerminal.get("="), "=", null, null, null);
            root.setLeft(new Node(0, line.substring(0, index), root, null, null));
            line = line.substring(index + 1, line.length()).trim();
            if (line.startsWith("-")) {
                line = line.replaceAll("\\-\\(", "(-1) * (");
            }
            root.setRight(new Node(MapTermanal.mapSymbolTerminal.get(line) == null
                    ? 0 : MapTermanal.mapSymbolTerminal.get(line), line, root, null, null));
            Node node = root;
            root = root.getRight();
            root.setParent(node);
            TreeLineHelper.FIRST_LINE = line;
            TreeLineHelper.CHECK = false;
            buildTree(line);
        }
        if (line.equals(TreeLineHelper.FIRST_LINE) && TreeLineHelper.CHECK) {
            return;
        }

        isLine = firstSymvol(line);
        if (isLine == -1) {
            return;
        }
        TreeLineHelper.CHECK = true;
        String fff = String.valueOf(line.charAt(isLine));
        root = new Node(MapTermanal.mapSymbolTerminal.get(fff), fff, root.getParent(), null, null);
        String left = root.getParent().getLeft() == null ? "" : root.getParent().getLeft().getValue();
        String right = root.getParent().getRight() == null ? "" : root.getParent().getRight().getValue();
        if (left == null || left.equals(line) || left.equals("")) {
            root.getParent().setLeft(root);
        } else if (right == null || right.equals(line) || right.equals("")) {
            root.getParent().setRight(root);
        }
        String s = refactorBrackets(line.substring(0, isLine)).trim();
        root.setLeft(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, root, null, null));
        Node node = root;
        root = root.getLeft();
        root.setParent(node);
        buildTree(s);
        root = root.getParent();
        while (root.getRight() != null)
            root = root.getParent();
        s = refactorBrackets(line.substring(isLine + 1, line.length())).trim();
        root.setRight(new Node(MapTermanal.mapSymbolTerminal.get(s) == null
                ? 0 : MapTermanal.mapSymbolTerminal.get(s), s, root, null, null));
        node = root;
        root = root.getRight();
        root.setParent(node);
        buildTree(s);
    }

    private void builtIfTree(String line, String value, int index) {
        root = new Node(MapTermanal.mapSymbolTerminal.get(value), value
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

    private void mainParent(Node node) {
        if (node.getParent() != null) {
            mainParent(node.getParent());
        } else {
            root = node;
            return;
        }
    }

    public int firstSymvol(String line) {
        List<Integer> list = refactor(line);
        if (list.size() > 0) {
            for (int i : list) {
                if (i == 0) {
                    continue;
                }
                int index1 = line.indexOf("-", i);
                index1 = index1 == -1 ? Integer.MAX_VALUE : index1;
                if (index1 != Integer.MAX_VALUE && line.charAt(index1 - 1) == '(')
                    index1 = Integer.MAX_VALUE;
                int index2 = line.indexOf("+", i);
                index2 = index2 == -1 ? Integer.MAX_VALUE : index2;
                if (index1 < index2) {
                    return index1;
                } else if (index2 < index1) {
                    return index2;
                } else {
                    index1 = line.indexOf("*", i);
                    index1 = index1 == -1 ? Integer.MAX_VALUE : index1;
                    index2 = line.indexOf("/", i);
                    index2 = index2 == -1 ? Integer.MAX_VALUE : index2;
                    if (index1 < index2) {
                        return index1;
                    } else if (index2 < index1) {
                        return index2;
                    }
                }
            }
        } else {
            int index1 = line.indexOf("-");
            index1 = index1 == -1 ? Integer.MAX_VALUE : index1;
            if (index1 != Integer.MAX_VALUE && index1 == 0)
                index1 = Integer.MAX_VALUE;
            int index2 = line.indexOf("+");
            index2 = index2 == -1 ? Integer.MAX_VALUE : index2;
            if (index1 < index2) {
                return index1;
            } else if (index2 < index1) {
                return index2;
            } else {
                index1 = line.indexOf("*");
                index1 = index1 == -1 ? Integer.MAX_VALUE : index1;
                index2 = line.indexOf("/");
                index2 = index2 == -1 ? Integer.MAX_VALUE : index2;
                if (index1 < index2) {
                    return index1;
                } else if (index2 < index1) {
                    return index2;
                }
            }
        }
        return -1;
    }

    private String rez(String line) throws ClassNotFoundException {
        if (line.indexOf("=") != -1 || line.indexOf(">") != -1 || line.indexOf("<") != -1) {
            toTable(line);
        } else {
            int index;
            if (line.indexOf("Math.sqrt") != -1) {
                int w;
                String value;
                while ((w = line.indexOf("Math.sqrt(") + 10) >= 10) {
                    value = line.substring(w, line.indexOf(")", w));
                    int sqrt;
                    if ((index = TableList.list.indexOf(value)) != -1) {
                        sqrt = (int)Math.sqrt(Double.parseDouble(Integer.toString((Integer) TableList.list.get(index).getValue())));
                    } else {
                        sqrt = (int)Math.sqrt(Double.parseDouble(value));
                    }
                    String s = line.substring(0, w - 15);
                    String s1 = line.substring(line.indexOf("sqrt(" + value) + 6 + value.length(), line.length());
                    line = s + sqrt + s1;
                }
            }
            String mas[] = line.split(" ");
            int k = 0;
            int h = 0;
            if (mas[0].startsWith("-")) {
                mas[0] = mas[0].trim().substring(1, mas[0].length());
                index = TableList.list.indexOf(mas[0]);
                k = -(index != -1 ? (int) TableList.list.get(index).getValue()
                        : Integer.parseInt(mas[0]));
            } else {
                index = TableList.list.indexOf(mas[0]);
                k = (index != -1 ? (int) TableList.list.get(index).getValue()
                        : Integer.parseInt(mas[0]));
            }
            if (mas[2].startsWith("-")) {
                mas[2] = mas[2].trim().substring(1, mas[2].length());
                index = TableList.list.indexOf(mas[2]);
                h = -(index != -1 ? (int) TableList.list.get(index).getValue()
                        : Integer.parseInt(mas[2]));
            } else {
                index = TableList.list.indexOf(mas[2]);
                h = (index != -1 ? (int) TableList.list.get(index).getValue()
                        : Integer.parseInt(mas[2]));
            }
            if (mas[1].equals("*")) {
                line = new String(String.valueOf(k * h));
            } else if (mas[1].equals("/")) {
                line = new String(String.valueOf(k / h));
            } else if (mas[1].equals("+")) {
                line = new String(String.valueOf(k + h));
            } else if (mas[1].equals("-")) {
                line = new String(String.valueOf(k - h));
            } else {
                line = null;
            }
            return line;
        }
        return null;
    }

    private void toTable(String s) throws ClassNotFoundException {
        String mas[] = s.split(" ");
        if (mas[1].equals("==") || mas[1].equals(">=") || mas[1].equals("<=") || mas[1].equals("!=") ||
                mas[1].equals(">") || mas[1].equals("<")) {
            int index;
            int k = (index = TableList.list.indexOf(mas[0])) != -1 ? (int) TableList.list.get(index).getValue()
                                                                   : Integer.parseInt(mas[0]);
            int h = (index = TableList.list.indexOf(mas[2])) != -1 ? (int) TableList.list.get(index).getValue()
                                                                   : Integer.parseInt(mas[2]);
            boolean b = k != h;
            if (mas[1].equals("==")) {
                if (k != h)
                    ReadCode.nextBlock();
            } else if (mas[1].equals(">=")) {
                if (k < h)
                    ReadCode.nextBlock();
            } else if (mas[1].equals("<=")) {
                if (k > h)
                    ReadCode.nextBlock();
            } else if (mas[1].equals("!=")) {
                if (k == h)
                    ReadCode.nextBlock();
            } else if (mas[1].equals(">")) {
                if (k <= h)
                    ReadCode.nextBlock();
            } else if (mas[1].equals("<")) {
                if (k >= h)
                    ReadCode.nextBlock();
            }
        } else {
            if (mas[2].equals("=")) {
                TableList.list.add(new TableElement(MapTermanal.mapSymbolTerminal.get(mas[0]) != null
                        ? MapTermanal.mapSymbolTerminal.get(mas[0]) : 0, mas[1], Integer.parseInt(mas[3])));
            } else if (mas[1].equals("=")) {
                int index = TableList.list.indexOf(mas[0]);
                TableElement element = TableList.list.get(index);
                element.setValue(Integer.parseInt(mas[2]));
                TableList.list.set(index, element);
            }
        }
        return;
    }

    public Iterator iterator() {
        return new Iter();
    }

    private class Iter implements Iterator {
        Node node = root;
        Node previous;

        String rez = "";



        public boolean hasNext() {
            return node != null;
        }

        public Node next() {
            if (node.getLeft() == previous && previous != null) {
                previous = node;
                node = node.getRight() == null ? null : node.getRight();
                if (node.getRight() == null && node.getLeft() == null) {
                    rez += previous.getValue().trim() + " ";
                    return previous;
                } else {
                    node = previous.getParent();
                }
                rez += previous.getValue().trim() + " ";
                return previous;
            }
            if (node.getRight() != null && previous != node.getRight()) {
                node = node.getRight();
                return next();
            }
            if (previous == null) {
                previous = node;
                node = node.getParent();
                return next();
            }
            if (node.getRight() == previous) {
                while (node.getLeft() != null) {
                    node = node.getLeft();
                    if (node.getRight() != null && node.getRight().getValue().equals("*")) {
                        while (node.getRight() != null)
                            node = node.getRight();
                        previous = null;
                        return next();
                    }
                }
                previous = node;
                node = node.getParent();
                rez += previous.getValue().trim() + " ";
                return previous;
            }
            if (previous.getRight() == node && previous != null) {
                Node node1 = node;
                node = previous.getParent();
                rez += node1.getValue() + " ";
                Node node2 = previous;
//////////////////
                try {
                    previous = new Node(0, rez = rez(rez.trim()) + " ", node, null, null);
                    if (rez.equals("null ")) {
                        node = null;
                        return node;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (node.getLeft() == node2) {
                    node.setLeft(previous);
                } else if (node.getRight() == node2) {
                    rez = "";
                    node.setRight(previous);
                }
                return node1;
            }
            return null;
        }


    }

    public List<Integer> refactor(String line) {
        if (!line.startsWith("(") && !line.endsWith(")")) {
            return new ArrayList<>();
        }
        String value;
        if (line.indexOf("Math.sqrt") != -1) {
            int w;
            while ((w = line.indexOf("Math.sqrt(") + 10) >= 10) {
                value = line.substring(w, line.indexOf(")", w));
                value = "(int)Math.sqrt(" + value + ")";
                String s = line.substring(0, line.indexOf(value));
                String s1 = line.substring(line.indexOf(value) + value.length(), line.length());
                String s2 = "";
                for (int i = 0; i < value.length(); i++) {
                    s2 += " ";
                }
                line = s + s2 + s1;
            }
        }
        char[] mas = line.toCharArray();
        List<Integer> indexs = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] == '(') {
                count++;
                if (count == 1)
                    indexs.add(i);
            } else if (mas[i] == ')') {
                count--;
                if (count == 0) {
                    indexs.add(i + 1);
                }
            }
        }
        return indexs;
    }

    public String refactorBrackets(String line) {
        line = line.trim();
        if (line.startsWith("(int)Math.sqrt")) {
            return line;
        }
        int index1, index2;
        if ((index1 = line.indexOf("(")) != -1 && (index2 = line.lastIndexOf(")")) != -1)
            line = line.substring(index1 + 1, index2);
        return line;
    }
}
