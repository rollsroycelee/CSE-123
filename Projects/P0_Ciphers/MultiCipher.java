// TODO: Write your implementation to MultiCipher here!
import java.util.*;

// This class applies multiple encryption layers in sequence.
// Encrypts by applying each cipher in order. Decrypts by applying each cipher
// in reverse order
public class MultiCipher extends Cipher{
    private List<Cipher> ciphers;


    // Behavior: Constructs a new MultiCipher that will apply the given ciphers in sequence.
    // Exception: 
    // - Throws IllegalArgumentException if ciphers is null.
    //   Returns: nothing
    // Parameters:
    // - ciphers: the ordered collection of ciphers to apply
    public MultiCipher(List<Cipher> ciphers){
        if(ciphers == null){
            throw new IllegalArgumentException("Cipher list cant be null");
        }
        this.ciphers = ciphers;
    }

    // Behavior: Encrypts the given text by applying each cipher in sequence. The output
    // of each cipher becomes the input to the next cipher.
    // Exception: 
    // - Throws IllegalArgumentException if input is null.
    //   Returns: 
    // - result: The encrypted text after applying all ciphers
    // Parameters:
    // - input: the text to encrypt
    public String encrypt(String input){
        if(input == null){
            throw new IllegalArgumentException("Input cant be null");
        }

        String result = input;
        for (int i = 0; i < ciphers.size(); i++){
            result = ciphers.get(i).encrypt(result);
        }
        return result;
    }

    // Behavior: Decrypts the given text by applying each cipher's decryption in reverse
    // order. The output of each decryption becomes the input to the next.
    // Exception: 
    // - Throws IllegalArgumentException if input is null.
    //   Returns: 
    // - result: The decrypted text after applying all cipher decryptions
    // Parameters:
    // - input: the text to decrypt
    public String decrypt(String input){
        if(input == null){
            throw new IllegalArgumentException("Input cant be null");
        }

        String result = input;
        for (int i = ciphers.size() - 1; i >= 0; i--){
            result = ciphers.get(i).decrypt(result);
        }
        return result;
    }





}