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
import java.util.LinkedList;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;
import tk.slicesofcheese.the_best_application_ever.R;

/**
 * Created by jonathan on 30-4-15.
 */
public abstract class Enemy implements CellEntity, Serializable {

    protected int xPos;
    protected int yPos;

    /**
     * Constructor of the Enemy class.
     */
    public Enemy (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
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
        return context.getResources().getDrawable(R.drawable.temp_64);
    }

    /**
     * Returns the possible moves for this Enemy this turn.
     * Enemy can adjust moveset if the player gets close.
     * @param xPlyr x position of player
     * @param yPlyr y position of player
     * @return a List containing {x, y, d} values where d is the distance to the player if the
     * Enemy is in range, d may need to get replaced if there are obstructions.
     */
    public abstract LinkedList<int[]> getMoves (int xPlyr, int yPlyr);
}
