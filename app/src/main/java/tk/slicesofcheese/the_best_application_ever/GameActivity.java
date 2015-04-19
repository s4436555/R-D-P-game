package tk.slicesofcheese.the_best_application_ever;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


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
                    case 0:
                        textView.setText("Up");
                        break;
                    case 1:
                        textView.setText("Right");
                        break;
                    case 2:
                        textView.setText("Down");
                        break;
                    default:
                        textView.setText("Left");
                        break;
                }

                //textView.setText("Touch coordinates : " +
                //       String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                return true;
            }
        });
    }

    private int location(float x, float y, int height, int width){
        float perX = x/(float)width;
        float perY = y/(float)height;
        if (perX > perY){
            if(1-perX > perY){
                return 0;
            } else {
                return 1;
            }
        } else {
            if(1-perX > perY){
                return 3;
            } else {
                return 2;
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
