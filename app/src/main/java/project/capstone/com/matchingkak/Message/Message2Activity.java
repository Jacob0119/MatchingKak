package project.capstone.com.matchingkak.Message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import project.capstone.com.matchingkak.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message2Activity extends AppCompatActivity {
private String mb_nick;
    private LinearLayout send_btn;
    private TextView receiver;
    private Toolbar toolbar;
    private EditText title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message2);


        init();
    }


    void init(){

        Intent intent=getIntent();
        mb_nick=intent.getStringExtra("mb_nick");
        receiver=(TextView)findViewById(R.id.message_reciever_text);
        receiver.setText(mb_nick);
        toolbar=(Toolbar)findViewById(R.id.message_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("메시지 보내기");

        findViewById(R.id.message_edit_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });


        title=(EditText)findViewById(R.id.message_title_edit);
        content=(EditText)findViewById(R.id.message_edit);



        send_btn=(LinearLayout) findViewById(R.id.message_send_btn);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final MaterialDialog progressD=  new MaterialDialog.Builder(Message2Activity.this)
                        .title("메시지 전송")
                        .content("전송중...")
                        .progress(true, 0)
                       .build();
                String t=title.getText().toString();
                String c=content.getText().toString();

                MessageService.getRetrofit(Message2Activity.this).send(mb_nick,t,c).enqueue(new Callback<MessageService.MsgResponse>() {
                    @Override
                    public void onResponse(Call<MessageService.MsgResponse> call, Response<MessageService.MsgResponse> response) {
                        progressD.dismiss();
                        new MaterialDialog.Builder(Message2Activity.this)

                                .title("전송 완료")
                                .content(mb_nick+"님께 메시지를 전송하였습니다!")
                                .positiveText("확인")
                                .iconRes(R.drawable.success)
                                .limitIconToDefaultSize()
                                .onAny(new MaterialDialog.SingleButtonCallback() {
                                           @Override
                                           public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                               Message2Activity.this.finish();
                                           }
                                       }
                                )
                                .show();
                    }

                    @Override
                    public void onFailure(Call<MessageService.MsgResponse> call, Throwable t) {
                        System.out.println("fail");
                    }
                });
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}