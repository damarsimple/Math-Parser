
import java.util.ArrayList;
import java.util.List;

class Lexer {
    public final String DIGITS = "0123456789";
    public final String WHITESPACE = "\n\t\r ";

    public char current;

    public String text;

    public int pos = 0;

    Lexer(String text) {
        this.text = text;
        advance();
    }

    void advance() {
        try {
            current = text.charAt(pos);
            pos++;
        } catch (IndexOutOfBoundsException e) {
            current = '\0';
        }
    }

    List<Token> generateTokens() {

        List<Token> tokens = new ArrayList<>();

        while (current != '\0') {

            if (current == ' ' || current == '\n' || current == '\t') {
                advance();
            }
            if (current == '+') {
                tokens.add(new Token(TokenType.PLUS, "+"));
                advance();
            } else if (current == '-') {
                tokens.add(new Token(TokenType.MINUS, "-"));
                advance();
            } else if (current == '*') {
                tokens.add(new Token(TokenType.MULTPIPLY, "*"));
                advance();
            } else if (current == '/') {
                tokens.add(new Token(TokenType.DIVIDE, "/"));
                advance();
            } else if (current == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                advance();
            } else if (current == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
                advance();
            } else if (Character.isDigit(current)) {
                StringBuilder sb = new StringBuilder(); // "123"
                while (Character.isDigit(current) || current == '.') {
                    sb.append(current);
                    advance();
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
            } else {
                throw new RuntimeException("Invalid character: " + current);
            }
        }

        return tokens;

    }
}
