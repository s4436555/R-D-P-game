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

        System.out.println("drawing!");

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStrokeWidth(2);
        paint.setColor(android.graphics.Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        Path path = new Path();

        for (Point p : points) {
            path.moveTo(p.x, p.y);
            path.lineTo(p.x, p.y);
        }

        path.close();

        canvas.drawPath(path, paint);
    }

    public void drawThing (Point[] points) {
        this.points = points;
        this.postInvalidate();
    }
}
