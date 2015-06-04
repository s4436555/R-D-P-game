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
package free.lunch.aDventure;

import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Level;

/**
 * This class controlls the enemies in the level.
 */
public class EnemyController {

    private Level level;

    /**
     * Constructor of the EnemyController class.
     * @param level level to control the enemies of
     */
    public EnemyController (Level level) {
        this.level = level;
    }

    /**
     * Moves all enemies of the level
     */
    public void moveEnemies (){
        int total = level.getEnemyCount();
        for (int i = 0; i < total; i++){
            Enemy enemy = level.getEnemy(i);
            Player player = level.getPlayer();
            if (player != null) {
                for (int[] move : enemy.getMoves(player.getX(), player.getY())) {
                    if (level.isValid(move[0], move[1])) {
                        if (level.getEntity(move[0], move[1]) instanceof Player)
                            level.killPlayer();
                        if (level.isFree(move[0], move[1])) {
                            level.moveEntity(enemy.getX(), enemy.getY(), move[0], move[1]);

                            if (level.gameover()) {
                                System.out.println("You lost!");
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
