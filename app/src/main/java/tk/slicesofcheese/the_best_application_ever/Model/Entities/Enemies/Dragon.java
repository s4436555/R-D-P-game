package tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemies;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.R;

/**
 * Created by jonathan on 28-5-15.
 */
public class Dragon extends Enemy {

    private int[][] moves;

    /**
     * Constructor of the Enemy class.
     *
     * @param xPos
     * @param yPos
     */
    public Dragon(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.dragon_128);
    }

    private int[] calcDelta (int x, int y, int xPlyr, int yPlyr) {
        int d = Math.abs (xPlyr - x) + Math.abs(yPlyr - y);
        return new int[]{x, y, d};
    }

    @Override
    public LinkedList<int[]> getMoves (int xPlyr, int yPlyr) {
        LinkedList<int[]> temp = new LinkedList<>();

        final int d = Math.abs (xPlyr - xPos) + Math.abs(yPlyr - yPos);

        temp.add(calcDelta(xPos -1, yPos -1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos +1, yPos +1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos +1, yPos -1, xPlyr, yPlyr));
        temp.add(calcDelta(xPos - 1, yPos + 1, xPlyr, yPlyr));
        Collections.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] lhs, int[] rhs) {
                if (d > 4) {
                    Random random = new Random();
                    return random.nextInt();
                }
                if (lhs[2] > rhs[2]) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return temp;
    }
}
