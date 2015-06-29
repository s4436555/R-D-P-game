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
package free.lunch.aDventure.Controller;

import java.io.Serializable;
import java.util.Random;

import free.lunch.aDventure.GameActivity;
import free.lunch.aDventure.Model.Corner;
import free.lunch.aDventure.Model.Direction;
import free.lunch.aDventure.Model.Entities.Enemies.Dragon;
import free.lunch.aDventure.Model.Entities.Enemies.Horse;
import free.lunch.aDventure.Model.Entities.Enemies.Rat;
import free.lunch.aDventure.Model.Entities.Enemies.Snake;
import free.lunch.aDventure.Model.Entities.Enemies.Wolf;
import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Entities.Portal;
import free.lunch.aDventure.Model.Level;
import free.lunch.aDventure.Model.StatisticsStorage;
import free.lunch.aDventure.R;
import free.lunch.aDventure.View.GameView;

/**
 * Controller for a:Dventure.
 */
public class Controller implements Runnable, Serializable {
    private EnemyController ec;
    private Level level;
    private GameView gv;
    private GameActivity ga;

    private Boolean onPortal = false;
    private Boolean working = false;
    private Corner corner;

    private int score = 0;

    private int demonsKilled = 0;
    private int snakesKill = 0;
    private int horsesKill = 0;
    private int wolvesKill = 0;
    private int ratsKill = 0;

    private int lost = 0;
    private int stagesCleared = 0;
    private int distanceWalked = 0;

    /**
     * Constructor for the Controller
     *
     * @param ga the GameActivity
     */
    public Controller(GameActivity ga) {
        this.ga = ga;
        LevelGenerator generator = new LevelGenerator();
        level = generator.genLevel(4);
        ec = new EnemyController(level);

        gv = (GameView) ga.findViewById(R.id.gameView);
        gv.setLevel(level);
    }

    public boolean isWorking() {
        return working;
    }

    public void postInput(Corner c) {
        corner = c;
        if (!working) {
            Thread t = new Thread(this);
            t.start();
        }
    }

    /**
     * Check whether the player is killed and thus whether the game is over
     *
     * @return true if the player is killed, false otherwise
     */
    private boolean checkGameOver() {
        Player player = level.getPlayer();
        if (player == null)
            return true;
        return !player.getAlive();
    }

    /**
     * Returns the statistics in an StatisticsStorage
     *
     * @return the statistics
     */
    public StatisticsStorage getStats() {
        return new StatisticsStorage(snakesKill, ratsKill, wolvesKill, horsesKill, lost, stagesCleared, distanceWalked, demonsKilled);
    }

    /**
     * Adds a kill to the list of enemies killed in this gamesession
     *
     * @param enemy
     */
    public void addKills(Enemy enemy) {
        if (enemy instanceof Dragon) {
            demonsKilled++;
        }
        if (enemy instanceof Wolf) {
            wolvesKill++;
        }
        if (enemy instanceof Snake) {
            snakesKill++;
        }
        if (enemy instanceof Rat) {
            ratsKill++;
        }
        if (enemy instanceof Horse) {
            horsesKill++;
        }
    }

    /**
     * Returns the level
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets the given level as the current level, also does this for the GameView and the EnemyController
     *
     * @param level the level to be set
     */
    public void setLevel(Level level) {
        gv.setLevel(level);
        ec.setLevel(level);
        this.level = level;
    }

    /**
     * Returns the current score
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Attempts to move the player in the given direction
     *
     * @param dir direction to move the player in
     * @return true if player could be moved, false otherwise
     */
    private boolean movePlayer(Direction dir) {
        Player player = level.getPlayer();
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

        if (level.isValid(x, y)) {
            if (!level.isWall(x, y)) {
                if (level.isEnemy(x, y)) {
                    addKills((Enemy) level.getEntity(x, y));
                    this.score = this.score + ((Enemy) level.getEntity(x, y)).getPoints();
                    gv.setScore(this.score);
                    level.removeEnemy((Enemy) level.getEntity(x, y));
                    if (level.levelCleared()) {
                        int px, py;
                        Random random = new Random();
                        do {
                            px = random.nextInt(level.getXSize());
                            py = random.nextInt(level.getYSize());
                        } while (!level.isFree(px, py));
                        level.addPortal(new Portal(px, py));
                    }
                }
                if (level.isPortal(x, y)) {
                    onPortal = true;
                    stagesCleared++;
                    gv.setStage(stagesCleared + 1);
                    LevelGenerator generator = new LevelGenerator();
                    level = generator.genLevel(level.getDifficulty() + 2);
                    ec = new EnemyController(level);
                    gv.setLevel(level);
                }
                level.moveEntity(player.getX(), player.getY(), x, y);
                return true;
            }
        }

        return false;
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        if (working || corner == null)
            return;
        working = true;
        level.getPlayer().idle();
        switch (corner) {
            case UP:
                this.movePlayer(Direction.UP);
                distanceWalked += 1;
                break;
            case RIGHT:
                this.movePlayer(Direction.RIGTH);
                distanceWalked += 1;
                break;
            case DOWN:
                this.movePlayer(Direction.DOWN);
                distanceWalked += 1;
                break;
            case LEFT:
                this.movePlayer(Direction.LEFT);
                distanceWalked += 1;
                break;
            case CENTER:
                break;
            default:
                return;
        }
        if (!onPortal) {
            ec.moveEnemies();
        } else {
            onPortal = false;
        }
        if (checkGameOver()) {
            lost++;
            ga.setStatistics();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            if (score > 0) {
                ga.scorePopup();
            } else {
                ga.finish();
            }
        }
        gv.postInvalidate();
        working = false;
    }
}
