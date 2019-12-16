package ohtu.kivipaperisakset;

public class KPSParempiTekoaly extends KPSPeli {

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly() {
        this.tekoaly = new TekoalyParannettu(20);
    }

    @Override
    public String pyydaTokanSiirto() {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }

    @Override
    public void asetaSiirto(String ekanSiirto) {
        tekoaly.asetaSiirto(ekanSiirto);
    }
}
