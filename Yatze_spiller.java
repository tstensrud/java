public class Yatze_spiller {
    private String name;
    private int playerId;
    private int points;

    Yatze_spiller(String name, int playerId) {
        this.name = name;
        this.points = 0;
        this.playerId = playerId;
    }
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    public int getPlayerId() {
        return playerId;
    }
    public void addPoints(int points) {
        this.points += points;
    }
}
