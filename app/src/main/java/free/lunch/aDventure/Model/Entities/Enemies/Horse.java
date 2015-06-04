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
 * Created by jonathan on 1-6-15.
 */
public class Horse extends Enemy {

    /**
     * Constructor of the Enemy class.
     *
     * @param xPos
     * @param yPos
     */
    public Horse(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.horse_128);
    }

    private int[] calcDelta (int x, int y, int xPlyr, int yPlyr) {
        int d = Math.abs (xPlyr - x) + Math.abs(yPlyr - y);
        return new int[]{x, y, d};
    }

    @Override
    public LinkedList<int[]> getMoves (int xPlyr, int yPlyr) {
        LinkedList<int[]> temp = new LinkedList<>();

        final int d = Math.abs (xPlyr - xPos) + Math.abs(yPlyr - yPos);

        temp.add(calcDelta(xPos -2, yPos -1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos -2, yPos +1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos +2, yPos -1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos +2, yPos +1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos -1, yPos -2, xPlyr, yPlyr));
        temp.add(calcDelta(xPos +1, yPos -2, xPlyr, yPlyr));
        temp.add(calcDelta(xPos -1, yPos +2, xPlyr, yPlyr));
        temp.add(calcDelta(xPos +1, yPos +2, xPlyr, yPlyr));
        Collections.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] lhs, int[] rhs) {
                Random random = new Random();

                if (lhs[2] > rhs[2]) {
                    return 1;
                } else if (lhs[2] < rhs[2]) {
                    return -1;
                }
                return random.nextInt(3) - 1;
            }
        });
        return temp;
    }
}
