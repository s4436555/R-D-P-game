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
import android.widget.GridLayout;

public class MainMenuActivity extends Activity {

    View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                plb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                scb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                stb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                crb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                return false;
            }

            switch (v.getId()) {
                case R.id.play_button:
                    plb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    scb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    stb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    crb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    break;
                case R.id.score_button:
                    plb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    scb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    stb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    crb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    break;
                case R.id.stats_button:
                    plb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    scb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    stb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    crb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    break;
                case R.id.credits_button:
                    plb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    scb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    stb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    crb.setBackgroundColor(getResources().getColor(R.color.menu_item_selected));
                    break;
                default:
                    plb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    scb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    stb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    crb.setBackgroundColor(getResources().getColor(R.color.menu_item));
                    break;
            }
            return false;
        }
    };

    GridLayout plb;
    GridLayout scb;
    GridLayout stb;
    GridLayout crb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        plb = (GridLayout) findViewById(R.id.play_button);
        scb = (GridLayout) findViewById(R.id.score_button);
        stb = (GridLayout) findViewById(R.id.stats_button);
        crb = (GridLayout) findViewById(R.id.credits_button);

        plb.setOnTouchListener(listener);
        scb.setOnTouchListener(listener);
        stb.setOnTouchListener(listener);
        crb.setOnTouchListener(listener);
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

    public void start(View view){
        Intent myIntent = new Intent(MainMenuActivity.this, GameActivity.class);
        MainMenuActivity.this.startActivity(myIntent);
        //Start spel
    }
}
