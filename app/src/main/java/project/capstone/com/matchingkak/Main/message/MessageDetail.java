package project.capstone.com.matchingkak.Main.message;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.message.data.MSGData;
import project.capstone.com.matchingkak.Message.Message2Activity;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.config;
import project.capstone.com.matchingkak.databinding.MessageDetailBinding;
import project.capstone.com.matchingkak.restAPI.APIUrl;

public class MessageDetail extends AppCompatActivity implements View.OnClickListener {
    private MSGData mData;
    private MessageDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.message_detail);
        binding= DataBindingUtil.setContentView(this,R.layout.message_detail);

        mData=getIntent().getParcelableExtra(config.MSG_KEY);

        init();



    }


    void init(){


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Glide.with(this).load(APIUrl.API_BASE_URL+mData.getTm_img()).apply(RequestOptions.circleCropTransform()).into(binding.msgDetailProfilePicture);
        binding.msgDetailTmName.setText("teamGod");
        binding.msgDetailUsernick.setText(mData.getMb_nick());
        binding.msgDetailTitle.setText(mData.getMs_title());
        binding.msgDetailDate.setText(mData.getMs_send_date());
        binding.msgDetailMsgcontent.setText(mData.getMs_content());
        binding.msgDetailReplybtn.setOnClickListener(this);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);}
    }


    @Override
    public void onClick(View view) {


        Intent intent=new Intent(this, Message2Activity.class);
        intent.putExtra(config.MB_NICK,mData.getMb_nick());
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_up_for_new,R.anim.anim_slide_up_for_current);

    }
}
