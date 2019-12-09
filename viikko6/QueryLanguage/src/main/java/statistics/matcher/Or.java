package statistics.matcher;

import statistics.Player;
import statistics.matcher.Matcher;

public class Or implements Matcher {

  private Matcher[] matchers;

  public Or(Matcher... matchers) {
    this.matchers = matchers;
  }

  @Override
  public boolean matches(Player player) {
    for (Matcher matcher : matchers) {
      if (matcher.matches(player)) {
        return true;
      }
    }

    return false;
  }

}