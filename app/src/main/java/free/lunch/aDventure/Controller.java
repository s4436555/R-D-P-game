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

import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.Random;

import free.lunch.aDventure.Model.Corner;
import free.lunch.aDventure.Model.Direction;
import free.lunch.aDventure.Model.Entities.Enemies.Dragon;
import free.lunch.aDventure.Model.Entities.Enemies.Horse;
import free.lunch.aDventure.Model.Entities.Enemies.Snake;
import free.lunch.aDventure.Model.Entities.Enemies.Wolf;
import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Entities.Portal;
import free.lunch.aDventure.Model.Level;
import free.lunch.aDventure.Model.LevelGenerator;
import free.lunch.aDventure.View.GameView;
import free.lunch.aDventure.View.TouchOverlay;

/**
 * Controller for a:Dventure.
 */
public class Controller implements View.OnTouchListener, Serializable {

    private final float buttonSize = 0.33f;
    private EnemyController ec;
    private Level level;
    private GameView gv;
    private GameActivity ga;
    private TouchOverlay to;

    private Boolean onPortal = false;


    private int score = 0;

    private int snakesKill = 0;
    private int horsesKill = 0;
    private int wolvesKill = 0;
    private int dragonsKill = 0;

    private int lost = 0;
    private int stagesCleared = 0;
    private int distanceWalked = 0;

    public Controller (GameActivity ga) {
        this.ga = ga;
        LevelGenerator generator = new LevelGenerator();
        level = generator.genLevel(4);
        ec = new EnemyController(level);

        gv = (GameView)ga.findViewById(R.id.gameView);
        gv.setLevel(level);

        to = (TouchOverlay)ga.findViewById(R.id.touchOverlay);
    }

    private boolean checkGameOver(){
        return level.getPlayer() == null;
    }


    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int[] c = gv.getCoordinates();

        int height = c[3] - c[1];
        int width = c[2] - c[0];
        float x = event.getX() - c[0];
        float y = event.getY() - c[1];

        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (location(x, y, height, width)) {
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
                    break;
            }
            if (!onPortal) {
                ec.moveEnemies();
            } else{
                onPortal = false;
            }
            if(checkGameOver()){
                lost++;
                if (score>0){
                    ga.scorePopup();
                } else {
                    ga.setStatistics();
                    ga.finish();
                }
            }
            gv.postInvalidate();
            to.clear();
        }
        else {
            to.drawTouchArea(location(x, y, height, width), c, buttonSize);
        }

        return true;
    }

    public StatisticsStorage getStats(){
        return new StatisticsStorage(snakesKill, dragonsKill, wolvesKill, horsesKill, lost, stagesCleared, distanceWalked);
    }

    /**
     * Returns the part of the screen that the coordinate is on.
     * @param x x coordinate of input
     * @param y y coordinate of input
     * @param height height of activity
     * @param width width of activity
     * @return the corner of the coordinate
     */
    private Corner location(float x, float y, int height, int width){
        float perX = x/(float)width;
        float perY = y/(float)height;

        if (perX > buttonSize && perX < 1-buttonSize && perY > buttonSize && perY < 1-buttonSize)
            return  Corner.CENTER;

        if (perX > perY){
            if(1 - perX > perY){
                return Corner.UP;
            } else {
                return Corner.RIGHT;
            }
        } else {
            if(1 - perX > perY){
                return Corner.LEFT;
            } else {
                return Corner.DOWN;
            }
        }
    }

    public void addKills(Enemy enemy){
        if (enemy instanceof Wolf){
            wolvesKill++;
        }
        if (enemy instanceof Snake){
            snakesKill++;
        }
        if (enemy instanceof Dragon){
            dragonsKill++;
        }
        if (enemy instanceof Horse){
            horsesKill++;
        }
    }

    public Level getLevel(){
        return level;
    }

    public void setLevel(Level level){
        gv.setLevel(level);
        ec.setLevel(level);
        this.level = level;
    }

    public int getScore(){
        return score;
    }

    /**
     * Attempts to move the player in the given direction
     * @param dir direction to move the player in
     * @return true if player could be moved, false otherwise
     */
    private boolean movePlayer (Direction dir) {
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
                    if (level.levelCleared()){
                        int px, py;
                        Random random = new Random();
                        do {
                            px = random.nextInt(level.getXSize());
                            py = random.nextInt(level.getYSize());
                        } while (!level.isFree(px,py));
                        level.addPortal(new Portal(px,py));
                    }
                }
                if (level.isPortal(x, y)) {
                    onPortal = true;
                    stagesCleared++;
                    gv.setStage(stagesCleared + 1);
                    System.out.println("Sneks: " +snakesKill+ " Dragons: " + dragonsKill+ " Horses: " +horsesKill+ "Wolves: " +wolvesKill);
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
}
