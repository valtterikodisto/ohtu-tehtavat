package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSPeli implements KPS {

  private static final Scanner scanner = new Scanner(System.in);

  public void pelaa() {
    Tuomari tuomari = new Tuomari();

    String ekanSiirto = pyydaEkanSiirto();
    String tokanSiirto = pyydaTokanSiirto();

    while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
      tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
      System.out.println(tuomari);
      System.out.println();

      ekanSiirto = pyydaEkanSiirto();

      tokanSiirto = pyydaTokanSiirto();

      asetaSiirto(ekanSiirto);
    }

    System.out.println();
    System.out.println("Kiitos!");
    System.out.println(tuomari);
  }

  public String pyydaEkanSiirto() {
    System.out.print("Ensimmäisen pelaajan siirto: ");
    return scanner.nextLine();
  }

  public String pyydaTokanSiirto() {
    System.out.print("Toisen pelaajan siirto: ");
    return scanner.nextLine();
  }

  public void asetaSiirto(String ekanSiirto) {
    // Oletusarvoisesti ei tehdä mitään
  }

  protected static boolean onkoOkSiirto(String siirto) {
    return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
  }
}