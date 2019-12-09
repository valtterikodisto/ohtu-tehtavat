package statistics.matcher;

import statistics.Player;
import statistics.matcher.Matcher;

public class HasFewerThan implements Matcher {

  Matcher hasAtLeast;
  Matcher not;

  public HasFewerThan(int value, String goals) {
    this.not = new Not(new HasAtLeast(value, goals));
  }

  @Override
  public boolean matches(Player p) {
    return this.not.matches(p);
  }

}