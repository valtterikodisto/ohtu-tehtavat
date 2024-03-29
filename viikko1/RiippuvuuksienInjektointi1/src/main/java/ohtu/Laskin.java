package ohtu;

public class Laskin {

    private IO io;

    public Laskin(IO io) {
        this.io = io;
    }

    public void suorita() {
        while (true) {
            System.out.println("luku 1: ");
            int luku1 = io.nextInt();
            if (luku1 == -9999)
                return;

            System.out.println("luku 2: ");
            int luku2 = io.nextInt();
            if (luku2 == -9999)
                return;

            int vastaus = laskeSumma(luku1, luku2);
            System.out.println("summa: " + vastaus);
        }
    }

    private int laskeSumma(int luku1, int luku2) {
        return luku1 + luku2;
    }

}
