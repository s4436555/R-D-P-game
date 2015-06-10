package free.lunch.aDventure;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class StatisticsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Drawable icon = getResources().getDrawable(R.drawable.chart_ic);
        ActionBar actionBar = getActionBar();
        if (icon != null && actionBar != null) {
            actionBar.setIcon(icon);
        }


        SharedPreferences statsPrefs = getSharedPreferences(GameActivity.STATS_PREFS, 0);
        String[] savedStats = statsPrefs.getString("stats", "").split("\\|");

        if (savedStats.length == 8) {
            TextView v = (TextView) findViewById(R.id.stats_demon_desc);
            v.setText("You killed " + savedStats[7] + " demons.");
            v = (TextView) findViewById(R.id.stats_snake_desc);
            v.setText("You killed " + savedStats[0] + " sneks.");
            v = (TextView) findViewById(R.id.stats_horse_desc);
            v.setText(savedStats[3] + " poor little horsies.");
            v = (TextView) findViewById(R.id.stats_wolf_desc);
            v.setText(savedStats[2] + " big bad wolves.");
            v = (TextView) findViewById(R.id.stats_rat_desc);
            v.setText(savedStats[1] + " nasty rats.");
            v = (TextView) findViewById(R.id.stats_stage_desc);
            v.setText("You went through " + savedStats[5] + " dungeon levels.");
            v = (TextView) findViewById(R.id.stats_step_desc);
            v.setText("But I would walk " + savedStats[6] + " miles\nAnd I would walk " +
                    savedStats[6] + " more\nJust to be the man who walked " + (Integer.parseInt(savedStats[6])*2) + " miles" +
                    "\nTo fall down at your door");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_statistics, menu);
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
