package crypto.hash;

public class MD5 {
    /*
     * Little Endian
     *
     * word A: 01 23 45 67
     * word B: 89 ab cd ef
     * word C: fe dc ba 98
     * word D: 76 54 32 10
     */
    private int
            A = 0x67452301,
            B = 0xefcdab89,
            C = 0x98badcfe,
            D = 0x10325476;

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

    //region Helper Methods
    public int rotationShiftLeft(int i, int n) {
        return (i << n) | (i >> 32 - n);
    }

    public int rotationShiftRight(int i, int n) {
        return (i >> n) | (i << 32 - n);
    }
    //endregion
}
