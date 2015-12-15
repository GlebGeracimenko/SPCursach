import javax.crypto.Mac;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bogdan on 16.12.2015.
 */
public class MapTermanal {

    private static Map<String, Integer> mapTerminalSymbol = new HashMap<String, Integer>();

    static {
        mapTerminalSymbol.put("-", Priority.MINUS);
        mapTerminalSymbol.put("+", Priority.PLUS);
        mapTerminalSymbol.put("/", Priority.DIVISION);
        mapTerminalSymbol.put("*", Priority.MULTIPLY);
        mapTerminalSymbol.put("while", Priority.WHILE);
        mapTerminalSymbol.put("for", Priority.FOR);
        mapTerminalSymbol.put("if", Priority.IF);
        mapTerminalSymbol.put("else", Priority.ELSE);
        mapTerminalSymbol.put("true", Priority.TRUE);
        mapTerminalSymbol.put("false", Priority.FALSE);
        mapTerminalSymbol.put("boolean", Priority.BOOLEAN);
        mapTerminalSymbol.put("Integer", Priority.INTEGER);
        mapTerminalSymbol.put("Double", Priority.DOUBLE);
        mapTerminalSymbol.put("int", Priority.INT);
        mapTerminalSymbol.put(">", Priority.MORE);
        mapTerminalSymbol.put("<", Priority.LESS);
        mapTerminalSymbol.put("<", Priority.LESS);
        mapTerminalSymbol.put("Math.sqrt", Priority.SQRT);
        mapTerminalSymbol.put("(", Priority.LEFT_PARENTHESIS);
        mapTerminalSymbol.put(")", Priority.RIGHT_PARENTHESIS);
        mapTerminalSymbol.put(";", Priority.SEMICOLON);

    }


}
