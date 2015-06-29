package free.lunch.aDventure.Controller;

import android.view.MotionEvent;
import android.view.View;

import free.lunch.aDventure.GameActivity;
import free.lunch.aDventure.Model.Corner;
import free.lunch.aDventure.R;
import free.lunch.aDventure.View.GameView;
import free.lunch.aDventure.View.TouchOverlay;

/**
 * Created by jonathan on 29-6-15.
 */
public class Listener implements View.OnTouchListener {

    private final float buttonSize = 0.33f;
    private GameView gv;
    private GameActivity ga;
    private TouchOverlay to;
    private Controller controller;

    public Listener(GameActivity ga, Controller controller) {
        this.ga = ga;
        this.controller = controller;

        gv = (GameView) ga.findViewById(R.id.gameView);
        to = (TouchOverlay) ga.findViewById(R.id.touchOverlay);
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
        if (controller.isWorking())
            return false;
        int[] c = gv.getCoordinates();

        int height = c[3] - c[1];
        int width = c[2] - c[0];
        float x = event.getX() - c[0];
        float y = event.getY() - c[1];

        if (event.getAction() == MotionEvent.ACTION_UP) {
            to.clear();
            controller.postInput(location(x, y, height, width));
        } else {
            to.drawTouchArea(location(x, y, height, width), c, buttonSize);
        }

        return true;
    }

    /**
     * Returns the part of the screen that the coordinate is on.
     *
     * @param x      x coordinate of input
     * @param y      y coordinate of input
     * @param height height of activity
     * @param width  width of activity
     * @return the corner of the coordinate
     */
    private Corner location(float x, float y, int height, int width) {
        float perX = x / (float) width;
        float perY = y / (float) height;

        if (perX > 1 || perX < 0 || perY > 1 || perY < 0)
            return Corner.OOB; // out of bounds

        if (perX > buttonSize && perX < 1 - buttonSize && perY > buttonSize && perY < 1 - buttonSize)
            return Corner.CENTER;

        if (perX > perY) {
            if (1 - perX > perY) {
                return Corner.UP;
            } else {
                return Corner.RIGHT;
            }
        } else {
            if (1 - perX > perY) {
                return Corner.LEFT;
            } else {
                return Corner.DOWN;
            }
        }
    }
}
