package free.lunch.aDventure;

/**
 * Stores statistics information.
 */
public class StatisticsStorage {

    public int snakesKilled;
    public int dragonsKilled;
    public int wolfsKilled;
    public int horsesKilled;
    public int gamesLost;
    public int gamesWon;
    public int stagesCleared;

    public StatisticsStorage () {
        snakesKilled = 0;
        dragonsKilled = 0;
        wolfsKilled = 0;
        horsesKilled = 0;
        gamesLost = 0;
        gamesWon = 0;
        stagesCleared = 0;
    }
}
