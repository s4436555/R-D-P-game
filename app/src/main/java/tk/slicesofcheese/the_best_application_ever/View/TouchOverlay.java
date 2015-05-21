package tk.slicesofcheese.the_best_application_ever.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;
import tk.slicesofcheese.the_best_application_ever.Model.Corner;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Player;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Wall;
import tk.slicesofcheese.the_best_application_ever.Model.Level;

/**
 * Created by jonathan on 30-4-15.
 */
public class TouchOverlay extends View implements Observer {

    private Point[] points;

    public TouchOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public TouchOverlay(Context context) {
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (points == null)
            return;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStrokeWidth(2);
        paint.setColor(android.graphics.Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        Path path = new Path();

        path.moveTo(points[0].x, points[0].y);

        for (Point p : points) {
            path.lineTo(p.x, p.y);
        }

        path.close();

        canvas.drawPath(path, paint);
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
