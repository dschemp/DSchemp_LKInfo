package color;

public class Color_Main {

    public static void main(String[] args) {
        RGBColor rgb = new RGBColor((byte)20, (byte)100, (byte)120);
        HSVColor hsv = ColorConverter.RGBtoHSV(rgb);
        System.out.print("");
    }

}
