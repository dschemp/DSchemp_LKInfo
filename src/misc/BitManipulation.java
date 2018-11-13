package misc;

public class BitManipulation {

    public int rotationShiftLeft(int i, int n) {
        return (i << n) | (i >> 32 - n);
    }

    public int rotationShiftRight(int i, int n) {
        return (i >> n) | (i << 32 - n);
    }

    public byte rotationShiftLeft(byte i, int n) {
        return (byte) ((i << n) | (i >> 8 - n));
    }

    public byte rotationShiftRight(byte i, int n) {
        return (byte) ((i >> n) | (i << 8 - n));
    }

}
