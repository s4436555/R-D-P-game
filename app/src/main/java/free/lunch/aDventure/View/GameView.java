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
package free.lunch.aDventure.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import free.lunch.aDventure.Model.CellEntity;
import free.lunch.aDventure.Model.Entities.DynamicEntity;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Entities.Portal;
import free.lunch.aDventure.Model.Entities.Wall;
import free.lunch.aDventure.Model.Level;
import free.lunch.aDventure.R;

/**
 * Displays the level.
 */
public class GameView extends View implements Observer {

    Bitmap persistent;
    private Level level;
    private float cell_size;
    private int margin_horizontal;
    private int margin_vertical;
    private int score = 0;
    private int bubble_margin = 10;
    private int stage = 1;
    private int scoreHeight = 40;
    private float bubble_height = 0;
    private int chat = 0;
    private float textSize;
    private int screenW;
    private int screenH;
    private boolean bgValid = false;

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public GameView(Context context) {
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

        bgValid = false;

        if (level == null)
            return;

        this.screenW = w;
        this.screenH = h;

        // calculate the dimensions
        cell_size = Math.min(w / level.getXSize(), h / level.getYSize());

        // calculate the required margin
        margin_horizontal = 12 + ((w - Math.round(cell_size * level.getXSize())) / 2); //------UNCLEAN-----
        margin_vertical = 12 + ((h - Math.round(cell_size * level.getYSize())) / 2); //------UNCLEAN-----

        //------UNCLEAN-----
        cell_size = Math.min((w - 40) / level.getXSize(), (h - 30) / level.getYSize());

        final float scale = getContext().getResources().getDisplayMetrics().density;
        bubble_margin = (int) (6 * scale);
        textSize = 16 * scale;
    }

    private void drawBackground(Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble);
        if (d != null) {
            d.setBounds(
                    margin_horizontal,
                    margin_vertical,
                    canvas.getWidth() - margin_horizontal,
                    canvas.getHeight() - margin_vertical
            );
            d.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (level == null) {
            System.out.println("No level to draw!");
            return;
        }

        if (!bgValid) {
            persistent = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_4444);
            Canvas temp = new Canvas(persistent);
            this.drawBackground(temp);
            this.drawStage(temp);
            this.drawConversation(temp);
            this.drawLevel(temp);
            bgValid = true;
        }
        canvas.drawBitmap(persistent, 0, 0, null);

