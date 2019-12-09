package statistics;

import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

import java.util.ArrayList;

public class QueryBuilder {

  ArrayList<Matcher> matchers = new ArrayList<>();

  Matcher build() {
    Matcher[] matcherArray = matchers.toArray(new Matcher[matchers.size()])
    Matcher and = new And(matcherArray);
    this.matchers.clear();

    return and;
  }

  QueryBuilder hasAtLeast(int value, String goals) {
    matchers.add(new HasAtLeast(value, goals));
    return this;
  }

  QueryBuilder hasFewerThan(int value, String goals) {
    matchers.add(new HasFewerThan(value, goals));
    return this;
  }

  QueryBuilder oneOf(Matcher... matchers) {
    this.matchers.add(new Or(matchers));
    return this;
  }

  QueryBuilder playsIn(String team) {
    matchers.add(new PlaysIn(team));
    return this;
  }
}