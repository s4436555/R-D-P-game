package tk.slicesofcheese.the_best_application_ever.View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Player;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Wall;
import tk.slicesofcheese.the_best_application_ever.Model.Level;

/**
 * Created by jonathan on 30-4-15.
 */
public class GameView extends View implements Observer {

    private Level level;

    private float cell_size;

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public GameView(Context context) {
        super(context);
    }

    /**
     * This method is called if the specified {@code Observable} object's
     * {@code notifyObservers} method is called (because the {@code Observable}
     * object has been updated.
     *
     * @param observable the {@link Observable} object.
     * @param data       the data passed to {@link Observable#notifyObservers(Object)}.
     */
    @Override
    public void update(Observable observable, Object data) {
        this.postInvalidate();
    }

    private void drawBackground(Canvas canvas) {
        // Make it black
        canvas.drawARGB(255, 0, 0, 0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.drawBackground(canvas);

        if(level==null)
            return;

        this.drawLevel();
    }

    private void drawLevel () {
        for (int x = 0; x < level.getXSize(); x++) {
            for (int y = 0; y < level.getYSize(); y++) {
                CellEntity entity = level.getEntity(x, y);
                if (entity != null) {
                    if (entity instanceof Wall)
                        drawWall(x, y);
                    else if (entity instanceof Enemy)
                        drawEnemy((Enemy) entity);
                    else if (entity instanceof Player)
                        drawPlayer((Player) entity);
                }
            }
        }
    }

    private void drawWall (int x, int y) {

    }

    private void drawEnemy (Enemy enemy) {

    }

    private void drawPlayer (Player player) {

    }

    public void setLevel (Level level) {
        this.level = level;
    }

}