        this.drawPlayer(canvas);
        this.drawEnemies(canvas);
        this.drawPortal(canvas);
        this.drawScore(canvas);
    }

    private void drawConversation(Canvas canvas) {
        String[] texts = new String[0];
        boolean[] positions = new boolean[0];

        // Draws the text above the game
        switch (chat) {
            case 0:
                texts = new String[]{"Is it grey?", "Ok.", "Think of a color.", "I'm a magician.",
                        "Woof do you do for a living?", "Ask me a question.", "Let's try this:"};
                positions = new boolean[]{true, false, true, true, false, true, true};
                break;
            case 1:
                texts = new String[]{"Uhmm...", "Are you trying to say I'm ugly?",
                        "Why do you have such big ears?", "So I can see better.",
                        "Why do you have such big eyes?", "Yea, wanted something different.",
                        "New profile picture?"};
                positions = new boolean[]{true, false, true, false, true, false, true};
                break;
            case 2:
                texts = new String[]{"You planet!", "Stop it.", "How do you organize a space party?",
                        "Please don't.", "Wanna hear another joke?", "Oh god...",
                        "Because his grandma told him!", "Why?"};
                positions = new boolean[]{true, false, true, false, true, false, true, false};
                break;
            case 3:
                texts = new String[]{"...", "from the great wall of China.",
                        "You can see the moon", "Fun fact:", "See you tomorrow",
                        "Ok, CU then.", "6pm?"};
                positions = new boolean[]{false, true, true, true, false, true, false};
                break;
            case 4:
                texts = new String[]{"Why do I even bother", "He was lucky it was a soft drink.",
                        "No?", "got hit in the head with a softdrink?",
                        "Did you hear about the guy who", "Not much", "So, what's up?"};
                positions = new boolean[]{false, true, false, true, true, true, false};
                break;
            case 5:
                texts = new String[]{"OK.", "DO NOT REPLY", "Expected Delivery time is 20:17",
                        "Your Pizza will be delivered shortly", "Jim&Jones Pizza Service",
                        "Thank you for your purchase at", "CONFIRMATION"};
                positions = new boolean[]{true, false, false, false, false, false, false};
                break;
            case 6:
                texts = new String[]{"Sssszz..", "You shush.", "Shhhhss", "Shhh", "shhsss",
                        "Shush!", "Sssszzsh..."};
                positions = new boolean[]{false, true, false, true, false, true, false};
                break;
            default:
                texts = new String[]{"Ok.", "Do your homework!", "LoL", "Ofc",
                        "Did you brush ur teeth?", "Hey, hon, how's everything going?"};
                positions = new boolean[]{true, false, false, true, false, false};
        }
        drawBubblesOnTop(texts, positions, canvas);
        bubble_height = 0;
    }

    private void drawBubblesOnTop(String[] texts, boolean[] positions, Canvas canvas) {
        for (int i = 0; i < texts.length; i++) {
            if (positions[i]) {
                drawBubbleRight(texts[i], canvas);
            } else {
                drawBubbleLeft(texts[i], canvas);
            }
        }
    }

    private void drawLevel(Canvas canvas) {
        for (int x = 0; x < level.getXSize(); x++) {
            for (int y = 0; y < level.getYSize(); y++) {
                CellEntity entity = level.getEntity(x, y);
                if (entity instanceof Wall)
                    drawEntity(canvas, entity);
            }
        }
    }

    private void drawPlayer(Canvas canvas) {
        Player player = level.getPlayer();
        if (player != null) {
            drawEntity(canvas, player);
            //drawDynamicEntity(canvas, player);
        }
    }

    private void drawEnemies(Canvas canvas) {
        int nr = level.getEnemyCount();
        for (int i = 0; i < nr; i++) {
            drawEntity(canvas, level.getEnemy(i));
            //drawDynamicEntity(canvas, level.getEnemy(i));
        }
    }

    private void drawPortal(Canvas canvas) {
        Portal portal = level.getPortal();
        if (portal != null) {
            drawEntity(canvas, portal);
        }
    }

    private void drawEntity(Canvas canvas, CellEntity entity) {
        //------UNCLEAN-----
        int px = margin_horizontal + 5 + Math.round(entity.getX() * cell_size);
        int py = margin_vertical + 5 + Math.round(entity.getY() * cell_size);

        Drawable d = entity.getImage(getContext());
        d.setBounds(px, py, Math.round(px + cell_size), Math.round(py + cell_size));
        d.draw(canvas);
    }

    private void drawDynamicEntity(Canvas canvas, DynamicEntity entity) {
        //------UNCLEAN-----
        int px = margin_horizontal + 5 + Math.round(entity.getOldX() * cell_size);
        int py = margin_vertical + 5 + Math.round(entity.getOldY() * cell_size);

        Drawable d = entity.getImage(getContext());
        d.setBounds(px, py, Math.round(px + cell_size - 30), Math.round(py + cell_size - 30));
        d.draw(canvas);
    }

    /**
     * Draws the bubble showing the score
     */
    private void drawScore(Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble);

        if (d != null) {
            Rect bounds = new Rect();
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(textSize);
            p.setAntiAlias(true);
            p.getTextBounds("stgre: 0000", 0, 11, bounds);

            bounds.inset(-bubble_margin, -bubble_margin);
            bounds.offsetTo(canvas.getWidth() - bounds.width() - margin_horizontal, canvas.getHeight() - margin_vertical);

            d.setBounds(bounds);

            scoreHeight = bounds.height();

            bounds.inset(bubble_margin, bubble_margin);
            d.draw(canvas);
            canvas.drawText("score: " + score, bounds.left, (float) (bounds.bottom - (bubble_margin * 0.4)), p);
        }
    }

    /**
     * Draws the bubble that tells the stage
     */
    private void drawStage(Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble2);

        if (d != null) {
            Rect bounds = new Rect();
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(textSize);
            p.getTextBounds(" stgre: 000", 0, 11, bounds);
            p.setAntiAlias(true);

            bounds.inset(-bubble_margin, -bubble_margin);
            bounds.offsetTo(margin_horizontal, canvas.getHeight() - margin_vertical + scoreHeight);

            d.setBounds(bounds);

            bounds.inset(bubble_margin, bubble_margin);
            d.draw(canvas);
            canvas.drawText("stage: " + stage, bounds.left + bubble_margin, (float) (bounds.bottom - (bubble_margin * 0.4)), p);
        }
    }

    /**
     * Setter for score
     *
     * @param score of the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Setter for stage
     *
     * @param stage of the player
     */
    public void setStage(int stage) {
        this.stage = stage;
        bgValid = false;
    }

    /**
     * Changes the level that needs to be drawn.
     *
     * @param level the level that needs to be drawn
     */
    public void setLevel(Level level) {
        this.level = level;
        bgValid = false;

        // NEW dimensions in case lvl size changed
        // calculate the dimensions
        cell_size = Math.min(screenW / level.getXSize(), screenH / level.getYSize());

        // calculate the required margin
        margin_horizontal = 12 + ((screenW - Math.round(cell_size * level.getXSize())) / 2); //------UNCLEAN-----
        margin_vertical = 12 + ((screenH - Math.round(cell_size * level.getYSize())) / 2); //------UNCLEAN-----

        //------UNCLEAN-----
        cell_size = Math.min((screenW - 40) / level.getXSize(), (screenW - 30) / level.getYSize());

        final float scale = getContext().getResources().getDisplayMetrics().density;
        bubble_margin = (int) (6 * scale);
        textSize = 16 * scale;
    }

    /**
     * Returns the coordinates of the edges of the level on the view.
     *
     * @return the coordinates of the edges of the level on the view,
     * upper right corner first, followed by the bottom right corner.
     */
    public int[] getCoordinates() {
        int[] temp = new int[4];

        temp[0] = margin_horizontal;
        temp[1] = margin_vertical;
        temp[2] = this.getWidth() - margin_horizontal;
        temp[3] = this.getHeight() - margin_vertical;

        return temp;
    }

    /**
     * Draws a bubble on top of the screen on the left
     *
     * @param text   in the bubble
     * @param canvas
     */
    private void drawBubbleLeft(String text, Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble2);

        if (d != null) {
            Rect bounds = new Rect();
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(textSize);
            p.getTextBounds(text + "g", 0, text.length() + 1, bounds);
            p.setAntiAlias(true);

            bounds.inset(-bubble_margin, -bubble_margin);

            bubble_height += bounds.height();

            bounds.offsetTo(margin_horizontal, (int) (margin_vertical - bubble_height));

            d.setBounds(bounds);


            bounds.inset(bubble_margin, bubble_margin);
            d.draw(canvas);
            canvas.drawText(text, bounds.left + bubble_margin, (float) (bounds.bottom - (bubble_margin * 0.4)), p);
        }
    }

    /**
     * Draws a bubble on top of the screen on the right
     *
     * @param text   in the bubble
     * @param canvas
     */
    private void drawBubbleRight(String text, Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.bubble);

        if (d != null) {
            Rect bounds = new Rect();
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(textSize);
            p.getTextBounds(text + "g", 0, text.length() + 1, bounds);
            p.setAntiAlias(true);

            bounds.inset(-bubble_margin, -bubble_margin);

            bubble_height += bounds.height();

            bounds.offsetTo(canvas.getWidth() - bounds.width() - margin_horizontal, (int) (margin_vertical - bubble_height));

            d.setBounds(bounds);

            bounds.inset(bubble_margin, bubble_margin);
            d.draw(canvas);
            canvas.drawText(text, bounds.left, (float) (bounds.bottom - (bubble_margin * 0.4)), p);
        }
    }

    public void setChat(int chat) {
        this.chat = chat;
        bgValid = false;
    }
}
