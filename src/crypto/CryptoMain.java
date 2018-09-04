package crypto;

import crypto.cipher.Caesar;

public class CryptoMain {

    public static void main(String[] args) {
        String c = Caesar.Encrypt("GUTEN TAG CHRISTIAN, WIE GEHT'S DIR SO?!", 3);
        System.out.println(c);
        String d = Caesar.Decrypt(c, 3);
        System.out.println(d);
    }

}
