package crypto;

import crypto.symmetric.AES;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AESTest {

    //region Global Variables
    AES aes128, aes192, aes256;
    byte[] key128, key192, key256;
    byte[] data;
    //endregion

    //region Setup / Initialization
    @org.junit.jupiter.api.BeforeAll
    public void setUp() {
        key128 = hexStringToByteArray("000102030405060708090a0b0c0d0e0f");
        key192 = hexStringToByteArray("000102030405060708090a0b0c0d0e0f1011121314151617");
        key256 = hexStringToByteArray("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f");

        aes128 = AES.Create(key128);
        aes192 = AES.Create(key192);
        aes256 = AES.Create(key256);

        data = hexStringToByteArray("00112233445566778899aabbccddeeff");
    }
    //endregion

    //region Helper methods
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    //endregion

    //region AES-128
    @org.junit.jupiter.api.Test
    void aes128_encrypt() {
        byte[] ciphertext = aes128.Encrypt(data);
        byte[] expected = hexStringToByteArray("69c4e0d86a7b0430d8cdb78070b4c55a");
        assertArrayEquals(ciphertext, expected);
    }

    @org.junit.jupiter.api.Test
    void aes128_decrypt() {
        byte[] ciphertext = hexStringToByteArray("69c4e0d86a7b0430d8cdb78070b4c55a");
        byte[] plaintext = aes128.Decrypt(ciphertext);
        assertArrayEquals(plaintext, data);
    }
    //endregion

    //region AES-192
    @org.junit.jupiter.api.Test
    void aes192_encrypt() {
        byte[] ciphertext = aes192.Encrypt(data);
        byte[] expected = hexStringToByteArray("dda97ca4864cdfe06eaf70a0ec0d7191");
        assertArrayEquals(ciphertext, expected);
    }

    @org.junit.jupiter.api.Test
    void aes192_decrypt() {
        byte[] ciphertext = hexStringToByteArray("dda97ca4864cdfe06eaf70a0ec0d7191");
        byte[] plaintext = aes192.Decrypt(ciphertext);
        assertArrayEquals(plaintext, data);
    }
    //endregion

    //region AES-256
    @org.junit.jupiter.api.Test
    void aes256_encrypt() {
        byte[] ciphertext = aes256.Encrypt(data);
        byte[] expected = hexStringToByteArray("8ea2b7ca516745bfeafc49904b496089");
        assertArrayEquals(ciphertext, expected);
    }

    @org.junit.jupiter.api.Test
    void aes256_decrypt() {
        byte[] ciphertext = hexStringToByteArray("8ea2b7ca516745bfeafc49904b496089");
        byte[] plaintext = aes256.Decrypt(ciphertext);
        assertArrayEquals(plaintext, data);
    }
    //endregion
}