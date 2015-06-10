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

import free.lunch.aDventure.Model.Entities.Enemies.Dragon;
import free.lunch.aDventure.Model.Entities.Enemies.Horse;
import free.lunch.aDventure.Model.Entities.Enemies.Snake;
import free.lunch.aDventure.Model.Entities.Enemies.TestEnemy;
import free.lunch.aDventure.Model.Entities.Enemies.Wolf;
import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Entities.Wall;

/**
 * A class that Jan should implement properly
 */
public class LevelGenerator {

    Random rand = new Random();

    public Level genLevel (int difficulty) {
        Level level = new Level(9, 9, difficulty);
        int pool = difficulty;

        int[] tempSpace;

        for (int i = 0; i < Math.min(difficulty, 8);) {
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
        while (pool > 0){
            tempSpace = getRandomFreeSpace(level);
            x = tempSpace[0];
            y = tempSpace[1];
            System.out.println("x: " +x+ "y: " +y);
            score = rand.nextInt(Math.min(3, pool)) + 1;
            System.out.println(score);
            System.out.println(pool);
            pool -= score;
            switch (score) {
                default: level.addEnemy(chooseEnemyLVL1(x, y)); break;
                case 2: level.addEnemy(chooseEnemyLVL2(x, y)); break;
                case 3: level.addEnemy(chooseEnemyLVL3(x, y)); break;
            }
        }
        /*level.addEnemy(new Dragon(1, 3));
        level.addEnemy(new TestEnemy(8, 4));
        level.addPlayer(new Player(0, 0));
        level.addEnemy(new Snake(6, 0, true));
        level.addEnemy(new Horse(8, 8));
        level.addEnemy(new Wolf(0, 8));

        for (int i = 0; i < 8; i++)
            level.addWall(new Wall(i, i));
*/
        return level;
    }

    private Enemy chooseEnemyLVL1 (int x, int y) {
        return new TestEnemy(x, y);
    }

    private Enemy chooseEnemyLVL2 (int x, int y) {
        switch (rand.nextInt(2)) {
            default: return new Snake(x, y, rand.nextBoolean());
            case 1: return new Horse(x, y);
        }
    }

    private Enemy chooseEnemyLVL3 (int x, int y) {
        switch (rand.nextInt(2)) {
            default: return new Dragon(x, y);
            case 2: return new Wolf(x, y);
        }
    }

    private int[] getRandomFreeSpace (Level level) {
        boolean existsFreeSpace = false;
        for (int i = 0; i < level.getXSize(); i++) {
            for (int j = 0; j < level.getYSize(); j++) {
                if (level.isFree(i, j))
                    existsFreeSpace = true;
            }
        }
        if(!existsFreeSpace)
            System.exit(-1);

        int x;
        int y;
        do {
            x = rand.nextInt(level.getXSize());
            y = rand.nextInt(level.getYSize());
        } while (!level.isFree(x, y));
        return new int[]{x, y};
    }

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

    /*add node class? */
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
