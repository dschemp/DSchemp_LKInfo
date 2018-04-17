package color;

public class ColorConverter {

    public static RGBColor HSVtoRGB(HSVColor hsv) {
        RGBColor rgb;

        int hi = (int) Math.floor(hsv.GetH() / 60f);
        float f = (hsv.GetH() / 60f) - hi;

        float p = hsv.GetV() * (1 - hsv.GetS());
        float q = hsv.GetV() * (1 - hsv.GetS() * f);
        float t = hsv.GetV() * (1 - hsv.GetS() * (1 - f));

        float r, g, b;

        switch (hi) {

            case 1:
                r = q;
                g = hsv.GetV();
                b = p;
                break;

            case 2:
                r = p;
                g = hsv.GetV();
                b = t;
                break;

            case 3:
                r = p;
                g = q;
                b = hsv.GetV();
                break;

            case 4:
                r = t;
                g = p;
                b = hsv.GetV();
                break;

            case 5:
                r = hsv.GetV();
                g = p;
                b = q;
                break;

            default:
                r = hsv.GetV();
                g = t;
                b = p;
                break;

        }

        rgb = new RGBColor((byte) (r*255), (byte) (g*255), (byte) (b*255));

        return rgb;
    }

    public static HSVColor RGBtoHSV(RGBColor rgb) {
        HSVColor hsv = new HSVColor();

        float h = 0f,
                s = 0f;

        float r = rgb.GetR() / 255f;
        float g = rgb.GetG() / 255f;
        float b = rgb.GetB() / 255f;

        float max = Math.max(Math.max(r, g), b);
        float min = Math.min(Math.min(r, g), b);

        // Calculate H
        if (min == max) h = 0;
        else if (r == max) h = 60 * ((g - b) / (max - min));
        else if (g == max) h = 60 * (2 + (b - r) / (max - min));
        else if (b == max) h = 60 * (4 + (r - g) / (max - min));

        if (h < 0) h = h + 360;
        hsv.SetH((int)h);

        // Calculate S
        if (max == 0) s = 0;
        else s = ((max - min) / max);

        hsv.SetS(s);

        // Set V
        hsv.SetV(max);

        return hsv;
    }

}
