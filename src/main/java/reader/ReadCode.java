package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by root on 23.12.15.
 */
public class ReadCode {

    public static boolean flag = true;
    public static int ifCount = 0;
    public static String line;
    private static String cycleString;
    private static Scanner scanner;
    private static Scanner cycleScanner;
    public static int lineNumber = 0;

    static {
        try {
            scanner = new Scanner(new File("txt.java"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void nextBlock() throws Exception {
        int count = ifCount;
        if (line.indexOf("while") != -1 && cycleString != null) {
            while (true) {
                if (line.indexOf("}") != -1) {
                    count--;
                    if (count == 0) {
                        break;
                    }
                }
                if (line.indexOf("{") != -1) {
                    count++;
                }
                nextLine();
            }
            System.out.println("Кінець циклу while");
            cycleString = null;
            flag = true;
        } else {
            if (line.indexOf("if") != -1) {
                if (scanner.hasNextLine()) {
                    lineNumber++;
                    line = scanner.nextLine();
                }
            }
            while (true) {
                if (line.indexOf("}") != -1) {
                    count--;
                    if (!flag && ifCount > 0 && count == ifCount - 1) {
                        flag = true;
                        break;
                    }
                }
                if (line.indexOf("{") != -1) {
                    count++;
                }
                if (count == 0) {
                    break;
                }
                if (scanner.hasNextLine()) {
                    lineNumber++;
                    line = scanner.nextLine();
                }
            }
        }
    }

    public static String nextLine() throws Exception {
        if (cycleString != null) {
            return cycle();
        }
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
            lineNumber++;
        } else {
            line = "END";
            return line;
        }
        if (line.indexOf("while") != -1) {
            cycle();
        }
        if (line.indexOf("}") != -1 && ifCount > 0) {
            nextBlock();
        }
        return line;
    }

    private static String cycle() throws Exception {
        if (cycleString == null) {
            System.out.println("Цикл while:");
            cycleString = line;
            if (line.indexOf("true") != -1) {
                throw new Exception("Цикл нескінчений, інша частина коду не буде виконана (" + lineNumber + ", " + line.indexOf("true") + ").");
            } else if (line.indexOf("false") != -1) {
                throw new Exception("Цикл не є корисним (" + lineNumber + ", " + line.indexOf("false") + ").");
            }
            cycleScanner = scanner;
            int count = 1;
            while (cycleScanner.hasNextLine()) {
                lineNumber++;
                String s = "\n" + cycleScanner.nextLine();
                cycleString += s;
                if (s.indexOf("}") != -1) {
                    count--;
                    if (count == 0) {
                        break;
                    }
                }
                if (s.indexOf("{") != -1) {
                    count++;
                }
            }
            cycleScanner = null;
        } else {
            if (cycleScanner == null) {
                cycleScanner = new Scanner(cycleString);
            }
            String s = "";
            if (cycleScanner.hasNextLine()) {
                s = cycleScanner.nextLine();
            } else {
                cycleScanner = null;
            }
            if (s.equals(line)) {
                cycle();
                return line;
            }
            line = s;
            return line;
        }
        return null;
    }

    private static void checkCycle() {
        Scanner scanner = new Scanner(cycleString);
        String s = scanner.nextLine();
        s = s.substring(s.indexOf("while") + 5, s.length());
        s.replaceAll("\\(", "");
        s.replaceAll("\\)", "");
    }

}
