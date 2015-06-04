package free.lunch.aDventure.Model.Entities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

import free.lunch.aDventure.Model.CellEntity;
import free.lunch.aDventure.R;

/**
 * Created by Tom on 4-6-2015.
 */
public class Portal implements CellEntity, Serializable {

    private int xPos;
    private int yPos;

    /**
     * Constructor of the wall class.
     */
    public Portal (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.portal2_128);
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }

    @Override
    public void setCoordinates(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
