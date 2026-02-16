// Represents a Caesar shift cipher that encrypts text by rotating characters
// by a fixed number of positions.
public class CaesarShift extends Substitution{
    
    // Behavior: Constructs a new CaesarShift cipher that shifts characters by the
    // given amount.
    // Exception: 
    // - Throws IllegalArgumentException if shift is negative.
    //   Returns: nothing
    // Parameters:
    // - shift: the number of positions to rotate characters
    public CaesarShift(int shift){
        super();
        if (shift < 0){
            throw new IllegalArgumentException("You cant shift less than 0 times");
        }

        String encoding = "";

        for (int i = 0; i < TOTAL_CHARS; i++){
            int shiftedIndex = (i + shift) % TOTAL_CHARS; //mod allows for it to loop 
            char shiftedChar = (char)(MIN_CHAR + shiftedIndex);
            encoding += shiftedChar;
        }
        setEncoding(encoding);
    }
}