
public class Main {

	public static void main(String[] args) throws Exception {
		Lexer lex = new Lexer(
		        "$x = 1 + 3;" +
		        "$y = 2 * 10 + 1;" +
		        "$z = ($x + $y)" +
		        "$p = 15 / 3" +
		        "print($z);");
		        
		        Parser parse = new Parser(lex);
		        parse.prog();
		        

	}

}
