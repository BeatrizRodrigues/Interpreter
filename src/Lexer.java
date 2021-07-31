
public class Lexer {

	final private String space = "\n\t";
	public int position;
    public String input;
    
    public Lexer(String input){
        this.input = input;
        position = 0;
    }
    
    public boolean hasInput(){
        return input.isEmpty() && position < input.length();
    }
    
    private Character NextChar(){
        if(position == input.length()){
            return Character.MIN_VALUE;
        }
        return input.charAt(position++);
    }
    
    public Token nextToken(){
        Character peek;
        do{
            peek = NextChar();
        } while(space.contains(peek.toString()));
        
        if(Character.isDigit(peek)){
            String v = "";
            do{
                v += peek;
                peek = NextChar();
            }while(Character.isDigit(peek));
            
            if(peek != Character.MIN_VALUE){
                position--; 
            }
            return new Token(TokenType.Num, Float.valueOf(v));
        }
        
        if (peek == '$') {
            String v = "";
            do {
                v += peek;
                peek = NextChar();
            } while (!space.contains(peek.toString())&&peek!='+'&&peek!='-'
                    &&peek!='*'&&peek!='/'&&peek!='('&&peek!=')'&&peek!='='
                    &&peek!=';');
            if (peek != Character.MIN_VALUE) {
                position--;
            }
            return new Token(TokenType.Var, v);
        }
        
        if (peek == 'p') {
            String v = "";
            do {
                v += peek;
                peek = NextChar();
            } while (Character.isLetter(peek));
            if (peek != Character.MIN_VALUE) {
                position--;
            }

            if(v.contains("print")){
                return new Token(TokenType.Print, v);
            }else{
                return new Token(TokenType.Invalid, null);
            }
            
        }
        
        if (peek == '+') {
            return new Token(TokenType.Sum, null);
        } else if (peek == '-') {
            return new Token(TokenType.Sub, null);
        } else if (peek == Character.MIN_VALUE) {
            return new Token(TokenType.EOF, null);
        } else if (peek == '*')
            return new Token(TokenType.Mult, null);
        else if (peek == '/')
            return new Token(TokenType.Div, null);
        else if (peek == ';')
            return new Token(TokenType.EOL, null);
        else if (peek == '(')
            return new Token(TokenType.OpBracket, null);
        else if (peek == ')')
            return new Token(TokenType.ClBracket, null);
        else if (peek == '=')
            return new Token(TokenType.Comp, null);
        else {
            return new Token(TokenType.Invalid, null);
        }
    }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getSpace() {
		return space;
	}
	
}
