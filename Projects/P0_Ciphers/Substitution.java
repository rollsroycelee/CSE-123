import java.util.*;


// This class encrypts and decrypts text by mapping each
// character to a different character based on a defined encoding.
public class Substitution extends Cipher{
    private String encoding;


    // Behavior: Constructs a new Substitution Cipher with an empty encoding.
    // Exceptions: None
    // Returns: A new Substitution object with empty encoding
    // Parameters: None
    public Substitution(){
        this.encoding = "";
    }


    //Behavior: Constructs a new Substitution Cipher with the provided encoding.
    //Exceptions: Throws IllegalArgumentException if the encoding is invalid:
    // - encoding is null
    // - length doesn't match TOTAL_CHARS
    // - contains duplicate characters
    // - contains characters outside the encodable range
    //Returns: 
    // - A new Substitution object with the given encoding
    //Parameters: 
    // - encoding: the string representing character mappings for encryption.
    public Substitution(String encoding){
        if(encoding == null){
            throw new IllegalArgumentException("Encoding can not be null");
        }

        if(encoding.length() != TOTAL_CHARS){
            throw new IllegalArgumentException("Encoding length must equal TOTAL_CHARS");
        }

        Set<Character> seen = new HashSet<>();
        for(int i = 0; i < encoding.length(); i++){
            char c = encoding.charAt(i);
            if (!isCharInRange(c)){
                throw new IllegalArgumentException("Encoding contains characters outside range");
            }
            if(seen.contains(c)){
                throw new IllegalArgumentException("Encoding contains duplicate character");
            }
            seen.add(c);
        }

        this.encoding = encoding;
    }
    

    // Behavior: Updates the encoding for this Substitution Cipher.
    // Exceptions: Throws IllegalArgumentException if the encoding is invalid:
    // - encoding is null
    // - length doesn't match TOTAL_CHARS
    // - contains duplicate characters
    // - contains characters outside the encodable range
    //    Returns: Nothing
    // Parameters: 
    // - encoding: the new string representing character mappings for encryption.
    public void setEncoding(String encoding){
        if(encoding == null){
            throw new IllegalArgumentException("Encoding can not be null");
        }
        if(encoding.length() != TOTAL_CHARS){
            throw new IllegalArgumentException("Encoding length must equal TOTAL_CHARS");
        }


        Set<Character> seen = new HashSet<>();
        for(int i = 0; i < encoding.length(); i++){
            char c = encoding.charAt(i);
            if (!isCharInRange(c)){
                throw new IllegalArgumentException("Encoding contains characters outside range");
            }
            if(seen.contains(c)){
                throw new IllegalArgumentException("Encoding contains duplicate character");
            }
            seen.add(c);
        }

        this.encoding = encoding;
    }



    //Behavior: Applies this Cipher's encryption scheme to the input string. Each
    // character in the encodable range is replaced with the character at the
    // corresponding position in the encoding. Characters outside the encodable
    // range do not change.
    // Exceptions: 
    // - Throws IllegalArgumentException if input is null.
    // - Throws IllegalStateException if encoding was never set.
    // Returns: 
    // - result: The encrypted version of the input string
    // Parameters: 
    // - input: the string to be encrypted. Should be non-null.
    public String encrypt(String input){
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (encoding.isEmpty()) {
            throw new IllegalStateException("Encoding was never set");
        }

        String result = "";

        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (isCharInRange(c)){
                int index = c - MIN_CHAR;
                result += encoding.charAt(index);
            } else {
                result += c;
            }
        }
        return result;
    }




    //Behavior: Applies the inverse of this Cipher's encryption scheme to the input string.
    // Each character in the encodable range is replaced with the character whose
    // position in the encoding matches the input character. Characters outside
    // the encodable range remain unchanged.
    // Exceptions: 
    // - Throws IllegalArgumentException if input is null.
    // - Throws IllegalStateException if encoding was never set.
    //Returns: 
    // - result: The decrypted version of the input string
    //Parameters: 
    // - input: the string to be decrypted. Should be non-null.

    public String decrypt(String input){
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (encoding.isEmpty()) {
            throw new IllegalStateException("Encoding was never set");
        }

        String result = "";
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (isCharInRange(c)){
                int encodingIndex = encoding.indexOf(c);
                char decoded = (char)(MIN_CHAR + encodingIndex);
                result += decoded;
            } else {
                result += c;
            }

        }

        return result;
    }
}