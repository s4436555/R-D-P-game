package free.lunch.aDventure;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class HighScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        getActionBar().setIcon(R.drawable.crown_ic);

        for (int i = 0; i < 10; i++) {
            int nameID = getResources().getIdentifier("player" + i + "_name", "id", getPackageName());
            TextView player = (TextView) findViewById(nameID);

            int descID = getResources().getIdentifier("player" + i + "_desc", "id", getPackageName());
            TextView desc = (TextView) findViewById(descID);

            //TODO:Load highscore names and scores

            player.setText("Player"+i);

            switch (i){
                case 0:
                    desc.setText("is dominating the leaderboard\nwith " + i + " points.");
                    break;
                case 1:
                    desc.setText("is the runner up with " + i + " points.");
                    break;
                case 2:
                    desc.setText("spent quite some time getting " + i + " points.");
                    break;
                case 8:
                    desc.setText("is so bad he only got " + i + " points.");
                    break;
                case 9:
                    desc.setText("nearly didn't make it on here\nwith his " + i + " points.");
                    break;
                default:
                    desc.setText("doesn't get an individual text\nwith his " + i + " points.");
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_high_score, menu);
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
