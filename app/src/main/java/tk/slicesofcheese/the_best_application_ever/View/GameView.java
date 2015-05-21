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
package tk.slicesofcheese.the_best_application_ever.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Player;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Wall;
import tk.slicesofcheese.the_best_application_ever.Model.Level;
import tk.slicesofcheese.the_best_application_ever.R;

/**
 * Displays the level.
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
        int px = Math.round(margin_horizontal + (enemy.getX() * cell_size ));
        int py = Math.round(margin_vertical + (enemy.getY() * cell_size ));

        Drawable d = getResources().getDrawable(R.drawable.temp_64);
        d.setBounds(px, py, Math.round(px + cell_size), Math.round(py + cell_size));
        d.draw(canvas);
    }

    private void drawPlayer (Canvas canvas, Player player) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        int px = Math.round(margin_horizontal + (player.getX() * cell_size ));
        int py = Math.round(margin_vertical + (player.getY() * cell_size ));

        Drawable d = getResources().getDrawable(R.drawable.happy_64);
        d.setBounds(px, py, Math.round(px + cell_size), Math.round(py + cell_size));
        d.draw(canvas);
    }

    /**
     * Changes the level that needs to be drawn.
     * @param level the level that needs to be drawn
     */
    public void setLevel (Level level) {
        this.level = level;
        System.out.println(level.addWall(5, 5));
    }

    /**
     * Returns the coordinates of the edges of the level on the view.
     * @return the coordinates of the edges of the level on the view,
     * upper right corner first, followed by the bottom right corner.
     */
    public int[] getCoordinates () {
        int[] temp = new int[4];

        temp[0] = Math.round(margin_horizontal);
        temp[1] = Math.round(margin_vertical);
        temp[2] = this.getWidth() - Math.round(margin_horizontal);
        temp[3] = this.getHeight() - Math.round(margin_vertical);

        return temp;
    }

}
