import java.util.List;

public class Parser {

    List<Token> tokens;

    int pos = 0;

    Token current = null;

    
    Parser(List<Token> tokens) {
        this.tokens = tokens;
        advance();
    }

    void advance() {
        try {
            current = tokens.get(pos);
            pos++;
        } catch (IndexOutOfBoundsException e) {
            current = null;
        }

    }

    public float parse() {
        float result = expr();
        

        if (current != null) {
            throw new RuntimeException("Expected end of input but got " + current);
        }
        return result;
    }
    
    public float expr() {

        float result = term();

        while (current != null && (current.type == TokenType.PLUS || current.type == TokenType.MINUS)) {
            TokenType op = current.type;

            advance();
            float right = term();
            if (op == TokenType.PLUS) {
                result += right;
            } else {
                result -= right;
            }

        }

        return result;

    }
    
    public float term() {

        float result = factor();

        while (current != null && (current.type == TokenType.MULTPIPLY || current.type == TokenType.DIVIDE)) {
            TokenType op = current.type;
            advance();
            float right = factor();
            if (op == TokenType.MULTPIPLY) {
                result *= right;
            } else {
                result /= right;
            }
        }

        return result;

    }
    
    public float factor() {
        
        if (current == null) {
            throw new RuntimeException("Unexpected end of input");
        }
        
        if (current.type == TokenType.NUMBER) {
            float result = Float.parseFloat(current.value);
            advance();
            return result;
        }
        
        if (current.type == TokenType.LPAREN) {
            advance();
            float result = expr();
            if (current == null || current.type != TokenType.RPAREN) {
                throw new RuntimeException("Expected ')'");
            }
            advance();
            return result;
        }
        
        throw new RuntimeException("Unexpected token: " + current);
        
    }
    
}
