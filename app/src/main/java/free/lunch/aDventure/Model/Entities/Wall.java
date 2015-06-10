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
 * A class for the Wall
 */
public class Wall implements CellEntity, Serializable {

    private int xPos;
    private int yPos;

    /**
     * Constructor of the Wall class
     */
    public Wall (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Gets the Wall image
     * @param context
     * @return the Wall image
     */
    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.mountain);
    }

    /**
     * Gets the y-coordinate of this wall
     * @return the y-coordinate of this wall
     */
    @Override
    public int getX() {
        return xPos;
    }

    /**
     * Gets the x-coordinate of this wall
     * @return the x-coordinate of this wall
     */
    @Override
    public int getY() {
        return yPos;
    }

    /**
     * Sets the given coordinates as the new coordinates of this wall
     * @param xPos new x coordinate
     * @param yPos new y coordinate
     */
    @Override
    public void setCoordinates(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
