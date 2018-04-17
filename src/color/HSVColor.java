package color;

public class HSVColor {

    private int
            H = 0;
    private float
            S = 0f,
            V = 0f;

    public HSVColor() { }

    public HSVColor(int h, float s, float v) {
        SetH(h);
        SetS(s);
        SetV(v);
    }

    public boolean SetH(int value) {
        if (value < 0 || value > 360) {
            return false;
        } else {
            this.H = value;
            return true;
        }
    }

    public boolean SetS(float value) {
        if (value < 0 || value > 1) {
            return false;
        } else {
            this.S = value;
            return true;
        }
    }

    public boolean SetV(float value) {
        if (value < 0 || value > 1) {
            return false;
        } else {
            this.V = value;
            return true;
        }
    }

    public int GetH() {
        return this.H;
    }

    public float GetS() {
        return this.S;
    }

    public float GetV() {
        return this.V;
    }


}
