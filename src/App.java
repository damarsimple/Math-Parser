import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        boolean repl = true; // digunakan untuk melacak apakah user masih ingin melanjutkan program
        Scanner scanner = new Scanner(System.in); // Untuk membaca input dari user

        while (repl) {
            System.out.printf("Enter a mathematical expression: "); // Menunjukkan prompt untuk input
            String text = scanner.nextLine(); // Membaca input dari user

            Lexer lexer = new Lexer(text); // Membuat objek Lexer

            List<Token> tokens = lexer.generateTokens(); // Membuat list dari token yang diterima dari Lexer

            System.out.println("Menu: "); // Menunjukkan menu

            System.out.println("1. Parse");
            System.out.println("2. Print Token");
            System.out.println("3. Print Operation Stack");
            System.out.println("4. Quit");

            System.out.printf("Enter your choice: "); // Menunjukkan prompt untuk input
            int choice = getInt(scanner); // Membaca input dari user

            Parser parser = new Parser(tokens); // Membuat objek Parser
            float result = parser.parse(); // Menjalankan fungsi parse dari Parser
            switch (choice) {
                case 1:
                    System.out.println("Result: " + result); // Menampilkan hasil dari parse
                    break;
                case 2:
                    for (Token token : tokens) {
                        System.out.println(token.toString()); // Menampilkan token yang diterima dari Lexer
                    }
                    break;
                case 3:

                    for (Operation operation : parser.getStack()) {
                        System.out.println(operation.toString()); // Menampilkan stack yang diterima dari Parser
                    }
                    System.out.println("Stack Size : " + parser.getStack().size()); // Menampilkan ukuran stack yang
                                                                                    // diterima dari Parser
                    System.out.println("Result : " + result); // Menampilkan hasil dari parse
                    break;
                case 4:
                    repl = false; // Mengakhiri program
                    break;
                default:
                    System.out.println("Invalid choice"); // Menampilkan pesan jika input tidak sesuai
                    break;
            }

        }
        scanner.close(); // Menutup scanner
    }

    public static int getInt(Scanner scanner) {
        int option = 0; // Variabel untuk menyimpan input dari user
        try {
            option = Integer.parseInt(scanner.nextLine()); // Menyimpan input dari user
        } catch (NumberFormatException e) { // Jika input tidak sesuai
            e.printStackTrace(); // Menampilkan error
        }

        return option; // Mengembalikan nilai input dari user
    }

}
