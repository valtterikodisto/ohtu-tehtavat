package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Komento {
  protected TextField tuloskentta;
  protected TextField syotekentta;
  protected Button nollaa;
  protected Button undo;
  protected Sovelluslogiikka sovellus;

  protected TextField edellinenTuloskentta;
  protected TextField edellinenSyotekentta;

  public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
    this.tuloskentta = tuloskentta;
    this.syotekentta = syotekentta;
    this.nollaa = nollaa;
    this.undo = undo;
    this.sovellus = sovellus;
    this.edellinenSyotekentta = new TextField();
    this.edellinenTuloskentta = new TextField();
  }

  protected void asetaTulos() {
    int laskunTulos = this.sovellus.tulos();

    this.syotekentta.setText("");
    this.tuloskentta.setText("" + laskunTulos);

    if (laskunTulos == 0) {
      nollaa.disableProperty().set(true);
    } else {
      nollaa.disableProperty().set(false);
    }
    undo.disableProperty().set(false);
  }

  protected void tallennaTila() {
    this.edellinenSyotekentta.setText(this.syotekentta.getText());
    this.edellinenTuloskentta.setText(this.tuloskentta.getText());
  }

  public void suorita() {

  }

  public void peru() {
    if (this.edellinenSyotekentta != null && this.edellinenTuloskentta != null) {
      this.syotekentta.setText(this.edellinenSyotekentta.getText());
      this.tuloskentta.setText(this.edellinenTuloskentta.getText());

      this.edellinenSyotekentta.setText(null);
      this.edellinenTuloskentta.setText(null);
    }
  }

}