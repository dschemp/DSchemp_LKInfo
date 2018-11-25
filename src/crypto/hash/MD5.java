package crypto.hash;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static misc.BitManipulation.*;

public class MD5 {

    /* Global variables */
    /**
     * Note that from the documentation and the code, there is NO offset.
     * The 1 in the docs equal a 1 in code
     */
    private int[] T = new int[]{
            0x0, // offset word
            0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
            0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
            0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
            0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
            0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
            0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
            0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
            0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
            0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
            0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
            0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
            0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
            0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
            0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
            0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
            0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391
    };

    /*
    public MD5() {

        T = new int[65} { 0x0 };
        for (int i = 0; i < T.length; i++) {
            int num = (int) (4294967296L * Math.abs(Math.sin(i + 1)));
            T[i] = num;
        }

    }
    */

    public static byte[] Hash(byte[] arr) {
        return new MD5().hash(arr);
    }

    //region Bit Manipulation Methods
    /*
     * F(X,Y,Z) = XY v not(X) Z
     * G(X,Y,Z) = XZ v Y not(Z)
     * H(X,Y,Z) = X xor Y xor Z
     * I(X,Y,Z) = Y xor (X v not(Z))
     */
    private int F(int x, int y, int z)
    {
        return (x & y | ~x & z);
    }

    private int G(int x, int y, int z)
    {
        return (x & z | y & ~z);
    }

    private int H(int x, int y, int z)
    {
        return (x ^ y ^ z);
    }

    private int I(int x, int y, int z)
    {
        return (y ^ (x & ~z));
    }
    //endregion

    /*
     * Output:
       The message digest produced as output is A, B, C, D. That is, we
       begin with the low-order byte of A, and end with the high-order byte
       of D.
       This completes the description of MD5. A reference implementation in
       C is given in the appendix.
     */

    public byte[] hash(byte[] arr) {
        arr = pad(arr);

        int[] hashArr = convertByteArrToIntArr(arr);
        int[] hashedValue = hash(hashArr);
        byte[] hashValueArr = convertIntArrToByteArr(hashedValue);

        return hashValueArr;
    }

