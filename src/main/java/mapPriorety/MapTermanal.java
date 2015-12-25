package mapPriorety;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bogdan on 16.12.2015.
 */
public class MapTermanal {

    public static Map<String, Integer> mapSymbolTerminal = new HashMap<String, Integer>();
    public static Map<Integer, String> mapTerminalSymbol = new HashMap<Integer, String>();

    static {
        mapSymbolTerminal.put(">", Priority.MORE);
        mapSymbolTerminal.put(">=", Priority.MORE_EQUALS);
        mapSymbolTerminal.put("<", Priority.LESS);
        mapSymbolTerminal.put("<=", Priority.LESS_EQUALS);
        mapSymbolTerminal.put("=", Priority.EQUALS);
        mapSymbolTerminal.put("!=", Priority.NOT_ASSIGNMENT);
        mapSymbolTerminal.put("==", Priority.ASSIGNMENT);
        mapSymbolTerminal.put("/", Priority.DIVISION);
        mapSymbolTerminal.put("*", Priority.MULTIPLY);
        mapSymbolTerminal.put("-", Priority.MINUS);
        mapSymbolTerminal.put("+", Priority.PLUS);
        mapSymbolTerminal.put("while", Priority.WHILE);
        mapSymbolTerminal.put("for", Priority.FOR);
        mapSymbolTerminal.put("if", Priority.IF);
        mapSymbolTerminal.put("else", Priority.ELSE);
        mapSymbolTerminal.put("true", Priority.TRUE);
        mapSymbolTerminal.put("false", Priority.FALSE);
        mapSymbolTerminal.put("boolean", Priority.BOOLEAN);
        mapSymbolTerminal.put("Integer", Priority.INTEGER);
        mapSymbolTerminal.put("double", Priority.DOUBLE);
        mapSymbolTerminal.put("int", Priority.INT);
        mapSymbolTerminal.put("Math.sqrt", Priority.SQRT);
        mapSymbolTerminal.put("()", Priority.PARENTHESIS);
/////////////////////////////////////////////////////////////////////
        mapTerminalSymbol.put(Priority.MORE, ">");
        mapTerminalSymbol.put(Priority.LESS, "<");
        mapTerminalSymbol.put(Priority.EQUALS, "=");
        mapTerminalSymbol.put(Priority.DIVISION, "/");
        mapTerminalSymbol.put(Priority.MULTIPLY, "*");
        mapTerminalSymbol.put(Priority.MINUS, "-");
        mapTerminalSymbol.put(Priority.PLUS, "+");
        mapTerminalSymbol.put(Priority.WHILE, "while");
        mapTerminalSymbol.put(Priority.FOR, "for");
        mapTerminalSymbol.put(Priority.IF, "if");
        mapTerminalSymbol.put(Priority.ELSE, "else");
        mapTerminalSymbol.put(Priority.TRUE, "true");
        mapTerminalSymbol.put(Priority.FALSE, "false");
        mapTerminalSymbol.put(Priority.BOOLEAN, "boolean");
        mapTerminalSymbol.put(Priority.INTEGER, "Integer");
        mapTerminalSymbol.put(Priority.DOUBLE, "Double");
        mapTerminalSymbol.put(Priority.INT, "int");
        mapTerminalSymbol.put(Priority.SQRT, "Math.sqrt");
    }
}
