package tk.slicesofcheese.the_best_application_ever.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by jonathan on 30-4-15.
 */
public interface CellEntity {
    Drawable getImage(Context context);

    int getX();
    int getY();
}
