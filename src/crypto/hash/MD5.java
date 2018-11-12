package crypto.hash;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MD5 {

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
        /*
         * Little Endian
         *
         * word A: 01 23 45 67
         * word B: 89 ab cd ef
         * word C: fe dc ba 98
         * word D: 76 54 32 10
         */
        int A = 0x67452301, B = 0xefcdab89, C = 0x98badcfe, D = 0x10325476;

        // TODO:
        /* Round 1 */
        /* Let [abcd k s i] denote the operation
            a = b + ((a + F(b,c,d) + X[k] + T[i]) <<< s). */
        /* Do the following 16 operations. */
        /*
            [ABCD 0 7 1]   [DABC 1 12 2]   [CDAB 2 17 3]   [BCDA 3 22 4]
            [ABCD 4 7 5]   [DABC 5 12 6]   [CDAB 6 17 7]   [BCDA 7 22 8]
            [ABCD 8 7 9]   [DABC 9 12 10]  [CDAB 10 17 11] [BCDA 11 22 12]
            [ABCD 12 7 13] [DABC 13 12 14] [CDAB 14 17 15] [BCDA 15 22 16]
        */

        /* Round 2. */
        /* Let [abcd k s i] denote the operation
            a = b + ((a + G(b,c,d) + X[k] + T[i]) <<< s). */
        /* Do the following 16 operations. */
        /*
            [ABCD 1 5 17]  [DABC 6 9 18]  [CDAB 11 14 19] [BCDA 0 20 20]
            [ABCD 5 5 21]  [DABC 10 9 22] [CDAB 15 14 23] [BCDA 4 20 24]
            [ABCD 9 5 25]  [DABC 14 9 26] [CDAB 3 14 27]  [BCDA 8 20 28]
            [ABCD 13 5 29] [DABC 2 9 30]  [CDAB 7 14 31]  [BCDA 12 20 32]
         */

        /* Round 3. */
        /* Let [abcd k s t] denote the operation
            a = b + ((a + H(b,c,d) + X[k] + T[i]) <<< s). */
        /* Do the following 16 operations. */
        /*
            [ABCD 5 4 33]  [DABC 8 11 34]  [CDAB 11 16 35] [BCDA 14 23 36]
            [ABCD 1 4 37]  [DABC 4 11 38]  [CDAB 7 16 39]  [BCDA 10 23 40]
            [ABCD 13 4 41] [DABC 0 11 42]  [CDAB 3 16 43]  [BCDA 6 23 44]
            [ABCD 9 4 45]  [DABC 12 11 46] [CDAB 15 16 47] [BCDA 2 23 48]
        */

        /* Round 4. */
        /* Let [abcd k s t] denote the operation
            a = b + ((a + I(b,c,d) + X[k] + T[i]) <<< s). */
        /* Do the following 16 operations. */
        /*
            [ABCD 0 6 49]  [DABC 7 10 50]  [CDAB 14 15 51] [BCDA 5 21 52]
            [ABCD 12 6 53] [DABC 3 10 54]  [CDAB 10 15 55] [BCDA 1 21 56]
            [ABCD 8 6 57]  [DABC 15 10 58] [CDAB 6 15 59]  [BCDA 13 21 60]
            [ABCD 4 6 61]  [DABC 11 10 62] [CDAB 2 15 63]  [BCDA 9 21 64]
        */

        throw new NotImplementedException();
    }

    //region Helper Methods
    public int rotationShiftLeft(int i, int n) {
        return (i << n) | (i >> 32 - n);
    }

    public int rotationShiftRight(int i, int n) {
        return (i >> n) | (i << 32 - n);
    }

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

        byte padByte = (byte)0x80;

        // The message is "padded" (extended) so that its length (in bits) is
        // congruent to 448 (56 bytes), modulo 512 (64 bytes).
        if (arr.length % 64 != 56) {
            throw new NotImplementedException();
        }
        else { // Don't pad
            return arr;
        }
    }

    private int doRoundOperation(int a, int b, int c, int d, int k, int s, int i, int round) {
        switch (round) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

        throw new NotImplementedException();
    }

    private int[] convertByteArrToIntArr(byte[] arr) {
        throw new NotImplementedException();
    }

    private byte[] convertIntArrToByteArr(int[] arr) {
        throw new NotImplementedException();
    }
    //endregion
}
