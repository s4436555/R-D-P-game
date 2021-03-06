/* Copyright 2015 Jan Potma, Jonathan Moerman, Mathis Sackers, Tom Nijholt
*
* This file is part of a:Dventure.
*
* a:Dventure is free software: you can redistribute it
* and/or modify it under the terms of the GNU General Public License as
* published by the Free Software Foundation, either version 2 of the
* License, or (at your option) any later version.
*
* a:Dventure is distributed in the hope that it will be
* useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
* Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with a:Dventure. If not, see http://www.gnu.org/licenses/.
*/
package free.lunch.aDventure;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import free.lunch.aDventure.Controller.Controller;
import free.lunch.aDventure.Controller.Listener;
import free.lunch.aDventure.Model.Level;
import free.lunch.aDventure.Model.Score;
import free.lunch.aDventure.Model.StatisticsStorage;
import free.lunch.aDventure.View.GameView;

public class GameActivity extends MainMenuActivity {
    public static final String GAME_PREFS = "HighScoresFile";
    public static final String STATS_PREFS = "StatsFile";
    private Controller controller;
    private SharedPreferences gamePrefs;
    private SharedPreferences statsPrefs;
    private String name;

    /**
     * Initializes the GameActivity, chooses a cha, loads the HighScores, loads the Statistics and initializes a new Controller
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        statsPrefs = getSharedPreferences(STATS_PREFS, 0);

        int chat = (int) Math.round(Math.random() * 7);
        GameView gameView = (GameView) findViewById(R.id.gameView);
        gameView.setChat(chat);

        ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            switch (chat) {
                case 0:
                    getActionBar().setTitle("Dog");
                    getActionBar().setIcon(R.drawable.poodle);
                    break;
                case 1:
                    getActionBar().setTitle("Grandma");
                    getActionBar().setIcon(R.drawable.wolf);
                    break;
                case 2:
                    getActionBar().setTitle("Bob");
                    getActionBar().setIcon(R.drawable.happy);
                    break;
                case 3:
                    getActionBar().setTitle("Joe");
                    getActionBar().setIcon(R.drawable.waving);
                    break;
                case 4:
                    getActionBar().setTitle("Cool Kid");
                    getActionBar().setIcon(R.drawable.sunglasses);
                    break;
                case 5:
                    getActionBar().setTitle("Pizza");
                    getActionBar().setIcon(R.drawable.ppl);
                    break;
                case 6:
                    getActionBar().setTitle("Snake");
                    getActionBar().setIcon(R.drawable.snake);
                    break;
                default:
                    getActionBar().setTitle("Mom");
                    getActionBar().setIcon(R.drawable.dragon);
            }
        }

        // this is the view on which you will listen for touch events
        final View touchView = findViewById(R.id.touchView);

        controller = new Controller (this);
        Listener listener = new Listener(this, controller);

        if (savedInstanceState != null && savedInstanceState.getSerializable("level") != null){
            controller.setLevel((Level) savedInstanceState.getSerializable("level"));
            int exScore = savedInstanceState.getInt("score");
        }

        touchView.setOnTouchListener(listener);
    }

    @Override
    protected void onDestroy(){
        //setHighScore();
        super.onDestroy();
    }

    /**
     * Adds the current score to the list of HighScores if it is good enough
     */
    public void setHighScore(){
        if (name == null || name.equals("") || name.isEmpty()){
            name = "unnamed";
        }
        // Fix name
        name = name.replace("-", "");
        name = name.replace("|", "");

        int currentScore = controller.getScore();
        if (currentScore > 0){
            SharedPreferences.Editor scoreEdit = gamePrefs.edit();
            DateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
            String dateOutput = dateForm.format(new Date());
            String scores = gamePrefs.getString("highScores", "");
            if (scores != null) {
                if (scores.length() > 0) {
                    List<Score> scoreStrings = new ArrayList<Score>();
                    String[] exScores = scores.split("\\|");
                    for (String eSc : exScores) {
                        String[] parts = eSc.split(" - ");
                        scoreStrings.add(new Score(parts[0], parts[1], Integer.parseInt(parts[2])));
                    }
                    Score newScore = new Score(name, dateOutput, currentScore);
                    scoreStrings.add(newScore);
                    Collections.sort(scoreStrings);
                    StringBuilder scoreBuild = new StringBuilder("");
                    for (int s = 0; s < scoreStrings.size(); s++) {
                        if (s >= 10) break;//only want ten
                        if (s > 0) scoreBuild.append("|");//pipe separate the score strings
                        scoreBuild.append(scoreStrings.get(s).getScoreText());
                    }
                    //write to prefs
                    scoreEdit.putString("highScores", scoreBuild.toString());
                    scoreEdit.apply();
                    //we have existing scores
                } else {
                    scoreEdit.putString("highScores", "" + name + " - " + dateOutput + " - " + currentScore);
                    scoreEdit.apply();
                }
            }
        }
        finish();
    }

    /**
     * Loads previous statistics and adds the statistics of this session to it.
     */
    public void setStatistics(){
        SharedPreferences.Editor statsEdit = statsPrefs.edit();
        String statistics = statsPrefs.getString("stats", "");
        StatisticsStorage newStats = controller.getStats();
        if (statistics != null) {
            if (statistics.length() > 0) {
                String[] exStats = statistics.split("\\|");
                StatisticsStorage allStats = new StatisticsStorage(Integer.parseInt(exStats[0]), Integer.parseInt(exStats[1]), Integer.parseInt(exStats[2]), Integer.parseInt(exStats[3]), Integer.parseInt(exStats[4]), Integer.parseInt(exStats[5]), Integer.parseInt(exStats[6]), Integer.parseInt(exStats[7]));
                allStats.addStatistics(newStats);
                statsEdit.putString("stats", allStats.toString());
                statsEdit.apply();
            } else {
                statsEdit.putString("stats", newStats.toString());
                statsEdit.apply();
            }
        }
    }

    /**
     * Loads an AlertDialog which asks the player for a name to which the score gotten gets addmited to.
     */
    public void scorePopup(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setCancelable(false);

        alert.setTitle("You Died!");
        alert.setMessage("Enter your name:");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText();
                name = value.toString();
                setHighScore();
            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // DON'T inflate the menu; this DOESN'T add items to the action bar if it is present.
        // Maybe add HELP and stuff here later
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

    @Override
    protected void onSaveInstanceState(Bundle state) {
        int currentScore = controller.getScore();
        super.onSaveInstanceState(state);
        state.putSerializable("level", controller.getLevel());
        state.putInt("score", currentScore);
        super.onSaveInstanceState(state);
    }
}