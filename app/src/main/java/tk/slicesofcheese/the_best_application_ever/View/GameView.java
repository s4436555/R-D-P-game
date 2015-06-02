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
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;
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
    private int score = 0;
    private int bubble_margin = 10;
    private int stage = 0;
    private int scoreHeight = 40;

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
        cell_size = Math.min(w / level.getXSize(), h  / level.getYSize());

        // calculate the required margin
        margin_horizontal = 12 + ((w - (cell_size * level.getXSize())) / 2); //------UNCLEAN-----
        margin_vertical = 12 + ((h - (cell_size * level.getYSize())) / 2); //------UNCLEAN-----

        //------UNCLEAN-----
        cell_size = Math.min((w - 40) / level.getXSize(), (h - 30) / level.getYSize());
    }

    private void drawBackground(Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble);
        d.setBounds(Math.round(margin_horizontal),
                Math.round(margin_vertical),
                canvas.getWidth()-Math.round(margin_horizontal),
                canvas.getHeight()-Math.round(margin_vertical)
        );
        d.draw(canvas);
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

        this.drawScore(canvas);

        this.drawStage(canvas);
    }

    private void drawLevel (Canvas canvas) {
        for (int x = 0; x < level.getXSize(); x++) {
            for (int y = 0; y < level.getYSize(); y++) {
                CellEntity entity = level.getEntity(x, y);
                if (entity != null) {
                    drawEntity(canvas, entity);
                }
            }
        }
    }

    private void drawEntity (Canvas canvas, CellEntity entity) {
        //------UNCLEAN-----
        int px = Math.round(margin_horizontal + 5 + (entity.getX() * cell_size ));
        int py = Math.round(margin_vertical + 5 + (entity.getY() * cell_size ));

        Drawable d = entity.getImage(getContext());
        d.setBounds(px, py, Math.round(px + cell_size), Math.round(py + cell_size));
        d.draw(canvas);
    }

    private void drawScore (Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble);

        Rect bounds = new Rect();
        Paint p = new Paint();
        p.setTextSize(40);
        p.getTextBounds("score: 000", 0, 10, bounds);

        bounds.inset(-bubble_margin, -bubble_margin);
        bounds.offsetTo((int) (canvas.getWidth() - bounds.width() - margin_horizontal), (int) (canvas.getHeight() - margin_vertical));

        d.setBounds(bounds);

        scoreHeight = bounds.height();

        bounds.inset(bubble_margin, bubble_margin);
        d.draw(canvas);
        canvas.drawText("score: " + score, bounds.left, bounds.bottom, p);
    }

    private void drawStage (Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble2);

        Rect bounds = new Rect();
        Paint p = new Paint();
        p.setTextSize(40);
        p.getTextBounds("score: 000", 0, 10, bounds);

        bounds.inset(-bubble_margin, -bubble_margin);
        bounds.offsetTo((int) margin_horizontal, (int) (canvas.getHeight() - margin_vertical + scoreHeight));

        d.setBounds(bounds);

        bounds.inset(bubble_margin, bubble_margin);
        d.draw(canvas);
        canvas.drawText("stage: " + stage, bounds.left+10,  bounds.bottom, p);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStage(int stage) {
        this.stage = stage;
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
