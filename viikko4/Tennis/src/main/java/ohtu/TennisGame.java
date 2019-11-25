package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name)
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public static String pointsToTennisPoints(int points) {
        String[] tennisPoints = { "Love", "Fifteen", "Thirty", "Forty" };
        if (points > 3) {
            return "";
        }

        return tennisPoints[points];
    }

    public String getScore() {
        String player1Score = pointsToTennisPoints(m_score1);
        String player2Score = pointsToTennisPoints(m_score2);

        String score = "";

        if (m_score1 >= 4 || m_score2 >= 4) {
            int minusResult = m_score1 - m_score2;

            if (minusResult == 0) {
                score = "Deuce";
            } else if (minusResult == 1)
                score = "Advantage " + player1Name;
            else if (minusResult == -1)
                score = "Advantage " + player2Name;
            else if (minusResult >= 2)
                score = "Win for player1";
            else
                score = "Win for player2";
        } else if (player1Score.equals(player2Score)) {
            score = player1Score + "-All";
        } else {
            score = player1Score + "-" + player2Score;
        }

        return score;
    }
}