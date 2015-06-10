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
package free.lunch.aDventure.Model.Entities.Enemies;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.LinkedList;

import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.R;

/**
 * An Enemy that moves like a horse in chess.
 */
public class Horse extends Enemy {

    /**
     * Constructor of the Enemy class.
     *
     * @param xPos x coordinate of this Horse
     * @param yPos y coordinate of this Horse
     */
    public Horse(int xPos, int yPos) {
        super(xPos, yPos);
    }

    /**
     * Get the points rewarded to the player when killing this enemy
     * @return
     */
    @Override
    public int getPoints(){
        return 3;
    }

    /**
     * Get the Horse image
     * @param context
     * @return
     */
    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.horse);
    }

    /**
     * Calculates the distance between the player and the enemy
     * @param x
     * @param y
     * @param xPlyr
     * @param yPlyr
     * @return
     */
    private int[] calcDelta (int x, int y, int xPlyr, int yPlyr) {
        int d = Math.abs (xPlyr - x) + Math.abs(yPlyr - y);
        return new int[]{x, y, d};
    }

    /**
     * Returns a list of possible moves
     * @param xPlyr x position of player
     * @param yPlyr y position of player
     * @return
     */
    @Override
    public LinkedList<int[]> getMoves (int xPlyr, int yPlyr) {
        LinkedList<int[]> temp = new LinkedList<>();
        temp.add(calcDelta(xPos - 2, yPos - 1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos - 2, yPos + 1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos + 2, yPos - 1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos + 2, yPos + 1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos - 1, yPos - 2, xPlyr, yPlyr));
        temp.add(calcDelta(xPos + 1, yPos - 2, xPlyr, yPlyr));
        temp.add(calcDelta(xPos - 1, yPos + 2, xPlyr, yPlyr));
        temp.add(calcDelta(xPos + 1, yPos + 2, xPlyr, yPlyr));
        return temp;
    }
}
