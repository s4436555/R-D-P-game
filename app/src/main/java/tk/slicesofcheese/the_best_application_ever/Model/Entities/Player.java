package tk.slicesofcheese.the_best_application_ever.Model.Entities;

import tk.slicesofcheese.the_best_application_ever.Model.CellEntity;

/**
 * Created by jonathan on 30-4-15.
 */
public class Player implements CellEntity {

    private int xPos;
    private int yPos;
    private int health;

    private static int MAXHEALTH; // a static variable may not be the best thing to have.

    /**
     * Constructor of the Player class.
     */
    public Player (int xPos, int yPos, int health) {
        MAXHEALTH = 5;
        this.health = health;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Returns the current amount of health of this user.
     * @return the current amount of health of this user
     */
    public int getHealth () {
        return health;
    }

    /**
     * Changes the amount of health of the Player, the resulting
     * amount of health will never by higher than the maximum amount of health.
     * @param newHealth new amount of health
     */
    public void setHealth (int newHealth) {
        health = newHealth;
        if (health > MAXHEALTH)
            health = MAXHEALTH;
    }

    /**
     * Checks if the Player has some health left.
     * @return true if Player has some health left, false otherwise
     */
    public boolean isAlive () {
        return (health > 0);
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
