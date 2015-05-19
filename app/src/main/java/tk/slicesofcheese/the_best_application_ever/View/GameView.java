package tk.slicesofcheese.the_best_application_ever.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private float margin_horizontal;
    private float margin_vertical;

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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(level==null)
            return;

        // calculate the dimensions
        cell_size = Math.min(w / level.getXSize(), h / level.getYSize());

        // calculate the required margin
        margin_horizontal = (w - (cell_size * level.getXSize())) / 2;
        margin_vertical = (h - (cell_size * level.getYSize())) / 2;
    }

    private void drawBackground(Canvas canvas) {
        // Make it black
        canvas.drawARGB(255, 0, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.drawBackground(canvas);

        if(level==null) {
            System.out.println("No level to draw!");
            return;
        }

        //System.out.

        this.drawLevel(canvas);
    }

    private void drawLevel (Canvas canvas) {
        for (int x = 0; x < level.getXSize(); x++) {
            for (int y = 0; y < level.getYSize(); y++) {
                CellEntity entity = level.getEntity(x, y);
                if (entity != null) {
                    if (entity instanceof Wall)
                        drawWall(canvas, x, y);
                    else if (entity instanceof Enemy)
                        drawEnemy(canvas, (Enemy) entity);
                    else if (entity instanceof Player)
                        drawPlayer(canvas, (Player) entity);
                }
            }
        }
    }

    private void drawWall (Canvas canvas, int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        float px = margin_horizontal + (x * cell_size );
        float py = margin_vertical + (y * cell_size );

        canvas.drawRect(px, py, px + cell_size, py + cell_size, paint);
    }

    private void drawEnemy (Canvas canvas, Enemy enemy) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        float px = margin_horizontal + (enemy.getX() * cell_size );
        float py = margin_vertical + (enemy.getY() * cell_size );

        canvas.drawRect(px, py, px + cell_size, py + cell_size, paint);
    }

    private void drawPlayer (Canvas canvas, Player player) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        float px = margin_horizontal + (player.getX() * cell_size );
        float py = margin_vertical + (player.getY() * cell_size );

        canvas.drawRect(px, py, px + cell_size, py + cell_size, paint);
    }

    public void setLevel (Level level) {
        this.level = level;
        System.out.println(level.addWall(5,5));
    }

}
