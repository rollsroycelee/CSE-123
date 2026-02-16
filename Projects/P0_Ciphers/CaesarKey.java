import java.util.*;
// This class creates an encoding by placing a key at the
// front of the substitution, followed by the remaining characters in order

public class CaesarKey extends Substitution{


    // Constructs a new CaesarKey cipher with the provided key value
    // Throws IllegalArgumentException if the key:
    // - is null
    // - contains duplicate characters
    // - contains characters outside the encodable range
    public  CaesarKey(String key){
        super();
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        //see if there are duplciates / valid characters in key
        Set<Character> keyChars = new HashSet<>();
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if (!isCharInRange(c)){
                throw new IllegalArgumentException("Key contains character outside the range");
            }
            if(keyChars.contains(c)){
                throw new IllegalArgumentException("Key contains duplicate character");
            }
            keyChars.add(c);
        }

        String encoding = key;
        for (int i = 0; i < TOTAL_CHARS; i++){
            char c = (char)(MIN_CHAR + i);
            if(!keyChars.contains(c)){
                encoding += c;
            }
        }

        setEncoding(encoding);
    }
}