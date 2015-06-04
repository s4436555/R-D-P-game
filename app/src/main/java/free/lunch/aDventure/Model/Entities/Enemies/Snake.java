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
 * Created by jonathan on 4-6-15.
 */
public class Snake extends Enemy {

    private boolean moveV;

    /**
     * Constructor of the Enemy class.
     *
     * @param xPos
     * @param yPos
     */
    public Snake(int xPos, int yPos) {
        super(xPos, yPos);
        moveV = true;
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.snake_128);
    }

    private int[] calcDelta (int x, int y, int xPlyr, int yPlyr) {
        int d = Math.abs (xPlyr - x) + Math.abs(yPlyr - y);
        return new int[]{x, y, d};
    }

    @Override
    public LinkedList<int[]> getMoves (int xPlyr, int yPlyr) {
        LinkedList<int[]> temp = new LinkedList<>();

        final int d = Math.abs (xPlyr - xPos) + Math.abs(yPlyr - yPos);

        if (moveV) {
            temp.add(calcDelta(xPos -1, yPos -1, xPlyr, yPlyr));
            temp.add(calcDelta(xPos +1, yPos +1, xPlyr, yPlyr));
            temp.add(calcDelta(xPos +1, yPos -1, xPlyr, yPlyr));
            temp.add(calcDelta(xPos -1, yPos +1, xPlyr, yPlyr));
            moveV = false;
        } else {
            temp.add(calcDelta(xPos -1, yPos, xPlyr, yPlyr));
            temp.add(calcDelta(xPos +1, yPos, xPlyr, yPlyr));
            temp.add(calcDelta(xPos, yPos -1, xPlyr, yPlyr));
            temp.add(calcDelta(xPos, yPos +1, xPlyr, yPlyr));
            moveV = true;
        }

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