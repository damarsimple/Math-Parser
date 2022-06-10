import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String text = "1 + 2 * 3 - 4 / 5 + (5 * 5) + 7";

        Lexer lexer = new Lexer(text);

        List<Token> tokens = lexer.generateTokens();

        for (Token token : tokens) {
            System.out.printf("%s ", token.type);
        }

        System.out.println();

        Parser parser = new Parser(tokens);

        float result = parser.parse();

        System.out.println("result: " + result);
    }

}
