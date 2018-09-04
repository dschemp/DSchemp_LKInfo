package crypto;

import crypto.cipher.Scytale;
import interfaces.Cipherable;

public class CryptoMain {

    public static void main(String[] args) {
        Cipherable cipher = new Scytale();

        String c = cipher.Encrypt("Halli Hallo, Wie gehtts?", 11);
        System.out.println(c);
        String d = cipher.Decrypt(c, 3);
        System.out.println(d);
    }

}