    private int[] hash(int[] arr) {


        /* Process each 16-word block */
        for (int i = 0; i <= (arr.length / 16) - 1; i++) {

            int[] X = new int[16];
            /* Copy block i into X */
            for (int j = 0; j < X.length; j++) {

            }

            /*
             * Little Endian
             *
             * word A: 01 23 45 67
             * word B: 89 ab cd ef
             * word C: fe dc ba 98
             * word D: 76 54 32 10
             */
            int A = 0x67452301,
                B = 0xEFCDAB89,
                C = 0x98BADCFE,
                D = 0x10325476;

            // TODO:
            /* Round 1 */
            /* Let [abcd k s i] denote the operation
            a = b + ((a + F(b,c,d) + X[k] + T[i]) <<< s). */
            /* Do the following 16 operations. */
            A = performRoundOperation(A, B, C, D, X[0], 7, 1, 1);
            D = performRoundOperation(D, A, B, C, X[1], 12, 2, 1);
            C = performRoundOperation(C, D, A, B, X[2], 17, 3, 1);
            B = performRoundOperation(B, C, D, A, X[3], 22, 4, 1);

            A = performRoundOperation(A, B, C, D, X[4], 7, 5, 1);
            D = performRoundOperation(D, A, B, C, X[5], 12, 6, 1);
            C = performRoundOperation(C, D, A, B, X[6], 17, 7, 1);
            B = performRoundOperation(B, C, D, A, X[7], 22, 8, 1);

            A = performRoundOperation(A, B, C, D, X[8], 7, 9, 1);
            D = performRoundOperation(D, A, B, C, X[9], 12, 10, 1);
            C = performRoundOperation(C, D, A, B, X[10], 17, 11, 1);
            B = performRoundOperation(B, C, D, A, X[11], 22, 12, 1);

            A = performRoundOperation(A, B, C, D, X[12], 7, 13, 1);
            D = performRoundOperation(D, A, B, C, X[13], 12, 14, 1);
            C = performRoundOperation(C, D, A, B, X[14], 17, 15, 1);
            B = performRoundOperation(B, C, D, A, X[15], 22, 16, 1);

            /* Round 2. */
            /* Let [abcd k s i] denote the operation
                a = b + ((a + G(b,c,d) + X[k] + T[i]) <<< s). */
            /* Do the following 16 operations. */
            A = performRoundOperation(A, B, C, D, X[1], 5, 17, 2);
            D = performRoundOperation(D, A, B, C, X[6], 9, 18, 2);
            C = performRoundOperation(C, D, A, B, X[11], 14, 19, 2);
            B = performRoundOperation(B, C, D, A, X[0], 20, 20, 2);

            A = performRoundOperation(A, B, C, D, X[5], 5, 21, 2);
            D = performRoundOperation(D, A, B, C, X[10], 9, 22, 2);
            C = performRoundOperation(C, D, A, B, X[15], 14, 23, 2);
            B = performRoundOperation(B, C, D, A, X[4], 20, 24, 2);

            A = performRoundOperation(A, B, C, D, X[9], 5, 25, 2);
            D = performRoundOperation(D, A, B, C, X[14], 9, 26, 2);
            C = performRoundOperation(C, D, A, B, X[3], 14, 27, 2);
            B = performRoundOperation(B, C, D, A, X[8], 20, 28, 2);

            A = performRoundOperation(A, B, C, D, X[13], 5, 29, 2);
            D = performRoundOperation(D, A, B, C, X[2], 9, 30, 2);
            C = performRoundOperation(C, D, A, B, X[7], 14, 31, 2);
            B = performRoundOperation(B, C, D, A, X[12], 20, 32, 2);

            /* Round 3. */
            /* Let [abcd k s t] denote the operation
                a = b + ((a + H(b,c,d) + X[k] + T[i]) <<< s). */
            /* Do the following 16 operations. */
            A = performRoundOperation(A, B, C, D, X[5], 4, 33, 3);
            D = performRoundOperation(D, A, B, C, X[8], 11, 34, 3);
            C = performRoundOperation(C, D, A, B, X[11], 16, 35, 3);
            B = performRoundOperation(B, C, D, A, X[14], 23, 36, 3);

            A = performRoundOperation(A, B, C, D, X[1], 4, 37, 3);
            D = performRoundOperation(D, A, B, C, X[4], 11, 38, 3);
            C = performRoundOperation(C, D, A, B, X[7], 16, 39, 3);
            B = performRoundOperation(B, C, D, A, X[10], 23, 40, 3);

            A = performRoundOperation(A, B, C, D, X[13], 4, 41, 3);
            D = performRoundOperation(D, A, B, C, X[0], 11, 42, 3);
            C = performRoundOperation(C, D, A, B, X[3], 16, 43, 3);
            B = performRoundOperation(B, C, D, A, X[6], 23, 44, 3);

            A = performRoundOperation(A, B, C, D, X[9], 4, 45, 3);
            D = performRoundOperation(D, A, B, C, X[12], 11, 46, 3);
            C = performRoundOperation(C, D, A, B, X[15], 16, 47, 3);
            B = performRoundOperation(B, C, D, A, X[2], 23, 48, 3);

            /* Round 4. */
            /* Let [abcd k s t] denote the operation
                a = b + ((a + I(b,c,d) + X[k] + T[i]) <<< s). */
            /* Do the following 16 operations. */
            A = performRoundOperation(A, B, C, D, X[0], 6, 49, 4);
            D = performRoundOperation(D, A, B, C, X[7], 10, 50, 4);
            C = performRoundOperation(C, D, A, B, X[14], 15, 51, 4);
            B = performRoundOperation(B, C, D, A, X[5], 21, 52, 4);

            A = performRoundOperation(A, B, C, D, X[12], 6, 53, 4);
            D = performRoundOperation(D, A, B, C, X[3], 10, 54, 4);
            C = performRoundOperation(C, D, A, B, X[10], 15, 55, 4);
            B = performRoundOperation(B, C, D, A, X[1], 21, 56, 4);

            A = performRoundOperation(A, B, C, D, X[8], 6, 57, 4);
            D = performRoundOperation(D, A, B, C, X[15], 10, 58, 4);
            C = performRoundOperation(C, D, A, B, X[6], 15, 59, 4);
            B = performRoundOperation(B, C, D, A, X[13], 21, 60, 4);

            A = performRoundOperation(A, B, C, D, X[4], 6, 61, 4);
            D = performRoundOperation(D, A, B, C, X[11], 10, 62, 4);
            C = performRoundOperation(C, D, A, B, X[2], 15, 63, 4);
            B = performRoundOperation(B, C, D, A, X[9], 21, 64, 4);



        }

        throw new NotImplementedException();
    }

