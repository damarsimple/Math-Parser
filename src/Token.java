public class Token {

    public TokenType type; // Menyimpan tipe token

    public String value; // Menyimpan nilai token

    public Token(TokenType type, String value) { // Konstruktor
        this.type = type; // Menyimpan tipe token
        this.value = value; // Menyimpan nilai token
    }

    public String toString() {// Menampilkan object token sebagai string
        return type + ": " + value; // Menampilkan tipe token dan nilai token
    }

}