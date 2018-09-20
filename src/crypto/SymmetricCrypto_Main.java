package crypto;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import crypto.symmetric.AES;

public class SymmetricCrypto_Main {

    public static void main(String[] args) {

        AES aes192 = AES.CreateWithNewRandomKey(192);

        String s = "abcdef123456789abcdef123456789abcdef123456789abc";

        byte[] data = HexBin.decode(s);

        byte[] key = aes192.getKey();
        byte[] ciphertext = aes192.encrypt(data);
        byte[] plaintext = aes192.decrypt(ciphertext);

        String keyString = HexBin.encode(key);
        String ciphertextString = HexBin.encode(ciphertext);
        String plaintextString = HexBin.encode(plaintext);

        System.out.println(keyString);
        System.out.println(ciphertextString);
        System.out.println(s);
        System.out.println(plaintextString);
    }

}
