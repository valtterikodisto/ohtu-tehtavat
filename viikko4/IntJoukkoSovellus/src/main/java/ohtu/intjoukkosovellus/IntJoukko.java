
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5; // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono; // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");// heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");// heitin vaan jotain :D
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            lukujono[0] = luku;
            alkioidenLkm++;
            return true;
        } else {
        }
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukujono.length == 0) {
                int[] taulukkoOld = new int[lukujono.length];
                taulukkoOld = lukujono;
                kopioiTaulukko(lukujono, taulukkoOld);
                lukujono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, lukujono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                on++;
            }
        }
        if (on > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                kohta = i;
                lukujono[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = lukujono[j];
                lukujono[j] = lukujono[j + 1];
                lukujono[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukujono[i];
                tuotos += ", ";
            }
            tuotos += lukujono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko vasen, IntJoukko oikea) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = vasen.toIntArray();
        int[] bTaulu = oikea.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko vasen, IntJoukko oikea) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = vasen.toIntArray();
        int[] bTaulu = oikea.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko vasen, IntJoukko oikea) {
        IntJoukko joukkoErotus = new IntJoukko();
        int[] aTaulu = vasen.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            if (!oikea.kuuluu(aTaulu[i])) {
                joukkoErotus.lisaa(aTaulu[i]);
            }
        }
        return joukkoErotus;
    }

}
