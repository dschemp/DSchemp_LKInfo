package crypto;

import crypto.cipher.Scytale;
import interfaces.Cipherable;

public class Crypto_Main {

    public static void main(String[] args) {
        Cipherable cipher = new Scytale();

        String data = "Halli Hallo, Wie geht's?";
        int num = 11;

        System.out.println("TEXT:     " + data);

        String c = cipher.Encrypt(data, num);
        System.out.println("ENCRYPT:  " + c);
        String d = cipher.Decrypt(c, num);
        System.out.println("DECRYPT:  " + d);
    }

}
