package crypto.symmetric;

import java.util.Arrays;
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

    //region Key + Getter and Setter
    private byte[] Key;

    private void setKey(byte[] key) {
        // Der Key darf nur aus 16- (128 bits), 24- (192 bits) oder 32- (256 bits) bytes bestehen
        // Zulaessig: 128, 192 oder 256 Bit
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("Key must be 128, 192 or 256 bits long");
        }
        this.Key = key;

        //region Calculate Rounds
        // Anzahl der Runden bei AES
        // max(b, k) | 128 | 192 | 256
        // Runden r	 | 10  | 12  | 14
        // Die Bit-Längen sind in 32-bit-(4 byte)-Schritten angegeben
        this.Rounds = (Key.length / 4) + 6;
        //endregion

        //region When setting the key, generate the expanded key and place it in the variable
        byte[] expandedKey = expandKey(key, key.length / 4);
        setExpandedKey(expandedKey);
        //endregion
    }

    public byte[] getKey() {
        return this.Key;
    }
    //endregion

    //region Misc
    private int Rounds;

    private byte[] Rcon = { (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00 };
    //endregion

    //region Expanded Key + Getter and Setter
    private byte[] ExpandedKey;

    private void setExpandedKey(byte[] expandedKey) {
        this.ExpandedKey = expandedKey;
    }
    //endregion

    //region SBoxes
    // SBox[x][y]
    private byte[][] SBox = {
            { (byte)0x63, (byte)0x7c, (byte)0x77, (byte)0x7b, (byte)0xf2, (byte)0x6b, (byte)0x6f, (byte)0xc5, (byte)0x30, (byte)0x01, (byte)0x67, (byte)0x2b, (byte)0xfe, (byte)0xd7, (byte)0xab, (byte)0x76, },
            { (byte)0xca, (byte)0x82, (byte)0xc9, (byte)0x7d, (byte)0xfa, (byte)0x59, (byte)0x47, (byte)0xf0, (byte)0xad, (byte)0xd4, (byte)0xa2, (byte)0xaf, (byte)0x9c, (byte)0xa4, (byte)0x72, (byte)0xc0, },
            { (byte)0xb7, (byte)0xfd, (byte)0x93, (byte)0x26, (byte)0x36, (byte)0x3f, (byte)0xf7, (byte)0xcc, (byte)0x34, (byte)0xa5, (byte)0xe5, (byte)0xf1, (byte)0x71, (byte)0xd8, (byte)0x31, (byte)0x15, },
            { (byte)0x04, (byte)0xc7, (byte)0x23, (byte)0xc3, (byte)0x18, (byte)0x96, (byte)0x05, (byte)0x9a, (byte)0x07, (byte)0x12, (byte)0x80, (byte)0xe2, (byte)0xeb, (byte)0x27, (byte)0xb2, (byte)0x75, },
            { (byte)0x09, (byte)0x83, (byte)0x2c, (byte)0x1a, (byte)0x1b, (byte)0x6e, (byte)0x5a, (byte)0xa0, (byte)0x52, (byte)0x3b, (byte)0xd6, (byte)0xb3, (byte)0x29, (byte)0xe3, (byte)0x2f, (byte)0x84, },
            { (byte)0x53, (byte)0xd1, (byte)0x00, (byte)0xed, (byte)0x20, (byte)0xfc, (byte)0xb1, (byte)0x5b, (byte)0x6a, (byte)0xcb, (byte)0xbe, (byte)0x39, (byte)0x4a, (byte)0x4c, (byte)0x58, (byte)0xcf, },
            { (byte)0xd0, (byte)0xef, (byte)0xaa, (byte)0xfb, (byte)0x43, (byte)0x4d, (byte)0x33, (byte)0x85, (byte)0x45, (byte)0xf9, (byte)0x02, (byte)0x7f, (byte)0x50, (byte)0x3c, (byte)0x9f, (byte)0xa8, },
            { (byte)0x51, (byte)0xa3, (byte)0x40, (byte)0x8f, (byte)0x92, (byte)0x9d, (byte)0x38, (byte)0xf5, (byte)0xbc, (byte)0xb6, (byte)0xda, (byte)0x21, (byte)0x10, (byte)0xff, (byte)0xf3, (byte)0xd2, },
            { (byte)0xcd, (byte)0x0c, (byte)0x13, (byte)0xec, (byte)0x5f, (byte)0x97, (byte)0x44, (byte)0x17, (byte)0xc4, (byte)0xa7, (byte)0x7e, (byte)0x3d, (byte)0x64, (byte)0x5d, (byte)0x19, (byte)0x73, },
            { (byte)0x60, (byte)0x81, (byte)0x4f, (byte)0xdc, (byte)0x22, (byte)0x2a, (byte)0x90, (byte)0x88, (byte)0x46, (byte)0xee, (byte)0xb8, (byte)0x14, (byte)0xde, (byte)0x5e, (byte)0x0b, (byte)0xdb, },
            { (byte)0xe0, (byte)0x32, (byte)0x3a, (byte)0x0a, (byte)0x49, (byte)0x06, (byte)0x24, (byte)0x5c, (byte)0xc2, (byte)0xd3, (byte)0xac, (byte)0x62, (byte)0x91, (byte)0x95, (byte)0xe4, (byte)0x79, },
            { (byte)0xe7, (byte)0xc8, (byte)0x37, (byte)0x6d, (byte)0x8d, (byte)0xd5, (byte)0x4e, (byte)0xa9, (byte)0x6c, (byte)0x56, (byte)0xf4, (byte)0xea, (byte)0x65, (byte)0x7a, (byte)0xae, (byte)0x08, },
            { (byte)0xba, (byte)0x78, (byte)0x25, (byte)0x2e, (byte)0x1c, (byte)0xa6, (byte)0xb4, (byte)0xc6, (byte)0xe8, (byte)0xdd, (byte)0x74, (byte)0x1f, (byte)0x4b, (byte)0xbd, (byte)0x8b, (byte)0x8a, },
            { (byte)0x70, (byte)0x3e, (byte)0xb5, (byte)0x66, (byte)0x48, (byte)0x03, (byte)0xf6, (byte)0x0e, (byte)0x61, (byte)0x35, (byte)0x57, (byte)0xb9, (byte)0x86, (byte)0xc1, (byte)0x1d, (byte)0x9e, },
            { (byte)0xe1, (byte)0xf8, (byte)0x98, (byte)0x11, (byte)0x69, (byte)0xd9, (byte)0x8e, (byte)0x94, (byte)0x9b, (byte)0x1e, (byte)0x87, (byte)0xe9, (byte)0xce, (byte)0x55, (byte)0x28, (byte)0xdf, },
            { (byte)0x8c, (byte)0xa1, (byte)0x89, (byte)0x0d, (byte)0xbf, (byte)0xe6, (byte)0x42, (byte)0x68, (byte)0x41, (byte)0x99, (byte)0x2d, (byte)0x0f, (byte)0xb0, (byte)0x54, (byte)0xbb, (byte)0x16 }
    };

    // InvSBox[x][y]
    private byte[][] InvSBox = {
            { (byte)0x52, (byte)0x09, (byte)0x6a, (byte)0xd5, (byte)0x30, (byte)0x36, (byte)0xa5, (byte)0x38, (byte)0xbf, (byte)0x40, (byte)0xa3, (byte)0x9e, (byte)0x81, (byte)0xf3, (byte)0xd7, (byte)0xfb },
            { (byte)0x7c, (byte)0xe3, (byte)0x39, (byte)0x82, (byte)0x9b, (byte)0x2f, (byte)0xff, (byte)0x87, (byte)0x34, (byte)0x8e, (byte)0x43, (byte)0x44, (byte)0xc4, (byte)0xde, (byte)0xe9, (byte)0xcb },
            { (byte)0x54, (byte)0x7b, (byte)0x94, (byte)0x32, (byte)0xa6, (byte)0xc2, (byte)0x23, (byte)0x3d, (byte)0xee, (byte)0x4c, (byte)0x95, (byte)0x0b, (byte)0x42, (byte)0xfa, (byte)0xc3, (byte)0x4e },
            { (byte)0x08, (byte)0x2e, (byte)0xa1, (byte)0x66, (byte)0x28, (byte)0xd9, (byte)0x24, (byte)0xb2, (byte)0x76, (byte)0x5b, (byte)0xa2, (byte)0x49, (byte)0x6d, (byte)0x8b, (byte)0xd1, (byte)0x25 },
            { (byte)0x72, (byte)0xf8, (byte)0xf6, (byte)0x64, (byte)0x86, (byte)0x68, (byte)0x98, (byte)0x16, (byte)0xd4, (byte)0xa4, (byte)0x5c, (byte)0xcc, (byte)0x5d, (byte)0x65, (byte)0xb6, (byte)0x92 },
            { (byte)0x6c, (byte)0x70, (byte)0x48, (byte)0x50, (byte)0xfd, (byte)0xed, (byte)0xb9, (byte)0xda, (byte)0x5e, (byte)0x15, (byte)0x46, (byte)0x57, (byte)0xa7, (byte)0x8d, (byte)0x9d, (byte)0x84 },
            { (byte)0x90, (byte)0xd8, (byte)0xab, (byte)0x00, (byte)0x8c, (byte)0xbc, (byte)0xd3, (byte)0x0a, (byte)0xf7, (byte)0xe4, (byte)0x58, (byte)0x05, (byte)0xb8, (byte)0xb3, (byte)0x45, (byte)0x06 },
            { (byte)0xd0, (byte)0x2c, (byte)0x1e, (byte)0x8f, (byte)0xca, (byte)0x3f, (byte)0x0f, (byte)0x02, (byte)0xc1, (byte)0xaf, (byte)0xbd, (byte)0x03, (byte)0x01, (byte)0x13, (byte)0x8a, (byte)0x6b },
            { (byte)0x3a, (byte)0x91, (byte)0x11, (byte)0x41, (byte)0x4f, (byte)0x67, (byte)0xdc, (byte)0xea, (byte)0x97, (byte)0xf2, (byte)0xcf, (byte)0xce, (byte)0xf0, (byte)0xb4, (byte)0xe6, (byte)0x73 },
            { (byte)0x96, (byte)0xac, (byte)0x74, (byte)0x22, (byte)0xe7, (byte)0xad, (byte)0x35, (byte)0x85, (byte)0xe2, (byte)0xf9, (byte)0x37, (byte)0xe8, (byte)0x1c, (byte)0x75, (byte)0xdf, (byte)0x6e },
            { (byte)0x47, (byte)0xf1, (byte)0x1a, (byte)0x71, (byte)0x1d, (byte)0x29, (byte)0xc5, (byte)0x89, (byte)0x6f, (byte)0xb7, (byte)0x62, (byte)0x0e, (byte)0xaa, (byte)0x18, (byte)0xbe, (byte)0x1b },
            { (byte)0xfc, (byte)0x56, (byte)0x3e, (byte)0x4b, (byte)0xc6, (byte)0xd2, (byte)0x79, (byte)0x20, (byte)0x9a, (byte)0xdb, (byte)0xc0, (byte)0xfe, (byte)0x78, (byte)0xcd, (byte)0x5a, (byte)0xf4 },
            { (byte)0x1f, (byte)0xdd, (byte)0xa8, (byte)0x33, (byte)0x88, (byte)0x07, (byte)0xc7, (byte)0x31, (byte)0xb1, (byte)0x12, (byte)0x10, (byte)0x59, (byte)0x27, (byte)0x80, (byte)0xec, (byte)0x5f },
            { (byte)0x60, (byte)0x51, (byte)0x7f, (byte)0xa9, (byte)0x19, (byte)0xb5, (byte)0x4a, (byte)0x0d, (byte)0x2d, (byte)0xe5, (byte)0x7a, (byte)0x9f, (byte)0x93, (byte)0xc9, (byte)0x9c, (byte)0xef },
            { (byte)0xa0, (byte)0xe0, (byte)0x3b, (byte)0x4d, (byte)0xae, (byte)0x2a, (byte)0xf5, (byte)0xb0, (byte)0xc8, (byte)0xeb, (byte)0xbb, (byte)0x3c, (byte)0x83, (byte)0x53, (byte)0x99, (byte)0x61 },
            { (byte)0x17, (byte)0x2b, (byte)0x04, (byte)0x7e, (byte)0xba, (byte)0x77, (byte)0xd6, (byte)0x26, (byte)0xe1, (byte)0x69, (byte)0x14, (byte)0x63, (byte)0x55, (byte)0x21, (byte)0x0c, (byte)0x7d }
    };
    //endregion

    public AES(byte[] key) {
        setKey(key);
    }

    //region Encrypt / Decrypt Methods
    private byte[] encryptBlock(byte[] data) {
        byte[][] state = convertArrayToState(data);

        state = addRoundKey(state, 0);

        for (int r = 1; r < Rounds; r++) {
            state = subBytes(state);
            state = shiftRows(state);
            state = mixColumns(state);
            state = addRoundKey(state, r);
        }

        state = subBytes(state);
        state = shiftRows(state);
        state = addRoundKey(state, Rounds);

        byte[] encryptedData = convertStateToByteArrray(state);
        return encryptedData;
    }

    private byte[] decryptBlock(byte[] encryptedData) {
        byte[][] state = convertArrayToState(encryptedData);

        state = addRoundKey(state, Rounds);

        for (int r = Rounds - 1; r >= 1; r--) {
            state = invShiftRows(state);
            state = invSubBytes(state);
            state = addRoundKey(state, r);
            state = invMixColumns(state);
        }

        state = invShiftRows(state);
        state = invSubBytes(state);
        state = addRoundKey(state, 0);

        byte[] decryptedData = convertStateToByteArrray(state);
        return decryptedData;
    }
    //endregion

    //region Wrapper Methods
    public byte[] encrypt(byte[] data) {
        int blocks = (int)Math.ceil(data.length/16.0);
        byte[] output = new byte[blocks*16];

        for (int i = 0; i < blocks; i++) {
            int start = i * 16;
            int end = (i+1)*16;
            byte[] block = Arrays.copyOfRange(data, start, end);
            byte[] ciphertext = encryptBlock(block);
            for (int j = start; j < end; j++) {
                output[j] = ciphertext[j - start];
            }
        }
        return output;
    }

    public byte[] decrypt(byte[] encryptedData) {
        int blocks = (int)Math.ceil(encryptedData.length/16.0);
        byte[] output = new byte[blocks*16];

        for (int i = 0; i < blocks; i++) {
            int start = i * 16;
            int end = (i+1)*16;
            byte[] block = Arrays.copyOfRange(encryptedData, start, end);
            byte[] ciphertext = decryptBlock(block);
            for (int j = start; j < end; j++) {
                output[j] = ciphertext[j - start];
            }
        }
        return output;
    }
    //endregion

    //region addRoundKey Method (for en- and decryption)
    private byte[][] addRoundKey(byte[][] state, int round) {
        for (int c = 0; c < 4; c++) {
            state[c][0] ^= ExpandedKey[4 * 4 * round + 4 * c + 0];
            state[c][1] ^= ExpandedKey[4 * 4 * round + 4 * c + 1];
            state[c][2] ^= ExpandedKey[4 * 4 * round + 4 * c + 2];
            state[c][3] ^= ExpandedKey[4 * 4 * round + 4 * c + 3];
        }

        return state;
    }
    //endregion

    //region Encryption Methods
    private byte[][] subBytes(byte[][] state) {
        for (int i = 0; i < 16; i++) {
            int x = i / 4;
            int y = i % 4;

            int firstNum  = (state[x][y] & 0xf0) >> 4;
            int secondNum = (state[x][y] & 0xf);

            state[x][y] = SBox[firstNum][secondNum];
        }

        return state;
    }

    private byte[][] shiftRows(byte[][] state) {
        byte[][] tempState = copyStateArray(state);

        // go through each row, starting at 1 because no bytes are shifted at 0
        for (int y = 1; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int newX = (x + y) % 4;
                tempState[x][y] = state[newX][y];
            }
        }

        return tempState;
    }

    private byte[][] mixColumns(byte[][] state) {
        byte[][] tempState = new byte[4][4];

        for (int x = 0; x < 4; x++) {

            tempState[x][0] = (byte)( bitwiseMultiplication((byte)0x2, state[x][0]) ^ bitwiseMultiplication((byte)0x3, state[x][1]) ^ state[x][2]                                   ^ state[x][3] );
            tempState[x][1] = (byte)( state[x][0]                                   ^ bitwiseMultiplication((byte)0x2, state[x][1]) ^ bitwiseMultiplication((byte)0x3, state[x][2]) ^ state[x][3] );
            tempState[x][2] = (byte)( state[x][0]                                   ^ state[x][1]                                   ^ bitwiseMultiplication((byte)0x2, state[x][2]) ^ bitwiseMultiplication((byte)0x3, state[x][3]) );
            tempState[x][3] = (byte)( bitwiseMultiplication((byte)0x3, state[x][0]) ^ state[x][1]                                   ^ state[x][2]                                   ^ bitwiseMultiplication((byte)0x2, state[x][3]) );
        }
        return tempState;
    }
    //endregion

    //region Decryption (Inverse) Methods
    private byte[][] invSubBytes(byte[][] state) {
        for (int i = 0; i < 16; i++) {
            int x = i / 4;
            int y = i % 4;

            int firstNum  = (state[x][y] & 0xf0) >> 4;
            int secondNum = (state[x][y] & 0xf);

            state[x][y] = InvSBox[firstNum][secondNum];
        }

        return state;
    }

    private byte[][] invShiftRows(byte[][] state) {
        byte[][] tempState = copyStateArray(state);

        // go through each row, starting at 1 because no bytes are shifted at 0
        for (int y = 1; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int newX = (x + 4 - y) % 4;
                tempState[x][y] = state[newX][y];
            }
        }

        return tempState;
    }

    private byte[][] invMixColumns(byte[][] state) {
        byte[][] tempState = new byte[4][4];

        for (int x = 0; x < 4; x++) {

            tempState[x][0] = (byte)( bitwiseMultiplication((byte)0x0e, state[x][0]) ^ bitwiseMultiplication((byte)0x0b, state[x][1]) ^ bitwiseMultiplication((byte)0x0d, state[x][2]) ^ bitwiseMultiplication((byte)0x09, state[x][3]) );
            tempState[x][1] = (byte)( bitwiseMultiplication((byte)0x09, state[x][0]) ^ bitwiseMultiplication((byte)0x0e, state[x][1]) ^ bitwiseMultiplication((byte)0x0b, state[x][2]) ^ bitwiseMultiplication((byte)0x0d, state[x][3]) );
            tempState[x][2] = (byte)( bitwiseMultiplication((byte)0x0d, state[x][0]) ^ bitwiseMultiplication((byte)0x09, state[x][1]) ^ bitwiseMultiplication((byte)0x0e, state[x][2]) ^ bitwiseMultiplication((byte)0x0b, state[x][3]) );
            tempState[x][3] = (byte)( bitwiseMultiplication((byte)0x0b, state[x][0]) ^ bitwiseMultiplication((byte)0x0d, state[x][1]) ^ bitwiseMultiplication((byte)0x09, state[x][2]) ^ bitwiseMultiplication((byte)0x0e, state[x][3]) );
        }
        return tempState;
    }
    //endregion

    //region Key Expansion
    private byte[] expandKey(byte[] key, int Nk) {
        byte[] words = new byte[4*4*(Rounds+1)];

        byte[] temp = new byte[4]; // Word

        int i = 0;

        while (i < Nk) {
            words[4 * i]     = key[4 * i];
            words[4 * i + 1] = key[4 * i + 1];
            words[4 * i + 2] = key[4 * i + 2];
            words[4 * i + 3] = key[4 * i + 3];
            i++;
        }

        i = Nk;

        while (i < 4 * (Rounds + 1)) {
            temp[0] = words[4 * (i - 1)];
            temp[1] = words[4 * (i - 1) + 1];
            temp[2] = words[4 * (i - 1) + 2];
            temp[3] = words[4 * (i - 1) + 3];

            if (i % Nk == 0) {
                temp = rotWord(temp);
                temp = subWord(temp);
                temp = coefAdd(temp, rcon(((byte) (i / Nk))));
            }
            else if (Nk > 6 && i % Nk == 4) {
                temp = subWord(temp);
            }

            words[4 * i] = ((byte) (words[4 * (i - Nk)] ^ temp[0]));
            words[4 * i + 1] = ((byte) (words[4 * (i - Nk) + 1] ^ temp[1]));
            words[4 * i + 2] = ((byte) (words[4 * (i - Nk) + 2] ^ temp[2]));
            words[4 * i + 3] = ((byte) (words[4 * (i - Nk) + 3] ^ temp[3]));

            i++;
        }
        return words;
    }

    //region Adopted from another source
    private byte[] coefAdd(byte[] word, byte[] word2) {
        byte[] temp = new byte[4];

        temp[0] = ((byte) (word[0] ^ word2[0]));
        temp[1] = ((byte) (word[1] ^ word2[1]));
        temp[2] = ((byte) (word[2] ^ word2[2]));
        temp[3] = ((byte) (word[3] ^ word2[3]));

        return temp;
    }

    private byte[] rcon(byte i) {

        if (i == 1) {
            Rcon[0] = 0x01; // x^(1-1) = x^0 = 1
        } else if (i > 1) {
            Rcon[0] = 0x02;
            i--;
            while (i-1 > 0) {
                Rcon[0] = bitwiseMultiplication(Rcon[0], (byte)0x02);
                i--;
            }
        }

        return Rcon;
    }

    private byte bitwiseMultiplication(byte a, byte b) {

        byte p = (byte)0x0, i, hbs;

        for (i = 0; i < 8; i++) {
            if ((b & (byte)0x1) != 0) {
                p ^= a;
            }

            hbs = (byte)(a & 0x80);
            a <<= 1;
            if (hbs != 0) a ^= 0x1b; // 0000 0001 0001 1011
            b >>= 1;
        }

        return p;
    }
    //endregion

    private byte[] subWord(byte[] word) {
        for (int i = 0; i < word.length; i++) {
            int firstNum = (word[i] & 0xF0) >> 4;
            int secondNum = (word[i] & 0x0F);

            word[i] = SBox[firstNum][secondNum];
        }

        return word;
    }

    private byte[] rotWord(byte[] word) {
        byte first = word[0];

        // Shift all bytes one byte to the left
        word[0] = word[1];
        word[1] = word[2];
        word[2] = word[3];
        word[3] = first;

        return word;
    }
    //endregion

    //region Array Transformation Helper Classes
    private byte[][] convertArrayToState(byte[] arr) {
        byte[][] state = new byte[4][4];

        // Befuelt das State Array aus dem Data Byte Array
        for (int i = 0; i < 16; i++) {
            int x = i / 4;
            int y = i % 4;

            state[x][y] = arr[i];
        }

        return state;
    }

    private byte[] convertStateToByteArrray(byte[][] state) {
        if (state == null)
            return null;

        byte[] bytes = new byte[16];
        for (int i = 0; i < 16; i++) {
            int x = i / 4;
            int y = i % 4;

            bytes[i] = state[x][y];
        }

        return bytes;
    }

    private byte[][] copyStateArray(byte[][] state) {
        byte[][] newState = new byte[4][4];

        for (int i = 0; i < 4; i++) {
            newState[i] = state[i].clone();
        }

        return newState;
    }
    //endregion
}
