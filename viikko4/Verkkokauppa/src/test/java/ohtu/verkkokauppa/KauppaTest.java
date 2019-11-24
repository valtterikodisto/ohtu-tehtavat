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

  @Test
  public void aloitaAsiointiNollaa() {
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
    k.aloitaAsiointi();
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 3);
  }

  public void pyydetaanUusiViiteJokaiseenMaksuun() {
    Pankki mockPankki = mock(Pankki.class);
    Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
    Varasto mockVarasto = mock(Varasto.class);

    when(mockViite.uusi()).thenReturn(42);
    when(mockVarasto.saldo(1)).thenReturn(10);
    when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(2, "voi", 3));

    Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.tilimaksu("pekka", "12345");

    // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
    // on kutsuttu kerran
    verify(mockViite, times(1)).uusi();

    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.tilimaksu("pekka", "12345");

    // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
    // on kutsuttu kaksi kertaa
    verify(mockViite, times(2)).uusi();

    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.tilimaksu("pekka", "12345");

    // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
    // on kutsuttu kolme kertaa
    verify(mockViite, times(3)).uusi();
  }

  @Test
  public void tuoteVoidaanPalauttaaVarastoon() {
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
    k.poistaKorista(2);

    verify(varasto).palautaVarastoon(new Tuote(2, "voi", 3));
  }
}