package ohtu.kivipaperisakset;

public class KPSTehdas {

  public static KPS kaksinpeli() {
    return new KPSPelaajaVsPelaaja();
  }

  public static KPS helppoYksinpeli() {
    return new KPSTekoaly();
  }

  public static KPS vaikeaYksinpeli() {
    return new KPSParempiTekoaly();
  }
}