package project.capstone.com.matchingkak;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

/**
 * Created by amco1 on 2018-03-22.
 */

public class AnimationManager {
    public static void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);

       // recyclerView.getAdapter().notifyDataSetChanged();
        //recyclerView.scheduleLayoutAnimation();
    }
}
