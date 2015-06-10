package free.lunch.aDventure.Model.Entities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

import free.lunch.aDventure.Model.CellEntity;
import free.lunch.aDventure.R;

/**
 * A class for the Portal
 */
public class Portal implements CellEntity, Serializable {

    private int xPos;
    private int yPos;

    /**
     * Constructor of the Portal class.
     */
    public Portal (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Gets the Portal image
     * @param context
     * @return the Portal image
     */
    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.portal);
    }

    /**
     * Gets the x-coordinate of the portal
     * @return the x-coordinate of the portal
     */
    @Override
    public int getX() {
        return xPos;
    }

    /**
     * Gets the y-coordinate of the portal
     * @return the y-coordinate of the portal
     */
    @Override
    public int getY() {
        return yPos;
    }

    /**
     * Sets the given coordinates as the portal's new coordinates
     * @param xPos new x coordinate
     * @param yPos new y coordinate
     */
    @Override
    public void setCoordinates(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
