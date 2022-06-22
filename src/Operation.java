public class Operation { // Class untuk menyimpan operasi
    TokenType type; // Menyimpan tipe operasi
    float valueA = 0; // Menyimpan nilai operasi
    float valueB = 0; // Menyimpan nilai operasi

    public Operation(TokenType type, float valueA, float valueB) { // Konstruktor
        this.type = type; // Menyimpan tipe operasi
        this.valueA = valueA; // Menyimpan nilai operasi
        this.valueB = valueB; // Menyimpan nilai operasi
    }

    public String toString() { // Menampilkan object operasi sebagai string
        return "Operation{" +
                "type=" + type +
                ", valueA=" + valueA +
                ", valueB=" + valueB +
                '}';
    }
}
