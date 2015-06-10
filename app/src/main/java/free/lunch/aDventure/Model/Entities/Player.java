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

import free.lunch.aDventure.Model.CellEntity;
import free.lunch.aDventure.R;

/**
 * A class for storing the player information.
 */
public class Player implements CellEntity, Serializable {

    private int xPos;
    private int yPos;

    /**
     * Constructor of the Player class.
     */
    public Player (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Get the y-coordinate of the player's location
     * @return the y-coordinate of the player's location
     */
    @Override
    public int getY () {
        return yPos;
    }

    /**
     * Get the x-coordinate of the player's location
     * @return the x-coordinate of the player's location
     */
    @Override
    public int getX () {
        return xPos;
    }

    /**
     * Sets the given coordinates as the new coordinates of the player
     * @param xPos new x coordinate
     * @param yPos new y coordinate
     */
    @Override
    public void setCoordinates (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Gets the Player image
     * @param context
     * @return the Player image
     */
    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.player);
    }
}
