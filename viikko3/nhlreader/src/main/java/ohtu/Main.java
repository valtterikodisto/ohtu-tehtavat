package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {
  public static void main(String[] args) throws IOException {
    String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

    String bodyText = Request.Get(url).execute().returnContent().asString();

    Gson mapper = new Gson();
    Player[] players = mapper.fromJson(bodyText, Player[].class);

    System.out.println("Players from FIN\n");
    for (Player player : players) {
      if (player.getNationality().toUpperCase().equals("FIN")) {
        System.out.println(player.getName() + "\t" + player.getTeam() + "\t" + player.getGoals() + " + "
            + player.getAssists() + " = " + (player.getGoals() + player.getAssists()));
      }
    }
  }
}
