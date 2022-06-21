public class Operation {
    TokenType type;
    float valueA = 0; 
    float valueB = 0;
    
    public Operation(TokenType type, float valueA, float valueB) {
        this.type = type;
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public String toString() {
        return "Operation{" +
                "type=" + type +
                ", valueA=" + valueA +
                ", valueB=" + valueB +
                '}';
    }
}
