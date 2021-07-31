
public class Token<T>{
	public TokenType type;
	public T value;

	public boolean hasValue() {
		return (value != null);
	}

	public Token(TokenType type, T value) {
		this.type = type;
		this.value = value;
	}
	
}
