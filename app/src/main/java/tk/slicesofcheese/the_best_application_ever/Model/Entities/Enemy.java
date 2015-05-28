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
public class Enemy implements CellEntity, Serializable {

    private int xPos;
    private int yPos;
    private int[][] moves;


    /**
     * Constructor of the Enemy class.
     */
    public Enemy (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

        genMoves();
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

    private void genMoves () {
        moves = new int[4][2];
        moves[0][0] = -1; // left
        moves[0][1] =  0;

        moves[1][0] =  1; // right
        moves[1][1] =  0;

        moves[2][0] =  0; // up
        moves[2][1] = -1;

        moves[3][0] =  0; // down
        moves[3][1] =  1;
    }

    public int[][] getMoves () {
        return moves;
    }
}
