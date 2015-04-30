package tk.slicesofcheese.the_best_application_ever.Model;

import java.util.LinkedList;

import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Player;

/**
 * Created by jonathan on 30-4-15.
 */
public class Level {

    private int xSize;
    private int ySize;

    private CellEntity[][] cells;
    private LinkedList<Enemy> enemies;
    private Player player;

    /**
     * Constructor of the Level class
     * @param xSize horizontal size of the level
     * @param ySize vertical size of the level
     */
    public Level (int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        cells = new CellEntity[xSize][ySize];
        enemies = new LinkedList<>();
    }

    /**
     * Returns a CellEntity ...
     * @param x x coordinate of the requested CellEntity
     * @param y y coordinate of the requested CellEntity
     * @return the CellEntity from the specified location
     */
    public CellEntity getEntity (int x, int y) {
        return cells[x][y];
    }

    /**
     * Returns the horizontal size of the level.
     * @return the horizontal size of the level
     */
    public int getXSize () {
        return xSize;
    }

    /**
     * Returns the vertical size of the level.
     * @return the vertical size of the level
     */
    public int getYSize () {
        return ySize;
    }

    /**
     * Attempts to add an Enemy to the level.
     * @param enemy Enemy needing to be added.
     * @return true if Enemy could be added, false otherwise
     */
    public boolean addEnemy (Enemy enemy) {
        int x = enemy.getX();
        int y = enemy.getY();

        if (x < 0 || y < 0 || x > xSize - 1 || y > ySize - 1)
            return false;
        if (cells[x][y] != null)
            return false;

        enemies.add(enemy);
        cells[x][y] = enemy;

        return true;
    }

    public int getEnemyCount () {
        return enemies.size();
    }

    public Enemy getEnemy (int pos) {
        return enemies.get(pos);
    }

    public boolean addPlayer (Player player) {
        int x = player.getX();
        int y = player.getY();

        if (x < 0 || y < 0 || x > xSize - 1 || y > ySize - 1)
            return false;
        if (cells[x][y] != null)
            return false;

        this.player = player;
        cells[x][y] = player;

        return true;
    }

    public Player getPlayer () {
        return player;
    }
}
