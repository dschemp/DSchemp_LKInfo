package recursion.backtracking.sudoku;

import java.util.ArrayList;

public class Sudoku_Feld {

    public int[][] feld;

    /**
     * @param inputFeld int[9][9] Array (jede Zahl nur von 0 bis 9, 0 = leer)
     */
    public Sudoku_Feld(int[][] inputFeld) {
        this.feld = inputFeld;
    }

    /**
     * @param x Start X Wert (Standard = 0)
     * @param y Start Y Wert (Standard = 0)
     * @param ursprungsFeld Falls Zahl nicht passt, zurückkehren (Standard = _Sudoku_Feld_.feld)
     * @return Boolean, ob Sudoku Lösen erfolgreich
     */
    public boolean FeldLoesen(int x, int y, int[][] ursprungsFeld) {
        // neue Koordinaten bestimmen
        int newX = x;
        int newY = y;
        if (y == 8) { // Wenn Y schon am Ende des Feldes
            newX++; // X (Spalte) um 1 erhöhen
            newY = 0; // Y wieder von oben
        } else if (y < 8) // Wenn Y noch nicht am Ende
            newY++; // Y um 1 erhöhen

        if (newX == 9) // Schlussendlich, wenn durch, true zurückgeben
            return true;

        if (feld[x][y] == 0) { // Wenn Feld nicht bereits besetzt ist, dann mit Zahl einsetzen beginnen
            int zahl = 1; // Start bei 1
            while (true) { // Endlos-Schleife
                // Durchzählen bis passende Zahl gefunden
                if (zahl >= 10) // Passt keine Zahl in das Feld, wird false zurückgegeben, und die vorherige Zelle neu evaluiert
                    return false;

                if (ZelleLoesen(x, y, zahl)) { // Wenn Zahl in Zelle den Regeln entsprechend reinpasst
                    feld[x][y] = zahl; // Zelle mit Zahl füllen

                    // TODO: Reset Feld wenn feldGeloest false ist
                    boolean feldGeloest = FeldLoesen(newX, newY, feld); // Schauen, ob es damit weiter geht
                    if (feldGeloest) // Wenn Feld komplett besetzt, dann raus aus der Schleife
                        break;
                    else // Wenn nicht, Feld auf ursprüngliches Feld reseten und neu versuchen
                        feld = ursprungsFeld;
                }
                zahl++;
            }
        }
        return FeldLoesen(newX, newY, feld); // Wenn Zelle bereits besetzt, nächste Zelle machen
    }

    private boolean ZelleLoesen(int x, int y, int zahl) {
        // Zeilen durchgehen
        for (int ySpalte = 0; ySpalte < 9; ySpalte++) {
            if (feld[x][ySpalte] == zahl)
                return false;
        }

        // Spalten durchgehen
        for (int xSpalte = 0; xSpalte < 9; xSpalte++) {
            if (feld[xSpalte][y] == zahl)
                return false;
        }

        // 3x3 Felder durchgehen // TODO: Verbessern nur Feld welches bearbeitet wurde
        return  Check3x3(0, 2, 0, 2, zahl) && // oben links
                Check3x3(3, 5, 0, 2, zahl) && // oben mitte
                Check3x3(6, 8, 0, 2, zahl) && // oben rechts

                Check3x3(0, 2, 3, 5, zahl) && // mitte links
                Check3x3(3, 5, 3, 5, zahl) && // mitte mitte
                Check3x3(6, 8, 3, 5, zahl) && // mitte rechts

                Check3x3(0, 2, 6, 8, zahl) && // unten links
                Check3x3(3, 5, 6, 8, zahl) && // unten mitte
                Check3x3(6, 8, 6, 8, zahl);   // unten rechts
    }


    @Override
    public String toString() {
        String text = "";
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                text += (feld[x][y] + " ");
                if (y == 2 || y == 5)
                    text += ('\t');
            }
            text += '\n';
            if (x == 2 || x == 5)
                text += '\n';
        }
        return text;
    }

    private boolean Check3x3(int x1, int x2, int y1, int y2, int zahl) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                if (feld[x][y] == zahl)
                    return false;
            }
        }
        return true;
    }

}
