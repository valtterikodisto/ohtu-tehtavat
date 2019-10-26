package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

  Reader readerStub = new Reader() {

    public List<Player> getPlayers() {
      ArrayList<Player> players = new ArrayList<>();

      players.add(new Player("Semenko", "EDM", 4, 12));
      players.add(new Player("Lemieux", "PIT", 45, 54));
      players.add(new Player("Kurri", "EDM", 37, 53));
      players.add(new Player("Yzerman", "DET", 42, 56));
      players.add(new Player("Gretzky", "EDM", 35, 89));

      return players;
    }
  };

  Statistics stats;

  @Before
  public void setUp() {
    stats = new Statistics(readerStub);
  }

  @Test
  public void playerSearchReturnsNullIfNotFound() {
    Player player = stats.search("Undefined");
    assertEquals(null, player);
  }

  @Test
  public void playerSearchReturnsCorrectPlayer() {
    Player player = stats.search("Semenko");
    assertEquals(new Player("Semenko", "EDM", 4, 12), player);
  }

  @Test
  public void teamReturnsCorrectPlayers() {
    ArrayList<Player> team = new ArrayList<>();

    team.add(new Player("Semenko", "EDM", 4, 12));
    team.add(new Player("Kurri", "EDM", 37, 53));
    team.add(new Player("Gretzky", "EDM", 35, 89));

    assertEquals(team, stats.team("EDM"));
  }

  @Test
  public void topScorersReturnsCorrectPlayers() {

    Player lemieux = new Player("Lemieux", "PIT", 45, 54);
    Player gretzky = new Player("Gretzky", "EDM", 35, 89);

    List<Player> topScorers = stats.topScorers(2);
    assertEquals(true, topScorers.contains(lemieux) && topScorers.contains(gretzky));
  }
}
