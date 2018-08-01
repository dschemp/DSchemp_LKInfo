package nullpunktbestimmung;

import java.util.function.Function;

public class Halbierungsverfahren {

    public static double FindeNullstelle(double startWert, double endWert, Function<Double, Double> funktion, double accuracy, int maxCalcs) {
        // Anzahl Halbierungen
        int calcs = 0;
        // Mittelwert Start-Ende
        double middleX = (startWert + endWert) / 2.0;
        // Funktionswert für den Mittelwert
        double middleY = funktion.apply(middleX);
        
        // Solange der Funktionswert des Mittelwerts nicht innerhalb des Genauigkeitbereiches ist 
        // und die max. Anzahl an Halbierungen erreicht ist, weiter hälften
        while ((middleY > accuracy || middleY < - accuracy) && calcs < maxCalcs) {
            // Neuer Mittelwert
            middleX = (startWert + endWert) / 2.0;
            // Mittelwerts Funktionwert
            middleY = funktion.apply(middleX);
            
            if (middleY < 0) // Wenn der Funktionswert negativ ist
                startWert = middleX; // Start an den Mittelwert
            if (middleY >= 0) // Wenn der Funktionswert positiv ist
                endWert = middleX; // Ende an den Mittelwert
            
            calcs++; // Anzahl an Halbierungen um eins erhöhen
        }

        return middleX; // Nullstelle (Mittelwert) zurückgeben
    }

    public static double FindeNullstelle(double startWert, double endWert, Function<Double, Double> funktion) {
        return FindeNullstelle(startWert, endWert, funktion, 1e-3, (int)1e6);
    }
    
    public static double FindeNullstelle2(double startWert, double endWert, Function<Double, Double> funktion, double accuracy, int maxCalcs) {
        // Anzahl an Halbierungen
        int calcs = 0;
        // Mittelwert für den Intervall Start-Ende
        double middleX = (startWert + endWert) / 2.0;
        
        // Solange |start-ende| größer als accuracy ist und die max Anzahl an Halbierungen nicht erreicht worden ist
        while ((startWert - endWert > accuracy || startWert - endWert < -accuracy) && calcs <= maxCalcs)
        {
            // Neuen Mittelwert ausrechnen
            middleX = (startWert + endWert) / 2.0;
            
            // wenn f(start) * f(ende) kleiner als 0 ist (negativ), Mittelwert neues Ende
            if (funktion.apply(startWert) * funktion.apply(endWert) < 0)
                endWert = middleX;
            else // wenn nicht (positiv), Mittelwert neuer Anfang
                startWert = middleX;
            
            calcs++; // Anzahl an Halbierungen um eins erhöhen
        }

        return middleX; // Nullstelle (Mittelwert) zurückgeben
    }

    public static double FindeNullstelle2(double startWert, double endWert, Function<Double, Double> funktion) {
        return FindeNullstelle2(startWert, endWert, funktion, 1e-3, (int)1e6);
    }
}
