package nullpunktbestimmung;

import java.util.function.Function;

public class Nullpunkt_Main {

    public static void main(String[] args) {
        // TODO code application logic here
        Function<Double, Double> funktion = (x) -> Math.pow(x, 2) - 2;
        double output = Halbierungsverfahren.FindeNullstelle2(0, 2, funktion);
        System.out.println(output);
    }

}
