package free.lunch.aDventure.Model.Entities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import free.lunch.aDventure.Model.CellEntity;

/**
 * Created by jonathan on 29-6-15.
 */
public abstract class DynamicEntity implements CellEntity {
    protected int xPos;
    protected int yPos;
    protected int oldX;
    protected int oldY;

    public DynamicEntity(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.oldX = xPos;
        this.oldY = yPos;
    }

    @Override
    public abstract Drawable getImage(Context context);

    /**
     * Get the y-coordinate of this.
     *
     * @return the y-coordinate of this
     */
    @Override
    public int getY() {
        return yPos;
    }

    /**
     * Get the x-coordinate of this.
     *
     * @return the x-coordinate of this
     */
    @Override
    public int getX() {
        return xPos;
    }

    /**
     * Get the previous y-coordinate of this.
     *
     * @return the previous y-coordinate of this
     */
    public int getOldY() {
        return oldY;
    }

    /**
     * Get the previous x-coordinate of this.
     *
     * @return the previous x-coordinate of this
     */
    public int getOldX() {
        return oldX;
    }

    public void idle() {
        this.oldX = xPos;
        this.oldY = yPos;
    }

    /**
     * Changes the coordinates of this.
     *
     * @param xPos new x coordinate
     * @param yPos new y coordinate
     */
    @Override
    public void setCoordinates(int xPos, int yPos) {
        this.oldX = this.xPos;
        this.oldY = this.yPos;
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
