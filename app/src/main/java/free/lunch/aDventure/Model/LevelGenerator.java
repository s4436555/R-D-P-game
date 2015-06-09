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

import free.lunch.aDventure.Model.Entities.Enemies.Dragon;
import free.lunch.aDventure.Model.Entities.Enemies.Horse;
import free.lunch.aDventure.Model.Entities.Enemies.Snake;
import free.lunch.aDventure.Model.Entities.Enemies.TestEnemy;
import free.lunch.aDventure.Model.Entities.Enemies.Wolf;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Entities.Wall;

/**
 * A class that Jan should implement properly
 */
public class LevelGenerator {

    public Level genLevel (int difficulty) {
        Level temp = new Level(9, 9);

        temp.addEnemy(new Dragon(1, 3));
        temp.addEnemy(new TestEnemy(8, 4));
        temp.addPlayer(new Player(0, 0));
        temp.addEnemy(new Snake(6, 0, true));
        temp.addEnemy(new Horse(8, 8));
        temp.addEnemy(new Wolf(0, 8));

        for (int i = 0; i < 8; i++)
            temp.addWall(new Wall(i, i));

        return temp;
    }

    private boolean checkClosedSpaces (Level level) {
        int y = 0;
        int x = 0;
        cellfound: while(y < level.getYSize())
        {
            x = 0;
            while(x < level.getXSize())
            {
                if(!level.isWall(x, y))
                    break cellfound;
            }
        }
        int reachableCells = breadthFirstExplore(level, x, y);
        int totalCells = 0;
        for(int i = 0; i < level.getXSize(); i++)
        {
            for(int j = 0; j < level.getYSize(); j++)
            {
                if(!level.isWall(i, j))
                    totalCells++;
            }
        }
        return totalCells == reachableCells;
    }

    /*add node class? */
    private int breadthFirstExplore (Level level, int x, int y) {
        LinkedList<int[]> frontier = new LinkedList<>();
        frontier.add(new int[]{x, y});
        LinkedList<int[]> explored = new LinkedList<>();
        while(!frontier.isEmpty())
        {
            int[] node = frontier.removeFirst();
            for(int[] newNode : new int[][]{new int[]{node[0] - 1, node[1]}, new int[]{node[0], node[1] - 1}, new int[]{node[0] + 1, node[1]}, new int[]{node[0], node[1] + 1}})
            {
                if(level.isValid(newNode[0], newNode[1])) {
                    if (!level.isWall(newNode[0], newNode[1]) && !isInThere(frontier, newNode) && !isInThere(explored, newNode))
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
