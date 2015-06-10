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

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Level;

/**
 * This class controls the enemies in the level.
 */
public class EnemyController implements Serializable {

    private Level level;

    /**
     * Constructor of the EnemyController class.
     * @param level level to control the enemies of
     */
    public EnemyController (Level level) {
        this.level = level;
    }

    /**
     * Sets the given level as the current level
     * @param level the level to be set
     */
    public void setLevel(Level level){
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
                List<int[]> moves = enemy.getMoves(player.getX(), player.getY());
                sortMoves(moves);
                for (int[] move : moves) {
                    if (level.isValid(move[0], move[1])) {
                        if (level.getEntity(move[0], move[1]) instanceof Player)
                            level.killPlayer();
                        if (level.isFree(move[0], move[1])) {
                            level.moveEntity(enemy.getX(), enemy.getY(), move[0], move[1]);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Sorts the list of moves
     * @param moves the available moves
     */
    private void sortMoves (List<int[]> moves) {
        Collections.sort(moves, new Comparator<int[]>() {
            @Override
            public int compare(int[] lhs, int[] rhs) {
                Random random = new Random();

                if (lhs[2] > rhs[2]) {
                    return 1;
                } else if (lhs[2] < rhs[2]) {
                    return -1;
                }
                return random.nextInt(3) - 1;
            }
        });
    }
}
