package color;

public class RGBColor {

    private byte
            R = 0,
            G = 0,
            B = 0;

    public RGBColor() { }

    public RGBColor(byte r, byte g, byte b) {
        SetR(r);
        SetG(g);
        SetB(b);
    }

    public float GetRelativeLuminance() {
        return 0.2126f * GetR() + 0.7152f * GetG() + 0.0722f * GetB();
    }

    public boolean SetR(byte value) {
        this.R = value;
        return true;
    }

    public boolean SetG(byte value) {
        this.G = value;
        return true;
    }

    public boolean SetB(byte value) {
        this.B = value;
        return true;
    }

    public byte GetR() {
        return this.R;
    }

    public byte GetG() {
        return this.G;
    }

    public byte GetB() {
        return this.B;
    }

}
