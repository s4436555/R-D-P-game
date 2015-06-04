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
package free.lunch.aDventure.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import free.lunch.aDventure.Model.Corner;
import free.lunch.aDventure.R;

/**
 * Shows the pressed area.
 */
public class TouchOverlay extends View implements Observer {

    private Point[] points;
    private int borderWidth;

    public TouchOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        borderWidth = metrics.densityDpi / 120;
    }

    public TouchOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        borderWidth = metrics.densityDpi / 120;
    }

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public TouchOverlay(Context context) {
        super(context);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        borderWidth = metrics.densityDpi / 120;
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (points == null)
            return;

        Paint border = new Paint(Paint.ANTI_ALIAS_FLAG);

        border.setStrokeWidth(borderWidth);
        border.setColor(this.getResources().getColor(R.color.touch_controls_border));
        border.setStyle(Paint.Style.STROKE);
        border.setAntiAlias(true);

        Paint inner = new Paint();

        inner.setColor(this.getResources().getColor(R.color.touch_controls_fill));
        inner.setStyle(Paint.Style.FILL);

        Path path = new Path();

        path.moveTo(points[0].x, points[0].y);

        for (Point p : points) {
            path.lineTo(p.x, p.y);
        }

        path.close();

        canvas.drawPath(path, border);
        canvas.drawPath(path, inner);
    }

    public void drawTouchArea (Corner corner, int[] coordinates, float cSize) {

        int width = coordinates[2] - coordinates[0];
        int height = coordinates[3] - coordinates[1];

        int a = Math.round(cSize * width);
        int b = Math.round(cSize * height);

        int[] inner = {
                coordinates[0] + a,
                coordinates[1] + b,
                coordinates[2] - a,
                coordinates[3] - b
        };

        points = new Point[4];

        switch (corner) {
            case UP:
                points[0] = new Point(coordinates[0], coordinates[1]);
                points[1] = new Point(coordinates[2], coordinates[1]);
                points[2] = new Point(inner[2], inner[1]);
                points[3] = new Point(inner[0], inner[1]);
                break;
            case RIGHT:
                points[0] = new Point(coordinates[2], coordinates[1]);
                points[1] = new Point(inner[2], inner[1]);
                points[2] = new Point(inner[2], inner[3]);
                points[3] = new Point(coordinates[2], coordinates[3]);
                break;
            case DOWN:
                points[0] = new Point(coordinates[0], coordinates[3]);
                points[1] = new Point(coordinates[2], coordinates[3]);
                points[2] = new Point(inner[2], inner[3]);
                points[3] = new Point(inner[0], inner[3]);
                break;
            case LEFT:
                points[0] = new Point(coordinates[0], coordinates[1]);
                points[1] = new Point(inner[0], inner[1]);
                points[2] = new Point(inner[0], inner[3]);
                points[3] = new Point(coordinates[0], coordinates[3]);
                break;
            default:
                points[0] = new Point(inner[0], inner[1]);
                points[1] = new Point(inner[2], inner[1]);
                points[2] = new Point(inner[2], inner[3]);
                points[3] = new Point(inner[0], inner[3]);
                break;
        }
        this.postInvalidate();
    }

    public void clear () {
        points = null;
        this.postInvalidate();
    }
}
