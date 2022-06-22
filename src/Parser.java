import java.util.List;
import java.util.Stack;

public class Parser {

    List<Token> tokens; // Menyimpan list dari token yang diterima dari Lexer

    int pos = 0; // Menyimpan posisi dari token yang sedang dibaca

    Stack<Operation> stack = new Stack<>(); // Menyimpan stack dari operasi yang diterima dari Lexer

    Token current = null; // Menyimpan token yang sedang dibaca

    Parser(List<Token> tokens) { // Konstruktor
        this.tokens = tokens; // Menyimpan list dari token yang diterima dari Lexer
        advance(); // Membaca token pertama
    }

    void advance() { // Membaca token berikutnya
        try {
            current = tokens.get(pos); // Menyimpan token yang sedang dibaca
            pos++; // Menajukan posisi ke token berikutnya
        } catch (IndexOutOfBoundsException e) { // Jika posisi melebihi ukuran list
            current = null; // Menyimpan token null
        }

    }

    public float parse() { // Menghitung hasil ekspresi
        float result = expr(); // Menghitung hasil ekspresi
        if (current != null) { // Jika token yang sedang dibaca tidak sama dengan null
            throw new RuntimeException("Expected end of input but got " + current); // Menampilkan pesan error
        }
        return result; // Mengembalikan hasil ekspresi
    }

    public Stack<Operation> getStack() { // Mengembalikan stack dari operasi yang diterima dari Lexer
        return this.stack; // Mengembalikan stack dari operasi yang diterima dari Lexer

    }

    public float expr() { // Menghitung ekspresi
        float result = term(); // Menghitung term

        while (current != null && (current.type == TokenType.PLUS || current.type == TokenType.MINUS)) { // Jika token
                                                                                                         // yang sedang
                                                                                                         // dibaca sama
                                                                                                         // dengan PLUS
                                                                                                         // atau MINUS
            TokenType op = current.type; // Menyimpan tipe token yang sedang dibaca

            float cpResult = result; // Menyimpan hasil ekspresi yang sedang dibaca

            advance(); // Membaca token berikutnya
            float right = term(); // Menghitung term

            stack.push(new Operation(op, cpResult, right)); // Menambahkan operasi ke stack

            if (op == TokenType.PLUS) { // Jika tipe token yang sedang dibaca sama dengan PLUS
                result += right; // Menjumlahkan hasil ekspresi
            } else {
                result -= right; // Mengurangkan hasil ekspresi
            }

        }

        return result; // Mengembalikan hasil ekspresi

    }

    public float term() { // Menghitung term
        float result = factor(); // Menghitung factor

        while (current != null && (current.type == TokenType.MULTPIPLY || current.type == TokenType.DIVIDE)) { // Jika
                                                                                                               // token
                                                                                                               // yang
                                                                                                               // sedang
                                                                                                               // dibaca
                                                                                                               // sama
                                                                                                               // dengan
                                                                                                               // MULTPIPLY
                                                                                                               // atau
                                                                                                               // DIVIDE
            TokenType op = current.type; // Menyimpan tipe token yang sedang dibaca

            float cpResult = result; // Menyimpan hasil ekspresi yang sedang dibaca

            advance(); // Membaca token berikutnya

            float right = factor(); // Menghitung factor

            stack.push(new Operation(op, cpResult, right)); // Menambahkan operasi ke stack
            if (op == TokenType.MULTPIPLY) { // Jika tipe token yang sedang dibaca sama dengan MULTPIPLY
                result *= right; // Mengkalikan hasil ekspresi
            } else {
                result /= right; // Membagi hasil ekspresi
            }
        }

        return result; // Mengembalikan hasil ekspresi

    }

    public float factor() { // Menghitung factor
        if (current == null) { // Jika token yang sedang dibaca sama dengan null
            throw new RuntimeException("Unexpected end of input"); // Menampilkan pesan error
        }

        if (current.type == TokenType.NUMBER) { // Jika token yang sedang dibaca sama dengan NUMBER
            float result = Float.parseFloat(current.value); // Menyimpan nilai token yang sedang dibaca
            advance(); // Membaca token berikutnya
            return result; // Mengembalikan hasil ekspresi
        }

        if (current.type == TokenType.LPAREN) { // Jika token yang sedang dibaca sama dengan LPAREN
            advance(); // Membaca token berikutnya
            float result = expr(); // Menghitung ekspresi
            if (current == null || current.type != TokenType.RPAREN) { // Jika token yang sedang dibaca tidak sama
                                                                       // dengan RPAREN
                throw new RuntimeException("Expected ')'"); // Menampilkan pesan error
            }
            advance(); // Membaca token berikutnya
            return result; // Mengembalikan hasil ekspresi
        }

        throw new RuntimeException("Unexpected token: " + current); // Menampilkan pesan error

    }

}
