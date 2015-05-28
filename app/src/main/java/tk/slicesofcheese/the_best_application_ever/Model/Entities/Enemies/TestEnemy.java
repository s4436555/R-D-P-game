package tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemies;

import android.content.Context;
import android.graphics.drawable.Drawable;

import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.R;

/**
 * Created by jonathan on 28-5-15.
 */
public class TestEnemy extends Enemy{
    /**
     * Constructor of the Enemy class.
     *
     * @param xPos
     * @param yPos
     */
    public TestEnemy(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public Drawable getImage(Context context) {
        return context.getResources().getDrawable(R.drawable.dragon_lq);
    }
}
