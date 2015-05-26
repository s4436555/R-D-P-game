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
public class Wall implements CellEntity, Serializable {

    private int xPos;
    private int yPos;

    /**
     * Constructor of the wall class.
     */
    public Wall (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.wall_placeholder);
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }
}
