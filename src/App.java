import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String text = "1 + 2 * 3 - 4 / 5";

        Lexer lexer = new Lexer(text);

        List<Token> tokens = lexer.generateTokens();

        for (Token token : tokens) {
            System.out.printf("v: %s type : %s  \n", token.value, token.type);
        }
    }

}
