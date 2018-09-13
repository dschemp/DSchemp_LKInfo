package crypto;

import crypto.asymmetric.RSA;
import org.omg.IOP.Encoding;

import java.math.BigInteger;
import java.util.Base64;

public class AsymmetricCrypto_Main {

    public static void main(String[] args) {
        BigInteger n = new BigInteger("3233");
        BigInteger e = new BigInteger("17");
        BigInteger d = new BigInteger("413");
        byte[] data = { (byte)0x65 };

        RSA rsa = new RSA();
        byte[] cipherText = rsa.Encrypt(data, n, e);
        byte[] plainText = rsa.Decrypt(cipherText, n, d);
        System.out.println();
    }
}