    //region Helper Methods

    /* --- For Bit Manipulation: see misc.BitManipulation  */

    private byte[] pad(byte[] arr) {
        /*
            The message is "padded" (extended) so that its length (in bits) is
            congruent to 448, modulo 512. That is, the message is extended so
            that it is just 64 bits shy of being a multiple of 512 bits long.
            Padding is always performed, even if the length of the message is
            already congruent to 448, modulo 512.

            Padding is performed as follows: a single "1" bit is appended to the
            message, and then "0" bits are appended so that the length in bits of
            the padded message becomes congruent to 448, modulo 512. In all, at
            least one bit and at most 512 bits are appended.
        */

        // The message is "padded" (extended) so that its length (in bits) is
        // congruent to 448 (56 bytes), modulo 512 (64 bytes).
        if (arr.length % 64 != 56) {
            int newArraySize = calcNewArrayLength(arr.length);
            byte[] newPaddedArr = new byte[newArraySize];

            // copy already existing data into new padded array
            System.arraycopy(arr, 0, newPaddedArr, 0, arr.length);

            byte padByte = (byte)0x80; // first padding byte
            for (int i = arr.length; i < newPaddedArr.length; i++) {
                newPaddedArr[i] = padByte;  // pad first byte with 0x80
                padByte = (byte)0x00;       // then set padbyte permanently to 0x00
            }

            return newPaddedArr;
        }
        else { // Don't pad
            return arr;
        }
    }

    private int performRoundOperation(int a, int b, int c, int d, int x, int s, int t, int round) {
        switch (round) {
            /* Round 1 */
            /* Let [abcd k s i] denote the operation
                a = b + ((a + F(b,c,d) + X[k] + T[i]) <<< s). */
            case 1:
                a += F(b, c, d) + x + T[t];
                a = rotationShiftLeft(a, s);
                a += b;
                break;

            /* Round 2. */
            /* Let [abcd k s i] denote the operation
                a = b + ((a + G(b,c,d) + X[k] + T[i]) <<< s). */
            case 2:
                a += G(b, c, d) + x + T[t];
                a = rotationShiftLeft(a, s);
                a += b;
                break;

            /* Round 3. */
            /* Let [abcd k s t] denote the operation
                a = b + ((a + H(b,c,d) + X[k] + T[i]) <<< s). */
            case 3:
                a += H(b, c, d) + x + T[t];
                a = rotationShiftLeft(a, s);
                a += b;
                break;

            /* Round 4. */
            /* Let [abcd k s t] denote the operation
                a = b + ((a + I(b,c,d) + X[k] + T[i]) <<< s). */
            case 4:
                a += I(b, c, d) + x + T[t];
                a = rotationShiftLeft(a, s);
                a += b;
                break;
        }

        return a;
    }

    private int[] convertByteArrToIntArr(byte[] arr) {
        throw new NotImplementedException();
    }

    private byte[] convertIntArrToByteArr(int[] arr) {
        throw new NotImplementedException();
    }

    private int calcNewArrayLength(int length) {
        while (length % 64 != 56) {
            length++;
        }
        return length;
    }
    //endregion
}
