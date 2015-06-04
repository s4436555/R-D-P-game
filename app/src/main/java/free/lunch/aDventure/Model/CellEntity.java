package free.lunch.aDventure.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by jonathan on 30-4-15.
 */
public interface CellEntity {
    Drawable getImage(Context context);

    int getX();
    int getY();

    /**
     * Changes the coordinates of this.
     * @param xPos new x coordinate
     * @param yPos new y coordinate
     */
    void setCoordinates (int xPos, int yPos);
}
