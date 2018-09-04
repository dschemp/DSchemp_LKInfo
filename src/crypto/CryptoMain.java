package crypto;

public class CryptoMain {

    public static void main(String[] args) {
        String c = CaesersCrypto.Encrypt("GUTEN TAG CHRISTIAN, WIE GEHT'S DIR SO?!", 3);
        System.out.println(c);
        String d = CaesersCrypto.Decrypt(c, 3);
        System.out.println(d);
    }

}
