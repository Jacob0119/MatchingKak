package project.capstone.com.matchingkak.list_game.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.databinding.ListGameCardviewBinding;


public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    // binding;
    ListGameCardviewBinding binding;
    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);

    }

    @Override
    public void notifyItemsetChanged() {
        notifyDataSetChanged();
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // cardview 생성하여 데이터랑 연결하기
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.list_game_cardview, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView =  view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view) {
         binding= DataBindingUtil.bind(view);
         binding.setCarditem(item);
        loadImage(binding.listGameViewPicture,item.getPicture(),view.getContext().getDrawable(R.drawable.main_logo));
    }

    @BindingAdapter({"imageUrl","error"})
    public static void loadImage(ImageView imageView , String imageUrl, Drawable error){
        RequestOptions requestOptions=new RequestOptions()
                                        .centerCrop()
                                         .circleCrop()
                                        .error(error)
                                        ;
        Glide.with(imageView.getContext()).load(imageUrl).apply(requestOptions).into(imageView);


    }

}
