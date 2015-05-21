/* Copyright 2015 Jan Potma, Jonathan Moerman, Mathis Sackers, Tom Nijholt
*
* This file is part of a:Dventure.
*
* a:Dventure is free software: you can redistribute it
* and/or modify it under the terms of the GNU General Public License as
* published by the Free Software Foundation, either version 2 of the
* License, or (at your option) any later version.
*
* a:Dventure is distributed in the hope that it will be
* useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
* Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with a:Dventure. If not, see http://www.gnu.org/licenses/.
*/
package tk.slicesofcheese.the_best_application_ever.Model;

import java.util.LinkedList;

import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Player;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Wall;

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

        if (isAvailable(x, y)) {
            enemies.add(enemy);
            cells[x][y] = enemy;
            return true;
        }
        return  false;
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

        if (isAvailable(x, y)) {
            this.player = player;
            cells[x][y] = player;
            return true;
        }
        return  false;
    }

    public Player getPlayer () {
        return player;
    }

    public boolean addWall (int x, int y) {
        if (isAvailable(x, y)) {
            cells[x][y] = new Wall();
            return true;
        }
        return  false;
    }

    public boolean isAvailable (int x, int y) {
        if (x < 0 || y < 0 || x > xSize - 1 || y > ySize - 1) {
            //System.out.println ("Out of bounds!" + x + " " + y);
            return false;
        }

        return cells[x][y] == null;
    }

    public boolean movePlayer (Direction dir) {

        if (player == null)
            return false;

        int x = player.getX();
        int y = player.getY();

        switch (dir) {
            case LEFT:
                x--;
                break;
            case RIGTH:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case UP:
                y--;
                break;
            default:
                break;
        }

        if (isAvailable(x,y)) {
            cells[player.getX()][player.getY()] = null;
            player.setCoordinates(x, y);
            cells[x][y] = player;
            return true;
        }

        return false;
    }
}
