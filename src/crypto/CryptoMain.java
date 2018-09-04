package crypto;

public class CryptoMain {

    public static void main(String[] args) {
        String c = CaesersCipher.Encrypt("GUTEN TAG CHRISTIAN, WIE GEHT'S DIR SO?!", 3);
        System.out.println(c);
        String d = CaesersCipher.Decrypt(c, 3);
        System.out.println(d);
    }

}
