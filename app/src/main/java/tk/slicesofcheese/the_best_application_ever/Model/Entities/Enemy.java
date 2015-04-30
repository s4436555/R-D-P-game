package tk.slicesofcheese.the_best_application_ever.Model.Entities;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;

/**
 * Created by jonathan on 30-4-15.
 */
public class Enemy implements CellEntity {

    private int xPos;
    private int yPos;

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
}
