package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by root on 23.12.15.
 */
public class ReadCode {

    private static String line;
    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("txt.java"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void nextBlock() {
        int count = 0;
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
        if (line.indexOf("else") != -1) {
            nextLine();
        }
    }

    public static String nextLine() {
        String s = null;
        if (scanner.hasNextLine())
            s = scanner.nextLine();
        line = s;
        return s;
    }

}
