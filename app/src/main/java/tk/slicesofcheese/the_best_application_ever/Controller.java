package tk.slicesofcheese.the_best_application_ever;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import tk.slicesofcheese.the_best_application_ever.Model.Corner;
import tk.slicesofcheese.the_best_application_ever.Model.Direction;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Player;
import tk.slicesofcheese.the_best_application_ever.Model.Level;
import tk.slicesofcheese.the_best_application_ever.View.GameView;
import tk.slicesofcheese.the_best_application_ever.View.TouchOverlay;

/**
 * Created by jonathan on 29-4-15.
 */
public class Controller implements View.OnTouchListener {

    private Level testlevel;
    private GameView gv;
    private TouchOverlay to;

    public Controller (GameActivity ga) {
        testlevel = new Level(9, 9);
        testlevel.addEnemy(new Enemy(1, 3));
        testlevel.addEnemy(new Enemy(8, 4));
        testlevel.addPlayer(new Player(0, 0, 5));

        for (int i = 0; i < 9; i++)
            testlevel.addWall(i, i);

        gv = (GameView)ga.findViewById(R.id.gameView);
        gv.setLevel(testlevel);

        to = (TouchOverlay)ga.findViewById(R.id.touchOverlay);
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
                    testlevel.movePlayer(Direction.UP);
                    gv.postInvalidate();
                    break;
                case RIGHT:
                    testlevel.movePlayer(Direction.RIGTH);
                    gv.postInvalidate();
                    break;
                case DOWN:
                    testlevel.movePlayer(Direction.DOWN);
                    gv.postInvalidate();
                    break;
                case LEFT:
                    testlevel.movePlayer(Direction.LEFT);
                    gv.postInvalidate();
                    break;
                default:
                    break;
            }
            to.clear();
        }
        else {
            to.drawTouchArea(location(x, y, height, width), c, 0.4f);
        }

        return true;
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

        if (perX > 0.40 && perX < 0.60 && perY > 0.40 && perY < 0.60)
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
}
