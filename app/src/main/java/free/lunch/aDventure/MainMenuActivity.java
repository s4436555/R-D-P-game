/* Copyright 2015 Jan Potma, Jonathan Moerman, Mathis Sackers, Tom Nijholt

 This file is part of a:Dventure.

 a:Dventure is free software: you can redistribute it
 and/or modify it under the terms of the GNU General Public License as
 published by the Free Software Foundation, either version 2 of the
 License, or (at your option) any later version.

 a:Dventure is distributed in the hope that it will be
 useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 Public License for more details.

 You should have received a copy of the GNU General Public License along
 with a:Dventure. If not, see http://www.gnu.org/licenses/.
*/
package free.lunch.aDventure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainMenuActivity extends Activity {

    private LinearLayout plb;
    private LinearLayout scb;
    private LinearLayout stb;
    private LinearLayout crb;
    private LinearLayout heb;
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            boolean oob = false;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                plb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                scb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                stb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                crb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                heb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                return false;
            }

            if (v.getHeight() < event.getY() || event.getY() < 0)
                oob = true;

            switch (v.getId()) {
                case R.id.play_button:
                    if (oob)
                        plb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    else
                        plb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    break;
                case R.id.score_button:
                    if (oob)
                        scb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    else
                        scb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    break;
                case R.id.stats_button:
                    if (oob)
                        stb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    else
                        stb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    break;
                case R.id.credits_button:
                    if (oob)
                        crb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    else
                        crb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    break;
                case R.id.help_button:
                    if (oob)
                        heb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    else
                        heb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        plb = (LinearLayout) findViewById(R.id.play_button);
        scb = (LinearLayout) findViewById(R.id.score_button);
        stb = (LinearLayout) findViewById(R.id.stats_button);
        crb = (LinearLayout) findViewById(R.id.credits_button);
        heb = (LinearLayout) findViewById(R.id.help_button);

        plb.setOnTouchListener(listener);
        scb.setOnTouchListener(listener);
        stb.setOnTouchListener(listener);
        crb.setOnTouchListener(listener);
        heb.setOnTouchListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // DON'T inflate the menu; this DOESN'T add items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public void start(View view) {
        Intent myIntent = new Intent(MainMenuActivity.this, GameActivity.class);
        MainMenuActivity.this.startActivity(myIntent);
        //Start Game
    }

    public void gotoHighscore(View view) {
        Intent myIntent = new Intent(MainMenuActivity.this, HighScoreActivity.class);
        MainMenuActivity.this.startActivity(myIntent);
        //Start Highscore
    }

    public void gotoStatistics(View view) {
        Intent myIntent = new Intent(MainMenuActivity.this, StatisticsActivity.class);
        MainMenuActivity.this.startActivity(myIntent);
        //Start Stats
    }

    public void gotoCredits(View view) {
        Intent myIntent = new Intent(MainMenuActivity.this, CreditsActivity.class);
        MainMenuActivity.this.startActivity(myIntent);
        //Start Highscore
    }

    public void gotoHelp(View view) {
        Intent myIntent = new Intent(MainMenuActivity.this, HelpActivity.class);
        MainMenuActivity.this.startActivity(myIntent);
        //Start Highscore
    }
}
