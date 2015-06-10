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

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import free.lunch.aDventure.Model.Entities.Enemies.Rat;
import free.lunch.aDventure.Model.Entities.Enemies.Horse;
import free.lunch.aDventure.Model.Entities.Enemies.Snake;
import free.lunch.aDventure.Model.Entities.Enemies.Dragon;
import free.lunch.aDventure.Model.Entities.Enemies.Wolf;
import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Entities.Wall;

/**
 * The class for level generation
 */
public class LevelGenerator {

    Random rand = new Random();
    boolean full = false;

    /**
     * Creates a level based on the given difficulty
     * @param difficulty the difficulty the level is to have
     * @return the created level
     */
    public Level genLevel (int difficulty) {

        int levelX = difficulty + rand.nextInt(3);
        int levelY = levelX + rand.nextInt(1);

        if (difficulty > 4) {
            levelX = 7 + rand.nextInt(2);
            levelY = Math.min(levelX - 1, 9);
        }

        if (difficulty > 6) {
            levelX = 9;
            levelY = 8 + rand.nextInt(1);
        }

        Level level = new Level(levelX, levelY, difficulty);
        int pool = difficulty;

        int[] tempSpace;

        for (int i = 0; i < Math.min(difficulty, level.getXSize());) {
            tempSpace = getRandomFreeSpace(level);
                if(checkClosedSpaces(level, tempSpace[0], tempSpace[1])) {
                    level.addWall(new Wall(tempSpace[0], tempSpace[1]));
                    i++;
                }
        }

        tempSpace = getRandomFreeSpace(level);
        level.addPlayer(new Player(tempSpace[0], tempSpace[1]));

        int x;
        int y;
        int score;
        int demonsAdded = 0;
        filled: while (pool > 0){
            do {
                tempSpace = getRandomFreeSpace(level);
                if(full) { break filled; }
            } while (level.isNextToPlayer(tempSpace[0], tempSpace[1]));
            x = tempSpace[0];
            y = tempSpace[1];

            // Prevent too many demons
            score = rand.nextInt(Math.min(3, pool)) + 1;
            if (score == 1) demonsAdded++;
            if (demonsAdded > 3) score++;

            pool -= score;
            switch (score) {
                default: level.addEnemy(chooseEnemyLVL1(x, y)); break;
                case 2: level.addEnemy(chooseEnemyLVL2(x, y)); break;
                case 3: level.addEnemy(chooseEnemyLVL3(x, y)); break;
            }
        }

        return level;
    }

    /**
     * Randomly chooses a lvl 1 enemy
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the chosen enemy
     */
    private Enemy chooseEnemyLVL1 (int x, int y) {
        return new Rat(x, y);
    }

    /**
     * Randomly chooses a lvl 2 enemy
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the chosen enemy
     */
    private Enemy chooseEnemyLVL2 (int x, int y) {
        switch (rand.nextInt(2)) {
            default: return new Snake(x, y, rand.nextBoolean());
            case 1: return new Wolf(x, y);
        }
    }

    /**
     * Randomly chooses a lvl 3 enemy
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the chosen enemy
     */
    private Enemy chooseEnemyLVL3 (int x, int y) {
        switch (rand.nextInt(2)) {
            default: return new Dragon(x, y);
            case 1: return new Horse(x, y);
        }
    }

    /**
     * Randomly chooses a free space
     * @param level the level on which a free space is to be chosen
     * @return the chosen free space
     */
    private int[] getRandomFreeSpace (Level level) {
        boolean existsFreeSpace = false;
        for (int i = 0; i < level.getXSize(); i++) {
            for (int j = 0; j < level.getYSize(); j++) {
                if (level.isFree(i, j))
                    existsFreeSpace = true;
            }
        }
        if(!existsFreeSpace)
        {
            full = true;
            return null;
        }

        int x;
        int y;
        do {
            x = rand.nextInt(level.getXSize());
            y = rand.nextInt(level.getYSize());
        } while (!level.isFree(x, y));
        return new int[]{x, y};
    }

    /**
     * Checks whether all spaces are reachable if the given space is filled
     * @param level the level to be checked
     * @param addedX the new x coordinate
     * @param addedY the new y coordinate
     * @return true if all spaces are reachable, false otherwise
     */
    private boolean checkClosedSpaces (Level level, int addedX, int addedY) {
        int[] freeCell = getRandomFreeSpace(level);
        int reachableCells = breadthFirstExplore(level, freeCell[0], freeCell[1], addedX, addedY);
        int totalCells = 0;
        for(int i = 0; i < level.getXSize(); i++)
        {
            for(int j = 0; j < level.getYSize(); j++)
            {
                if(!level.isWall(i, j) && !(i == addedX && j == addedY))
                    totalCells++;
            }
        }
        return totalCells == reachableCells;
    }

    /**
     * A breadth-first-search to see if all spaces are reachable if the given space is filled
     * @param level the level to be checked
     * @param x the x coordinate of the starting position
     * @param y the y coordinate of the starting position
     * @param addedX the x coordinate of the to be filled space
     * @param addedY the y coordinate of the to be filled space
     * @return true if all spaces are reachable, false otherwise
     */
    private int breadthFirstExplore (Level level, int x, int y, int addedX, int addedY) {
        LinkedList<int[]> frontier = new LinkedList<>();
        frontier.add(new int[]{x, y});
        LinkedList<int[]> explored = new LinkedList<>();
        while(!frontier.isEmpty())
        {
            int[] node = frontier.removeFirst();
            for(int[] newNode : new int[][]{new int[]{node[0] - 1, node[1]}, new int[]{node[0], node[1] - 1}, new int[]{node[0] + 1, node[1]}, new int[]{node[0], node[1] + 1}})
            {
                if(level.isValid(newNode[0], newNode[1])) {
                    if (!level.isWall(newNode[0], newNode[1]) && !(newNode[0] == addedX && newNode[1] == addedY) && !isInThere(frontier, newNode) && !isInThere(explored, newNode))
                        frontier.add(newNode);
                }
            }
            explored.add(node);
        }
        return explored.size();
    }

    private boolean isInThere (LinkedList<int[]> list, int[] node)
    {
        ListIterator<int[]> it;
        it = list.listIterator();
        while(it.hasNext())
        {
            int[] temp = it.next();
            if(temp[0] == node[0] && temp[1] == node[1])
                return true;
        }
        return false;
    }
}
