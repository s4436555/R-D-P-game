package free.lunch.aDventure;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class StatisticsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        getActionBar().setIcon(R.drawable.chart_ic);

        //TODO: Load stats

        TextView v = (TextView) findViewById(R.id.stats_snake_desc);
        v.setText("You killed " + 2 + " sneks.");
        v = (TextView) findViewById(R.id.stats_horse_desc);
        v.setText( 20 + " poor little horsies.");
        v = (TextView) findViewById(R.id.stats_wolf_desc);
        v.setText( 299 + "big bad wolves.");
        v = (TextView) findViewById(R.id.stats_dragon_desc);
        v.setText( 6 + " mighty dragons.");
        v = (TextView) findViewById(R.id.stats_stage_desc);
        v.setText( "You went through " + 100 + " dungeon levels.");
        v = (TextView) findViewById(R.id.stats_step_desc);
        v.setText("But I would walk " + 20 + " miles\nAnd I would walk " +
                20 + " more\nJust to be the man who walked "+40+" miles" +
                "\nTo fall down at your door");
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
