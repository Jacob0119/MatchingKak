package project.capstone.com.matchingkak.Main.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import project.capstone.com.matchingkak.R;

/**
 * Created by Lee on 2018-03-22.
 */

public class HomeScrollListener extends RecyclerView.OnScrollListener {

    private int lastPosition=-1;
    private int firstPosition=-1;
    private Context context;
    private HomeFragment parent;
    public HomeScrollListener(Context context, HomeFragment parent){

        this.context=context;
        this.parent=parent;

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager=LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
            int lastVisible=layoutManager.findLastVisibleItemPosition();
            int firstVisible=layoutManager.findFirstVisibleItemPosition();

            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
            if (lastVisibleItemPosition == itemTotalCount) {
                //끝에 도달


            }else if(firstPosition>firstVisible){
                View item= layoutManager.findViewByPosition(firstVisible);
                setAnimation(item, R.anim.item_animation_fall_down);
                lastPosition=lastVisible;
                firstPosition=firstVisible;
            }
            else if(lastPosition<lastVisible){
                View item= layoutManager.findViewByPosition(lastVisible);
                setAnimation(item,R.anim.item_animation_fall_up);
                lastPosition=lastVisible;
                firstPosition=firstVisible;
            }

        }

       void setAnimation(View item,int Rid){


        Animation animation= AnimationUtils.loadAnimation(context,Rid);
        item.startAnimation(animation);


    }
}
