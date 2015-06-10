package free.lunch.aDventure;

/**
 * Stores statistics information.
 */
public class StatisticsStorage {

    public int demonsKilled;
    public int snakesKilled;
    public int dragonsKilled;
    public int wolvesKilled;
    public int horsesKilled;
    public int gamesLost;
    public int stagesCleared;
    public int distanceWalked;

    public StatisticsStorage (int snakes, int dragons, int wolves, int horses, int lost, int stagesClear, int distance, int demons){
        snakesKilled = snakes;
        dragonsKilled = dragons;
        wolvesKilled = wolves;
        horsesKilled = horses;
        gamesLost = lost;
        stagesCleared = stagesClear;
        distanceWalked = distance;
        demonsKilled = demons;
    }

    public void addStatistics(StatisticsStorage add){
        snakesKilled = snakesKilled + add.snakesKilled;
        dragonsKilled = dragonsKilled + add.dragonsKilled;
        wolvesKilled = wolvesKilled + add.wolvesKilled;
        horsesKilled = horsesKilled + add.horsesKilled;
        gamesLost = gamesLost + add.gamesLost;
        stagesCleared = stagesCleared + add.stagesCleared;
        distanceWalked = distanceWalked + add.distanceWalked;
        demonsKilled = demonsKilled + add.demonsKilled;
    }

    public String toString(){
        return snakesKilled+"|"+dragonsKilled+"|"+wolvesKilled+"|"+horsesKilled+"|"+gamesLost+"|"+stagesCleared+"|"+distanceWalked+"|"+demonsKilled;
    }
}
