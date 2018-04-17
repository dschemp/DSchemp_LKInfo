package nullpunktbestimmung;

import java.util.function.Function;

public class NewtonscheNaeherungsformel {
    
    public static double NullstelleBestimmen(double startwert, Function<Double, Double> funktion, Function<Double, Double> ersteAbleitungDerFunktion, double accuracy) {
        // Funktionswert vom Startwert
        double funktionsWert = funktion.apply(startwert);
        // Funktionswert der ersten Ableitung vom Startwert
        double funktionsWertDerErstenAbleitung = ersteAbleitungDerFunktion.apply(startwert);
        
        // Ergebnis nach Newtonsche Formel
        double ergebnis = startwert - (funktionsWert / funktionsWertDerErstenAbleitung);
        
        // Wenn das Ergebnis im Bereich +/- accuracy, dann gebe den Wert zurück
        if (funktion.apply(ergebnis) < 0 + accuracy && funktion.apply(ergebnis) > 0 - accuracy)
            return ergebnis;
        
        return NullstelleBestimmen(ergebnis, funktion, ersteAbleitungDerFunktion, accuracy);
    }
    
}
