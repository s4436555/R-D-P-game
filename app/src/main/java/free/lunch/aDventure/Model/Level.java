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
package free.lunch.aDventure.Model;

import java.io.Serializable;
import java.util.LinkedList;

import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Entities.Portal;
import free.lunch.aDventure.Model.Entities.Wall;

/**
 * A class for storing and handling all of the level data.
 */
public class Level implements Serializable{

    private int xSize;
    private int ySize;
    private int difficulty;

    private CellEntity[][] cells;
    private LinkedList<Enemy> enemies;
    private Player player;

    /**
     * Constructor of the Level class
     * @param xSize horizontal size of the level
     * @param ySize vertical size of the level
     */
    public Level (int xSize, int ySize, int difficulty) {
        this.xSize = xSize;
        this.ySize = ySize;

        this.difficulty = difficulty;

        cells = new CellEntity[xSize][ySize];
        enemies = new LinkedList<>();
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
     * Returns the assigned difficulty of this level.
     * @return the assigned difficulty of this level
     */
    public int getDifficulty () {
        return difficulty;
    }

    /**
     * Assigns a difficulty to this level.
     * @param newDifficulty the new difficulty for this level
     */
    public void setDifficulty (int newDifficulty) {
        difficulty = newDifficulty;
    }

    /**
     * Returns true if the player has been killed.
     * @return true if the player has been killed, false otherwise
     */
    public boolean gameover () {
        return player == null;
    }

    /**
     * Returns true if no enemies are left in this level.
     * @return true if no enemies are left in this level, false otherwise
     */
    public boolean levelCleared () {
        return enemies.size() == 0;
    }

    public boolean isFree (int x, int y) {
        if (player != null) {
            for (int pX = player.getX()-1; pX <= player.getX()+1; pX++) {
                for (int pY = player.getY() - 1; pY <= player.getY() + 1; pY++) {
                    if (isValid(pX, pY) {
                        if (cells[pX][pY] != null) return false;
                    }
                }
            }
        }
        if (isValid(x, y))
            return cells[x][y] == null;
        return false;
    }

    public boolean isValid (int x, int y) {
        return (!(x < 0 || y < 0 || x > xSize - 1 || y > ySize - 1));
    }

    public boolean isEnemy (int x, int y){
        return cells[x][y] instanceof Enemy;
    }

    public boolean isWall (int x, int y){
        return cells[x][y] instanceof Wall;
    }

    public boolean isPortal (int x, int y){
        return cells[x][y] instanceof Portal;
    }

    /**
     * Returns the player instance stored in this.
     * @return the player instance
     */
    public Player getPlayer () {
        return player;
    }

    /**
     * Returns a CellEntity.
     * @param x x coordinate of the requested CellEntity
     * @param y y coordinate of the requested CellEntity
     * @return the CellEntity from the specified location
     */
    public CellEntity getEntity (int x, int y) {
        return cells[x][y];
    }

    /**
     * Returns the number of enemies left in this.
     * @return the number of enemies left
     */
    public int getEnemyCount () {
        return enemies.size();
    }

    /**
     * Returns an Enemy.
     * @param index index of enemy
     * @return an instance of Enemy
     */
    public Enemy getEnemy (int index) {
        return enemies.get(index);
    }

    /**
     * Removes the player from this level.
     */
    public void killPlayer () {
        clearCell(player.getX(), player.getY());
        player = null;
    }

    /**
     * Changes the cell at the given coordinates to null
     * @param x x coordinate
     * @param y y coordinate
     */
    public void clearCell (int x, int y) {
        cells[x][y] = null;
    }

    /**
     * Moves a CellEntity, if possible.
     * @param oldX current x coordinate
     * @param oldY current y coordinate
     * @param newX new x coordinate
     * @param newY new y coordinate
     */
    public void moveEntity (int oldX, int oldY, int newX, int newY) {
        if (oldX == newX && oldY == newY)
            return;
        if (isValid(newX, newY) && isValid(oldX, oldY)) {
            CellEntity entity = cells[oldX][oldY];
            if (entity != null) {
                entity.setCoordinates(newX, newY);
                cells[newX][newY] = entity;
                clearCell(oldX, oldY);
            }
        }
    }

    /**
     * Removes a Enemy in this.
     * @param enemy Enemy to remove
     */
    public void removeEnemy (Enemy enemy) {
        clearCell(enemy.getX(), enemy.getY());
        enemies.remove(enemy);
    }

    /**
     * Attempts to add an Enemy to the level.
     * @param enemy Enemy needing to be added.
     * @return true if Enemy could be added, false otherwise
     */
    public boolean addEnemy (Enemy enemy) {
        int x = enemy.getX();
        int y = enemy.getY();

        if (isFree(x, y)) {
            enemies.add(enemy);
            cells[x][y] = enemy;
            return true;
        }
        return  false;
    }

    /**
     * Attempts to add the player to the level.
     * @param player Player needing to be added
     * @return true if the player could be added, false otherwise
     */
    public boolean addPlayer (Player player) {
        int x = player.getX();
        int y = player.getY();

        if (isFree(x, y)) {
            this.player = player;
            cells[x][y] = player;
            return true;
        }
        return  false;
    }

    /**
     * Attempts to add a wall piece to the level.
     * @param wall Wall needing to be added
     * @return true if the wall could be added, false otherwise
     */
    public boolean addWall (Wall wall) {
        int x = wall.getX();
        int y = wall.getY();

        if (isFree(x, y)) {
            cells[x][y] = wall;
            return true;
        }
        return  false;
    }

    /**
     * Attempts to add a portal piece to the level.
     * @param portal Portal needing to be added
     * @return true if the portal could be added, false otherwise
     */
    public boolean addPortal (Portal portal) {
        int x = portal.getX();
        int y = portal.getY();

        if (isFree(x, y)) {
            cells[x][y] = portal;
            return true;
        }
        return  false;
    }
}
