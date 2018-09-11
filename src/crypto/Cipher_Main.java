package crypto;

import crypto.cipher.Caesar;
import crypto.cipher.Scytale;
import crypto.cipher.Vigenere;

public class Cipher_Main {

    public static void main(String[] args) {
        Caesar caesar     = new Caesar();
        Scytale scytale   = new Scytale();
        Vigenere vigenere = new Vigenere();

        String data    = "The quick brown fox jumps over the lazy dog";
        int num        = 13;
        char key       = 'N';
        String textKey = "KEY";

        System.out.println("TEXT:     " + data);

        /* ---------------------------------- */
        System.out.println("--- Caesar ---");
        String caesar_encrypt = caesar.Encrypt(data, key);
        System.out.println("ENCRYPT:  " + caesar_encrypt);
        String caesar_decrypt = caesar.Decrypt(caesar_encrypt, key);
        System.out.println("DECRYPT:  " + caesar_decrypt);
        /* ---------------------------------- */
        System.out.println("--- Scytale ---");
        String scytale_encrypt = scytale.Encrypt(data, num);
        System.out.println("ENCRYPT:  " + scytale_encrypt);
        String scytale_decrypt = scytale.Decrypt(scytale_encrypt, num);
        System.out.println("DECRYPT:  " + scytale_decrypt);
        // ----------------------------------
        System.out.println("--- Vigenere ---");
        String vigenere_encrypt = vigenere.Encrypt(data, textKey);
        System.out.println("ENCRYPT:  " + vigenere_encrypt);
        String vigenere_decrypt = vigenere.Decrypt(vigenere_encrypt, textKey);
        System.out.println("DECRYPT:  " + vigenere_decrypt);
    }

}
