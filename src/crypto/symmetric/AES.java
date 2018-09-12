package crypto.symmetric;

import java.util.Random;

public class AES {

    //region Static Factory Methods
    public static AES Create(byte[] key) {
        return new AES(key);
    }

    public static AES CreateWithNewRandomKey(int bitLength) {
        // Der Key darf nur aus 16- (128 bits), 24- (192 bits) oder 32- (256 bits) bytes bestehen
        // Zulaessig: 128, 192 oder 256 Bit
        if (bitLength != 128 && bitLength != 192 && bitLength != 256) {
            throw new IllegalArgumentException("Key must be 128, 192 or 256 bits long");
        }

        Random rnd = new Random();
        byte[] arr = new byte[bitLength / 8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (byte)rnd.nextInt(256);
        }
        return Create(arr);
    }
    //endregion

    private byte[] Key;

    //region Key Getter and Setter
    public void setKey(byte[] key) {
        // Der Key darf nur aus 16- (128 bits), 24- (192 bits) oder 32- (256 bits) bytes bestehen
        // Zulaessig: 128, 192 oder 256 Bit
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("Key must be 128, 192 or 256 bits long");
        }
        this.Key = key;
    }

    public byte[] getKey() {
        return this.Key;
    }
    //endregion

    public AES(byte[] key) {
        setKey(key);
    }

    //region Encrypt / Decrypt Methods
    public byte[] Encrypt(byte[] data) {
        // Anzahl der Runden bei AES
        // max(b, k) | 128 | 192 | 256
        // Runden r	 | 10  | 12  | 14
        // Die Bit-Längen sind in 32-bit-(4 byte)-Schritten angegeben
        int rounds = (Key.length / 4) + 6;

        /*
            Arbeitsweise
            Rijndael ist eine als Substitutions-Permutations-Netzwerk entworfene Blockchiffre.
            Bei Rijndael können Blocklänge und Schlüssellänge unabhängig voneinander die
            Werte 128, 192 oder 256 Bits erhalten, während bei AES die Einschränkung
            der festgelegten Blockgröße von 128 Bit und der Schlüsselgröße von 128, 192 oder 256 Bit gilt.
            Jeder Block wird zunächst in eine zweidimensionale Tabelle mit vier Zeilen geschrieben,
            deren Zellen ein Byte groß sind.
            Die Anzahl der Spalten variiert somit je nach Blockgröße von 4 (128 Bits) bis 8 (256 Bits).
            Jeder Block wird nun nacheinander bestimmten Transformationen unterzogen.
            Aber anstatt jeden Block einmal mit dem Schlüssel zu verschlüsseln,
            wendet Rijndael verschiedene Teile des erweiterten Originalschlüssels nacheinander auf den Klartext-Block an.
         */

        // Als "WORD" kann der Datentyp _int_ benutzt werden, da dieser eine Groeße von 4 bytes (32 bits) besitzt.

        // das Arrays ist folgendermaßen aufgebaut [Spalten][Reihen]
        byte[][] state = new byte[4][4];

        for (int i = 0; i < 16; i++) {
            int x = i / 4;
            int y = i % 4;

            state[x][y] = data[i];
        }


        return null;
    }

    public byte[] Decrypt(byte[] encryptedData, byte[] key) {
        return null;
    }
    //endregion

    private byte[] AddRoundKey(byte[] state, byte[] key) {
        return null;
    }

    //region Encryption Methods
    private byte[] SubBytes(byte[] state) {
        return null;
    }

    private byte[] ShiftRows(byte[] state) {
        return null;
    }

    private byte[] MixColumns(byte[] state) {
        return null;
    }
    //endregion

    //region Decryption (Inverse) Methods
    private byte[] InvSubBytes(byte[] state) {
        return null;
    }

    private byte[] InvShiftRows(byte[] state) {
        return null;
    }

    private byte[] InvMixColumns(byte[] state) {
        return null;
    }
    //endregion

    //region Key Expansion
    private byte[] SubWord() {
        return null;
    }

    private byte[] RotWord() {
        return null;
    }
    //endregion
}
