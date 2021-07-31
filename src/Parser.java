import java.util.HashMap;
import java.util.Map;

public class Parser<T> {

	private Lexer lexer;
	private Token lookAhead;
	Map<String, Double> symbolTable = new HashMap<String, Double>();

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		this.lookAhead = lexer.nextToken();
	}

	public void match(Token token) throws Exception {
		if (lookAhead.type == token.type && lookAhead.value == token.value) {
			lookAhead = lexer.nextToken();
		} else {
			throw new Exception("Systax Error! Match");
		}
	}

	public void prog() throws Exception {
		stmt();
		match(lookAhead);
		lines();
	}

	public void lines() throws Exception {
		if (lookAhead.type != TokenType.EOF) {
			prog();
		}
	}

	public void stmt() throws Exception {
		if (lookAhead.type == TokenType.Var) {
			atr();
		} else if (lookAhead.type == TokenType.Print) {
			print();
		} else {
			if (lookAhead.type != TokenType.EOF) {
				throw new Exception("Syntax Error! Parser");
			}
		}
	}

	public void print() throws Exception {
		if (lookAhead.type == TokenType.Comp) {
			match(lookAhead);
		}
		if (lookAhead.type == TokenType.OpBracket) {
			match(lookAhead);
		}

		Double v = symbolTable.get(lookAhead.value);

		match(lookAhead);

		if (lookAhead.type == TokenType.ClBracket) {
			match(lookAhead);
		}

		System.out.println(v);
	}

	public void atr() throws Exception {
		String value = (String) lookAhead.value;
		match(new Token(TokenType.Var, value));
		match(new Token(TokenType.Comp, null));
		Double v = expr();
		symbolTable.put(value, v);
	}

	public double expr() throws Exception {

		double fact = fact();

		if (lookAhead.type == TokenType.Sum) {
			match(lookAhead);
			double expr1 = expr();
			return fact + expr1;
		} else if (lookAhead.type == TokenType.Sub) {
			match(lookAhead);
			double expr1 = expr();
			return fact - expr1;
		} else {
			return fact;
		}
	}

	public double fact() throws Exception {

		double term = term();

		if (lookAhead.type == TokenType.Mult) {
			match(lookAhead);
			double fact1 = fact();
			return term * fact1;
		} else if (lookAhead.type == TokenType.Div) {
			match(lookAhead);
			double fact1 = fact();
			return term / fact1;
		} else {
			return term;
		}

	}

	public double term() throws Exception {

		if (lookAhead.type == TokenType.OpBracket) {
			return expr();
		}

		if (lookAhead.type == TokenType.Num) {
			double v = (double) lookAhead.value;
			match(lookAhead);
			return v;
		}

		if (lookAhead.type == TokenType.Var) {
			String s = (String) lookAhead.value;
			match(lookAhead);
			return symbolTable.get(s);
		}

		return 99;

	}

	public Lexer getLexer() {
		return lexer;
	}

	public void setLexer(Lexer lexer) {
		this.lexer = lexer;
	}

	public Token getLookAhead() {
		return lookAhead;
	}

	public void setLookAhead(Token lookAhead) {
		this.lookAhead = lookAhead;
	}

	public Map<String, Double> getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(Map<String, Double> symbolTable) {
		this.symbolTable = symbolTable;
	}

}
