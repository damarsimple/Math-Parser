import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        boolean repl = true;
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        while (repl) {
            System.out.printf("Enter a mathematical expression: ");
            String text = scanner.nextLine();

            Lexer lexer = new Lexer(text);

            List<Token> tokens = lexer.generateTokens();

            System.out.println("Menu: ");

            System.out.println("1. Parse");
            System.out.println("2. Print Token");
            System.out.println("3. Print Operation Stack");
            System.out.println("4. Quit");

            System.out.printf("Enter your choice: ");
            int choice = getInt(scanner);

            Parser parser = new Parser(tokens);
            float result = parser.parse();
            switch (choice) {
                case 1:
                    System.out.println("Result: " +result);
                    break;
                case 2:
                    for (Token token : tokens) {
                        System.out.println(token.toString());
                    }
                    break;
                case 3:

                    for (Operation operation : parser.getStack()) {
                        System.out.println(operation.toString());
                    }
                    System.out.println("Stack Size : " + parser.getStack().size());
                    System.out.println("Result : " + result);
                    break;
                case 4:
                    repl = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

     

        }
        scanner.close();
    }

    public static int getInt(Scanner scanner) {
        int option = 0;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return option;
    }


}
