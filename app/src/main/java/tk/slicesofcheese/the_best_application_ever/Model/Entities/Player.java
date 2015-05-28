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
package tk.slicesofcheese.the_best_application_ever.Model.Entities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;
import tk.slicesofcheese.the_best_application_ever.R;

/**
 * Created by jonathan on 30-4-15.
 */
public class Player implements CellEntity, Serializable {

    private int xPos;
    private int yPos;
    private int health;

    private static int MAXHEALTH; // a static variable may not be the best thing to have.

    /**
     * Constructor of the Player class.
     */
    public Player (int xPos, int yPos, int health) {
        MAXHEALTH = 5;
        this.health = health;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Returns the current amount of health of this user.
     * @return the current amount of health of this user
     */
    public int getHealth () {
        return health;
    }

    /**
     * Changes the amount of health of the Player, the resulting
     * amount of health will never by higher than the maximum amount of health.
     * @param newHealth new amount of health
     */
    public void setHealth (int newHealth) {
        health = newHealth;
        if (health > MAXHEALTH)
            health = MAXHEALTH;
    }

    /**
     * Checks if the Player has some health left.
     * @return true if Player has some health left, false otherwise
     */
    public boolean isAlive () {
        return (health > 0);
    }

    public int getY () {
        return yPos;
    }

    public int getX () {
        return xPos;
    }

    public void setCoordinates (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.happy_128);
    }
}
