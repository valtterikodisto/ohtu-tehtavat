package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

  public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
    super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  public void suorita() {
    super.tallennaTila();

    int arvo = Integer.parseInt(this.syotekentta.getText());
    this.sovellus.plus(arvo);

    super.asetaTulos();
  }

}