package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by root on 23.12.15.
 */
public class ReadCode {

    private static String line;
    private static String cycleString;
    private static Scanner scanner;
    private static Scanner cycleScanner;

    static {
        try {
            scanner = new Scanner(new File("txt.java"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void nextBlock() {
        int count = 0;
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
            cycleString = null;
        } else {
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
//            if (line.indexOf("else") != -1) {
//                nextLine();
//            }
        }
    }

    public static String nextLine() {
        if (cycleString != null) {
            return cycle();
        }
        if (scanner.hasNextLine())
            line = scanner.nextLine();
        if (line.indexOf("while") != -1) {
            cycle();
        }
        return line;
    }

    private static String cycle() {
        if (cycleString == null) {
            cycleString = line;
            cycleScanner = scanner;
            int count = 1;
            while (cycleScanner.hasNextLine()) {
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

}
