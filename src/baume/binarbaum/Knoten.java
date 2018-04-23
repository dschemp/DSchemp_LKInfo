package baume.binarbaum;

public class Knoten {

    public Knoten KnotenLinks;
    public Knoten KnotenRechts;
    public int KnotenInhalt;

    public Knoten(int knotenInhalt) {
        this.KnotenInhalt = knotenInhalt;
    }

    public Knoten(int knotenInhalt, Knoten knotenLinks, Knoten knotenRechts) {
        this.KnotenInhalt = knotenInhalt;
        this.KnotenLinks = knotenLinks;
        this.KnotenRechts = knotenRechts;
    }
}
