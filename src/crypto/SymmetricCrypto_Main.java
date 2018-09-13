package crypto;

import crypto.symmetric.AES;

public class SymmetricCrypto_Main {

    public static void main(String[] args) {
        byte[] data = { (byte)0x8c, (byte)0xe6, (byte)0xa4, (byte)0xe8, (byte)0x66, (byte)0xa1, (byte)0x87, (byte)0x13, (byte)0x70, (byte)0xa4, (byte)0x5a, (byte)0xc1, (byte)0xe3, (byte)0x4a, (byte)0xa6, (byte)0x30 };
        byte[] key = { (byte)0x2D, (byte)0xF3, (byte)0x22, (byte)0x8B, (byte)0xAE, (byte)0x21, (byte)0x49, (byte)0x39, (byte)0x02, (byte)0xA2, (byte)0x06, (byte)0x42, (byte)0x2C, (byte)0x60, (byte)0xC8, (byte)0x1F };

        byte[] testKey = {
                (byte)0x2b,
                (byte)0x7e,
                (byte)0x15,
                (byte)0x16,
                (byte)0x28,
                (byte)0xae,
                (byte)0xd2,
                (byte)0xa6,
                (byte)0xab,
                (byte)0xf7,
                (byte)0x15,
                (byte)0x88,
                (byte)0x09,
                (byte)0xcf,
                (byte)0x4f,
                (byte)0x3c
        };

        AES aes = AES.Create(testKey);
        byte[] aes_key = aes.getKey();

        byte[] expandedKey = aes.getExpandedKey();


    }

}
