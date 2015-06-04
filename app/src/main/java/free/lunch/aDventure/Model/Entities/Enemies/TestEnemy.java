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

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.R;

/**
 * Created by jonathan on 28-5-15.
 */
public class TestEnemy extends Enemy{

    /**
     * Constructor of the Enemy class.
     *
     * @param xPos
     * @param yPos
     */
    public TestEnemy(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.temp_128);
    }

    private int[] calcDelta (int x, int y, int xPlyr, int yPlyr) {
        int d = Math.abs (xPlyr - x) + Math.abs(yPlyr - y);
        return new int[]{x, y, d};
    }

    @Override
    public LinkedList<int[]> getMoves (int xPlyr, int yPlyr) {
        LinkedList<int[]> temp = new LinkedList<>();
        temp.add(calcDelta(xPos -1, yPos, xPlyr, yPlyr));
        temp.add(calcDelta(xPos +1, yPos, xPlyr, yPlyr));
        temp.add(calcDelta(xPos, yPos -1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos, yPos +1, xPlyr, yPlyr));
        Collections.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] lhs, int[] rhs) {
                Random random = new Random();

                if (lhs[2] > rhs[2]) {
                    return 1;
                } else if (lhs[2] < rhs[2]){
                    return -1;
                }
                return random.nextInt(3) - 1;
            }
        });
        return temp;
    }
}
