package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import ohtu.verkkokauppa.Kauppa;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KauppaTest {

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);

    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    // sitten testattava kauppa
    Kauppa k = new Kauppa(varasto, pankki, viite);

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }

  @Test
  public void tilisiirtoToimiiKahdellaEriTuotteella() {
    Pankki pankki = mock(Pankki.class);

    Viitegeneraattori viite = mock(Viitegeneraattori.class);

    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "voi", 3));

    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 8);
  }

  @Test
  public void tilisiirtoToimiiKahdellaSamallaTuotteella() {
    Pankki pankki = mock(Pankki.class);

    Viitegeneraattori viite = mock(Viitegeneraattori.class);

    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
  }

  @Test
  public void tilisiirtoToimiiLoppuneellaTuotteellaOikein() {
    Pankki pankki = mock(Pankki.class);

    Viitegeneraattori viite = mock(Viitegeneraattori.class);

    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.saldo(2)).thenReturn(0);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "voi", 3));

    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
  }
}