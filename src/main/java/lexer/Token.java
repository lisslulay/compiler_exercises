package lexer;

public class Token {
    TokenType _type;
    String _value;

    public Token(TokenType _type, String _value) {
        this._type = _type;
        this._value = _value;
    }

    public TokenType get_type() {
        return _type;
    }

    public String get_value() {
        return _value;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Value: %s", _type, _value);
    }

    public boolean isVariable() {
        return _type == TokenType.VARIABLE;
    }

    public boolean isScalar() {
        return _type == TokenType.INTEGER || _type == TokenType.FLOAT || _type == TokenType.STRING ||
                _type == TokenType.BOOLEAN;
    }
}
