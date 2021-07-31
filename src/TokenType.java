import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TokenType {

	Num(0),
    Sum(1),
    Sub(2),
    Mult(3),
    Div(4),
    
    Var(5),
    
    Comp(6),
    OpBracket(7),
    ClBracket(8),
    
    Print(9),
    EOL(10),
    EOF(99),
    Invalid(-1);
    
    private static final Map<Integer,TokenType> terminalSymbol2TokenType = new HashMap<Integer, TokenType>();

    static{
        for(TokenType t : EnumSet.allOf(TokenType.class)){
            terminalSymbol2TokenType.put(t.terminalSymbol(), t);
        }
    }

    private final int terminalSymbol;
    
    private TokenType(Integer terminalSymbol) {
        this.terminalSymbol = terminalSymbol;
    }

    public int terminalSymbol(){
        return this.terminalSymbol;
    }

    public static TokenType byTerminalSymbol(Integer i) {
        return terminalSymbol2TokenType.get(i);
    }

	public static Map<Integer, TokenType> getTerminalsymbol2tokentype() {
		return terminalSymbol2TokenType;
	}

	public int getTerminalSymbol() {
		return terminalSymbol;
	}
    
    
}
