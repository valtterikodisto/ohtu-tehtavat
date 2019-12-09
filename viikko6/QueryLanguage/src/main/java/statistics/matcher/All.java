package statistics.matcher;

import statistics.Player;

public class All implements Matcher {

  @Override
  public boolean matches(Player player) {
    return true;
  }

}