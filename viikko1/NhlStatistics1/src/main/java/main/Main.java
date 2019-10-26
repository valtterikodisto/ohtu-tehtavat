package main;

import ohtuesimerkki.Player;
import ohtuesimerkki.PlayerReader;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;

public class Main {
    public static void main(String[] args) {
        Reader reader = new PlayerReader("https://nhlstatisticsforohtu.herokuapp.com/players.txt");
        Statistics stats = new Statistics(reader);

        System.out.println("Philadelphia Flyers");
        for (Player player : stats.team("PHI")) {
            System.out.println(player);
        }

        System.out.println("");

        System.out.println("Top scorers");
        for (Player player : stats.topScorers(10)) {
            System.out.println(player);
        }
    }
}
