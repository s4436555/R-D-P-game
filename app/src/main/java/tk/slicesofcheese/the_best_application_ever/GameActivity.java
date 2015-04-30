package tk.slicesofcheese.the_best_application_ever;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import tk.slicesofcheese.the_best_application_ever.Model.Corner;
import tk.slicesofcheese.the_best_application_ever.View.GameView;


public class GameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final TextView textView = (TextView)findViewById(R.id.textView);
        // this is the view on which you will listen for touch events
        final View touchView = findViewById(R.id.touchView);
        /*Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;*/
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (location((int) event.getX(),(int) event.getY(), v.getHeight(), v.getWidth())){
                    case UP:
                        textView.setText("Up");
                        break;
                    case RIGHT:
                        textView.setText("Right");
                        break;
                    case DOWN:
                        textView.setText("Down");
                        break;
                    case LEFT:
                        textView.setText("Left");
                        break;
                    default:
                        textView.setText("center");
                        break;
                }

                //textView.setText("Touch coordinates : " +
                //       String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                return true;
            }
        });

        GameView gv = (GameView)this.findViewById(R.id.gameView);

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
            if(1-perX > perY){
                return Corner.UP;
            } else {
                return Corner.RIGHT;
            }
        } else {
            if(1-perX > perY){
                return Corner.LEFT;
            } else {
                return Corner.DOWN;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}