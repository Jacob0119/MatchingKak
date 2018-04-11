package project.capstone.com.matchingkak.list_game.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import project.capstone.com.matchingkak.ActivityStarterManager;
import project.capstone.com.matchingkak.GameItemViewUtils;
import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.databinding.ListGameCardviewBinding;
import project.capstone.com.matchingkak.list_game.ListGameAdapterContract;
import project.capstone.com.matchingkak.restAPI.APIUrl;


public class CardPagerAdapter extends PagerAdapter implements ListGameAdapterContract.view,ListGameAdapterContract.model,OnClickListener{

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private OnClickListener listener;
    // binding;
    ListGameCardviewBinding binding;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        setOnClickListener(this);
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
    public void setChangeRootBackground(View v, int position) {
            String imageUrl=APIUrl.API_BASE_URL+mData.get(position).getPicture();


        Glide.with(v.getContext()).load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new MultiTransformation<Bitmap>(new CenterCrop(),new BlurTransformation(),new ColorFilterTransformation(Color.BLACK))))
                .into((ImageView)v);
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
    public Object instantiateItem(ViewGroup container, final int position) {

        // cardview 생성하여 데이터랑 연결하기
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.list_game_cardview, container, false);
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(v,position);
            }
        });
        bind(mData.get(position), view);
        CardView cardView =  view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);

        if(mViews.size()<=position)
            mViews.add(position, cardView);
        else
            mViews.set(position,cardView);
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
            GameItemViewUtils.set_state(binding.listGameViewState,item.getState());
         loadImage(binding.listGameViewPicture, APIUrl.API_BASE_URL+item.getPicture(),view.getContext().getDrawable(R.drawable.main_logo));
    }

    @BindingAdapter({"imageUrl","error"})
    public static void loadImage(ImageView imageView , String imageUrl, Drawable error){
        RequestOptions requestOptions=new RequestOptions()
                                        .centerCrop()
                                        .error(error)
                                        ;
        Glide.with(imageView.getContext()).load(imageUrl).apply(requestOptions).into(imageView);


    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnClickListener(OnClickListener clickListener) {

        listener=clickListener;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public void addItems(List items, boolean isUpper) {

        if(isUpper){
            mData=items;
        }else{

            mData.addAll(items);
        }
    }

    @Override
    public void OnClick(View v, int position) {
        CardItem item=mData.get(position);
        ActivityStarterManager.StartGameDetailActivity(v.getContext(),item.getGmNo());
    }
}
