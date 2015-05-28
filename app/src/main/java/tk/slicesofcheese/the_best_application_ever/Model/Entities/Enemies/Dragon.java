package tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemies;

import android.content.Context;
import android.graphics.drawable.Drawable;

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

        genMoves();
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.dragon_lq);
    }

    private void genMoves () {
        moves = new int[4][2];
        moves[0][0] = -1; // left
        moves[0][1] = -1;

        moves[1][0] =  1; // right
        moves[1][1] =  1;

        moves[2][0] =  1; // up
        moves[2][1] = -1;

        moves[3][0] = -1; // down
        moves[3][1] =  1;
    }

    @Override
    public int[][] getMoves() {
        return moves;
    }
}
