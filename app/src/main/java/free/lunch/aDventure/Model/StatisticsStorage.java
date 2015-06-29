package free.lunch.aDventure.Model;

/**
 * Stores statistics information.
 */
public class StatisticsStorage {

    public int dragonsKilled;
    public int snakesKilled;
    public int ratsKilled;
    public int wolvesKilled;
    public int horsesKilled;
    public int gamesLost;
    public int stagesCleared;
    public int distanceWalked;

    public StatisticsStorage (int snakes, int rats, int wolves, int horses, int lost, int stagesClear, int distance, int dragons){
        snakesKilled = snakes;
        ratsKilled = rats;
        wolvesKilled = wolves;
        horsesKilled = horses;
        gamesLost = lost;
        stagesCleared = stagesClear;
        distanceWalked = distance;
        dragonsKilled = dragons;
    }

    public void addStatistics(StatisticsStorage add){
        snakesKilled = snakesKilled + add.snakesKilled;
        ratsKilled = ratsKilled + add.ratsKilled;
        wolvesKilled = wolvesKilled + add.wolvesKilled;
        horsesKilled = horsesKilled + add.horsesKilled;
        gamesLost = gamesLost + add.gamesLost;
        stagesCleared = stagesCleared + add.stagesCleared;
        distanceWalked = distanceWalked + add.distanceWalked;
        dragonsKilled = dragonsKilled + add.dragonsKilled;
    }

    public String toString(){
        return snakesKilled+"|"+ ratsKilled +"|"+wolvesKilled+"|"+horsesKilled+"|"+gamesLost+"|"+stagesCleared+"|"+distanceWalked+"|"+ dragonsKilled;
    }
}
