package tk.slicesofcheese.the_best_application_ever;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jonathan on 29-4-15.
 */
public class Controller implements View.OnTouchListener {

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
