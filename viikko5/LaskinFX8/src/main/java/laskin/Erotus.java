package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

  public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
    super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  public void suorita() {
    super.tallennaTila();
    int arvo = Integer.parseInt(this.syotekentta.getText());
    this.sovellus.miinus(arvo);
    super.asetaTulos();
  }

}