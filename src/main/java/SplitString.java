import java.util.ArrayList;

/**
 * Created by bogdan on 21.12.2015.
 */
public class SplitString {

    static String tempString;

    public SplitString(String tempString) {
        this.tempString = tempString;
    }

    static char[] masChar = tempString.toCharArray();

    static ArrayList<Character> listSymbol = new ArrayList<Character>();

    static {
        for (int i = 0; i < masChar.length; i++) {

            if(masChar[i] != ' ') {
                listSymbol.add(masChar[i]);
            }
        }

    }


}
