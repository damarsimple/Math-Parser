
import java.util.ArrayList;
import java.util.List;

class Lexer {
    public final String DIGITS = "0123456789"; // Digit
    public final String WHITESPACE = "\n\t\r "; // Whitespace

    public char current; // Digunakan untuk menyimpan karakter yang sedang dibaca

    public String text; // Digunakan untuk menyimpan input dari user

    public int pos = 0; // Digunakan untuk menyimpan posisi dari karakter yang sedang dibaca

    Lexer(String text) { // Konstruktor
        this.text = text; // Menyimpan input dari user
        advance(); // Membaca karakter pertama
    }

    void advance() {
        try {
            current = text.charAt(pos); // Menyimpan karakter yang sedang dibaca
            pos++; // Menajukan posisi ke karakter berikutnya
        } catch (IndexOutOfBoundsException e) { // Jika posisi melebihi ukuran string
            current = '\0'; // Menyimpan karakter null
        }
    }

    List<Token> generateTokens() {

        List<Token> tokens = new ArrayList<>(); // Membuat list dari token yang diterima dari Lexer

        while (current != '\0') { // Selama karakter yang sedang dibaca tidak sama dengan null

            if (current == ' ' || current == '\n' || current == '\t') { // Jika karakter yang sedang dibaca adalah
                                                                        // whitespace
                advance(); // Menajukan posisi ke karakter berikutnya
            }
            if (current == '+') { // Jika karakter yang sedang dibaca adalah +
                tokens.add(new Token(TokenType.PLUS, "+")); // Menambahkan token + ke list
                advance(); // Menajukan posisi ke karakter berikutnya
            } else if (current == '-') { // Jika karakter yang sedang dibaca adalah -
                tokens.add(new Token(TokenType.MINUS, "-")); // Menambahkan token - ke list
                advance(); // Menajukan posisi ke karakter berikutnya
            } else if (current == '*') { // Jika karakter yang sedang dibaca adalah *
                tokens.add(new Token(TokenType.MULTPIPLY, "*")); // Menambahkan token * ke list
                advance(); // Menajukan posisi ke karakter berikutnya
            } else if (current == '/') { // Jika karakter yang sedang dibaca adalah /
                tokens.add(new Token(TokenType.DIVIDE, "/")); // Menambahkan token / ke list
                advance(); // Menajukan posisi ke karakter berikutnya
            } else if (current == '(') { // Jika karakter yang sedang dibaca adalah (
                tokens.add(new Token(TokenType.LPAREN, "(")); // Menambahkan token ( ke list
                advance(); // Menajukan posisi ke karakter berikutnya
            } else if (current == ')') { // Jika karakter yang sedang dibaca adalah )
                tokens.add(new Token(TokenType.RPAREN, ")")); // Menambahkan token ) ke list
                advance(); // Menajukan posisi ke karakter berikutnya
            } else if (Character.isDigit(current)) { // Jika karakter yang sedang dibaca adalah digit
                StringBuilder sb = new StringBuilder(); // Membuat string builder untuk nomor
                while (Character.isDigit(current) || current == '.') { // Selama karakter yang sedang dibaca adalah
                                                                       // digit ataupun .
                    sb.append(current); // Menambahkan karakter yang sedang dibaca ke string builder
                    advance(); // Menajukan posisi ke karakter berikutnya
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString())); // Menambahkan token nomor ke list
            } else { // Jika karakter yang sedang dibaca bukanlah digit, whitespace, atau operator
                throw new RuntimeException("Invalid character: " + current); // Menampilkan error
            }
        }

        return tokens; // Mengembalikan list dari token yang diterima dari Lexer

    }
}
