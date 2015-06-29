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
package free.lunch.aDventure.Model.Entities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

import free.lunch.aDventure.R;

/**
 * A class for storing the player information.
 */
public class Player extends DynamicEntity implements Serializable {
    private int xPos;
    private int yPos;
    private boolean alive;

    /**
     * Constructor of the Player class.
     */
    public Player(int xPos, int yPos) {
        super(xPos, yPos);
        alive = true;
    }

    /**
     * Checks if this is alive.
     *
     * @return true if alive, false otherwise
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * Changes whether this is alive.
     *
     * @param alive whether this Player is alive or not
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets the Player image
     *
     * @param context
     * @return the Player image
     */
    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.player);
    }
}
