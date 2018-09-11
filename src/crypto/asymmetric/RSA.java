package crypto.asymmetric;

import java.math.BigInteger;

public class RSA {

    public byte[] Encrypt(byte[] data, BigInteger n, BigInteger e) {
        BigInteger b = new BigInteger(data);
        return b.modPow(e, n).toByteArray();
    }

    public byte[] Decrypt(byte[] encryptedData, BigInteger n, BigInteger d) {
        BigInteger b = new BigInteger(encryptedData);
        return b.modPow(d, n).toByteArray();
    }

}
