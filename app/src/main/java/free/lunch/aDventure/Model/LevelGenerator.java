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

    public Level genLevel () {
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
}
