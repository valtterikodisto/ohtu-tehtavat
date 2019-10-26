package ohtu;

import java.util.Scanner;

public class KonsoliIO {

  private Scanner lukija;

  public KonsoliIO() {
    lukija = new Scanner(System.in);
  }

  public int nextInt() {
    return lukija.nextInt();
  }

  public void print(String m) {
    System.out.println(m);
  }
}