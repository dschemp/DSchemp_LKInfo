package misc;

public class BitManipulation {

    public static int rotationShiftLeft(int i, int n) {
        return (i << n) | (i >> 32 - n);
    }

    public static int rotationShiftRight(int i, int n) {
        return (i >> n) | (i << 32 - n);
    }

    public static byte rotationShiftLeft(byte i, int n) {
        return (byte) ((i << n) | (i >> 8 - n));
    }

    public static byte rotationShiftRight(byte i, int n) {
        return (byte) ((i >> n) | (i << 8 - n));
    }

}
