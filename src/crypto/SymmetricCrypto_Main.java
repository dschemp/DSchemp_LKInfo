package crypto;

import crypto.symmetric.AES;

public class SymmetricCrypto_Main {

    public static void main(String[] args) {

        byte[] data;
        byte[] key;
        byte[] encryptedData;

        /* --- AES-128 --- */
        data          = hexStringToByteArray("00112233445566778899aabbccddeeff");
        key           = hexStringToByteArray("000102030405060708090a0b0c0d0e0f");
        encryptedData = hexStringToByteArray("69c4e0d86a7b0430d8cdb78070b4c55a");

        AES aes128 = AES.Create(key);
        byte[] aes128_ciphertext = aes128.Encrypt(data);
        if (CheckIfArrayAreEqual(encryptedData, aes128_ciphertext))
            System.out.println("[AES-128] Encryption is ok");
        else
            System.out.println("[AES-128] Encryption is not ok");
        byte[] aes128_plaintext = aes128.Decrypt(encryptedData);
        if (CheckIfArrayAreEqual(data, aes128_plaintext))
            System.out.println("[AES-128] Decryption is ok");
        else
            System.out.println("[AES-128] Decryption is not ok");

        /* --- AES-192 --- */
        data          = hexStringToByteArray("00112233445566778899aabbccddeeff");
        key           = hexStringToByteArray("000102030405060708090a0b0c0d0e0f1011121314151617");
        encryptedData = hexStringToByteArray("dda97ca4864cdfe06eaf70a0ec0d7191");

        AES aes192 = AES.Create(key);
        byte[] aes192_ciphertext = aes192.Encrypt(data);
        if (CheckIfArrayAreEqual(encryptedData, aes192_ciphertext))
            System.out.println("[AES-192] Encryption is ok");
        else
            System.out.println("[AES-192] Encryption is not ok");
        byte[] aes192_plaintext = aes192.Decrypt(encryptedData);
        if (CheckIfArrayAreEqual(data, aes192_plaintext))
            System.out.println("[AES-192] Decryption is ok");
        else
            System.out.println("[AES-192] Decryption is not ok");

        /* --- AES-256 --- */
        data          = hexStringToByteArray("00112233445566778899aabbccddeeff");
        key           = hexStringToByteArray("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f");
        encryptedData = hexStringToByteArray("8ea2b7ca516745bfeafc49904b496089");

        AES aes256 = AES.Create(key);
        byte[] aes256_ciphertext = aes256.Encrypt(data);
        if (CheckIfArrayAreEqual(encryptedData, aes256_ciphertext))
            System.out.println("[AES-256] Encryption is ok");
        else
            System.out.println("[AES-256] Encryption is not ok");
        byte[] aes256_plaintext = aes256.Decrypt(encryptedData);
        if (CheckIfArrayAreEqual(data, aes256_plaintext))
            System.out.println("[AES-256] Decryption is ok");
        else
            System.out.println("[AES-256] Decryption is not ok");
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static boolean CheckIfArrayAreEqual(byte[] arr1, byte[] arr2) {
        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

}
