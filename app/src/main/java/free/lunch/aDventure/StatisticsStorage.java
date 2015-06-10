package free.lunch.aDventure;

/**
 * Stores statistics information.
 */
public class StatisticsStorage {

    public int demonsKilled;
    public int snakesKilled;
    public int ratsKilled;
    public int wolvesKilled;
    public int horsesKilled;
    public int gamesLost;
    public int stagesCleared;
    public int distanceWalked;

    public StatisticsStorage (int snakes, int rats, int wolves, int horses, int lost, int stagesClear, int distance, int demons){
        snakesKilled = snakes;
        ratsKilled = rats;
        wolvesKilled = wolves;
        horsesKilled = horses;
        gamesLost = lost;
        stagesCleared = stagesClear;
        distanceWalked = distance;
        demonsKilled = demons;
    }

    public void addStatistics(StatisticsStorage add){
        snakesKilled = snakesKilled + add.snakesKilled;
        ratsKilled = ratsKilled + add.ratsKilled;
        wolvesKilled = wolvesKilled + add.wolvesKilled;
        horsesKilled = horsesKilled + add.horsesKilled;
        gamesLost = gamesLost + add.gamesLost;
        stagesCleared = stagesCleared + add.stagesCleared;
        distanceWalked = distanceWalked + add.distanceWalked;
        demonsKilled = demonsKilled + add.demonsKilled;
    }

    public String toString(){
        return snakesKilled+"|"+ ratsKilled +"|"+wolvesKilled+"|"+horsesKilled+"|"+gamesLost+"|"+stagesCleared+"|"+distanceWalked+"|"+demonsKilled;
    }
}
