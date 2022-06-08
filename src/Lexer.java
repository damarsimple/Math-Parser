class Lexer {
    public final String DIGITS = "0123456789";
    public final String WHITESPACE = "\n\t\r ";

    public int current = 0;

    public String text;

    Lexer(String text) {
        this.text = text;
    }
}
