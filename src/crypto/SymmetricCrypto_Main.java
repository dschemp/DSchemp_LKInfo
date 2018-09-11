package crypto;

import crypto.symmetric.AES;

public class SymmetricCrypto_Main {

    public static void main(String[] args) {
        int[] intData = { 0x8c, 0xe6, 0xa4, 0xe8, 0x66, 0xa1, 0x87, 0x13, 0x70, 0xa4, 0x5a, 0xc1, 0xe3, 0x4a, 0xa6, 0x30 };
        int[] intKey = { 0x2D, 0xF3, 0x22, 0x8B, 0xAE, 0x21, 0x49, 0x39, 0x02, 0xA2, 0x06, 0x42, 0x2C, 0x60, 0xC8, 0x1F };
        byte[] binData = new byte[intData.length];
        byte[] binKey = new byte[intKey.length];
        for (int i = 0; i < intData.length; i++) {
            binData[i] = (byte)intData[i];
        }
        for (int i = 0; i < intKey.length; i++) {
            binKey[i] = (byte)intKey[i];
        }

        AES aes = AES.Create(binKey);
        byte[] key = aes.getKey();

    }

}
