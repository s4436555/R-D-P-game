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
 * This Enemy moves with diagonal steps.
 */
public class Rat extends Enemy {

    /**
     * Constructor of the Rat class.
     *
     * @param xPos x coordinate of this
     * @param yPos y coordinate of this
     */
    public Rat(int xPos, int yPos) {
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
     * Get the Rat image
     * @param context
     * @return
     */
    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.rat);
    }

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
        temp.add(calcDelta(xPos - 1, yPos - 1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos + 1, yPos + 1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos + 1, yPos - 1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos - 1, yPos + 1, xPlyr, yPlyr));
        return temp;
    }
}
