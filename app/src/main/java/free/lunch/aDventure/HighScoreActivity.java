package free.lunch.aDventure;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class HighScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Drawable icon = getResources().getDrawable(R.drawable.crown_ic);
        ActionBar actionBar = getActionBar();
        if (icon != null && actionBar != null) {
            actionBar.setIcon(icon);
        }

        getActionBar().setIcon(R.drawable.crown_ic);

        SharedPreferences scorePrefs = getSharedPreferences(GameActivity.GAME_PREFS, 0);
        String[] savedScores = scorePrefs.getString("highScores", "").split("\\|");


        for (int i = 0; i < savedScores.length; i++) {

            String[] outputScores = savedScores[i].split("\\-");

            int nameID = getResources().getIdentifier("player" + i + "_name", "id", getPackageName());
            TextView player = (TextView) findViewById(nameID);

            int descID = getResources().getIdentifier("player" + i + "_desc", "id", getPackageName());
            TextView desc = (TextView) findViewById(descID);

            //TODO:Load highscore names and scores
            if (outputScores.length == 3) {
                switch (i){
                    case 0:
                        player.setText(outputScores[0]);
                        desc.setText("is dominating the leaderboard with" +outputScores[2]+  " points\nDate:" +outputScores[1]);
                        break;
                    case 1:
                        player.setText(outputScores[0]);
                        desc.setText("is the runner up with" +outputScores[2]+ " points\nDate:" +outputScores[1]);
                        break;
                    case 2:
                        player.setText(outputScores[0]);
                        desc.setText("spent quite some time getting" +outputScores[2]+ " points\nDate:" +outputScores[1]);
                        break;
                    case 8:
                        player.setText(outputScores[0]);
                        desc.setText("is so bad he only got" +outputScores[2]+  " points\nDate:" +outputScores[1]);
                        break;
                    case 9:
                        player.setText(outputScores[0]);
                        desc.setText("nearly didn't make it on here with his" +outputScores[2]+  " points\nDate:" +outputScores[1]);
                        break;
                    default:
                        player.setText(outputScores[0]);
                        desc.setText("doesn't get an individual text with his" +outputScores[2]+ " points\nDate:" +outputScores[1]);
                        break;
                }
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
