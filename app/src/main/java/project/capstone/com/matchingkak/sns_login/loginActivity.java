package project.capstone.com.matchingkak.sns_login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.nhn.android.naverlogin.OAuthLogin;

import java.security.MessageDigest;

import de.hdodenhof.circleimageview.CircleImageView;
import project.capstone.com.matchingkak.R;

public class loginActivity extends AppCompatActivity {
    Context mContext=loginActivity.this;
    
    private SessionCallback callback;
    TextView user_nickname,user_email;
    CircleImageView user_img;
    LinearLayout success_layout;
    Button logout_btn;
    Button loginButton;

    AQuery aQuery;

    OAuthLogin mOAuthLoginModule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                loginActivity.this
                ,"reXClXhDw5yssWc69qro"
                ,"woYKHdguGr"
                ,"MatchingKak"
                //,OAUTH_CALLBACK_INTENT
                // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
        );
        //OAuthLoginButton naverLoginButton = ( OAuthLoginButton ) findViewById( R.id.naver );
       /* naverLoginButton.setOAuthLoginHandler( new OAuthLoginHandler( )
        {
            @Override
            public void run( boolean b )
            {
                if ( b )
                {
                    final String token = mOAuthLoginModule.getAccessToken( loginActivity.this );
                    new Thread( new Runnable( )
                    {
                        @Override
                        public void run( )
                        {
                            String response = mOAuthLoginModule.requestApi( loginActivity.this, token, "https://openapi.naver.com/v1/nid/me" );
                            try
                            {
                                JSONObject json = new JSONObject( response );
                                // response 객체에서 원하는 값 얻어오기
                                String email = json.getJSONObject( "response" ).getString( "email" );
                                // 액티비티 이동 등 원하는 함수 호출
                                Toast.makeText(loginActivity.this,email,Toast.LENGTH_LONG );
                            } catch ( JSONException e )
                            {
                                e.printStackTrace( );
                            }
                        }
                    } ).start( );
                }
                else {
                    String errorCode = mOAuthLoginModule.getLastErrorCode(mContext).getCode();
                    String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                    Toast.makeText(mContext, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                }
            }
        } );




        ///////////////////////////////////////////////////////////////////
        aQuery = new AQuery(this);
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        // 카카오톡 로그인 버튼
        loginButton = (Button)findViewById(R.id.kakao);
        loginButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(!isConnected()){
                        Toast.makeText(loginActivity.this,"인터넷 연결을 확인해주세요",Toast.LENGTH_SHORT).show();
                    }
                }

                if(isConnected()){
                    return false;
                }else{
                    return true;
                }
            }
        });

        // 로그인 성공 시 사용할 뷰
        //
        /*
        success_layout = (LinearLayout)findViewById(R.id.success_layout);
        user_nickname =(TextView)findViewById(R.id.user_nickname);
        user_img =(CircleImageView) findViewById(R.id.user_img);
        user_email =(TextView)findViewById(R.id.user_email);
        logout_btn = (Button)findViewById(R.id.logout);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Session.getCurrentSession().isOpened()) {
                    requestLogout();
                }
            }
        });


        if(Session.getCurrentSession().isOpened()){
            requestMe();
        }else{
            success_layout.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
        }*/

    }


    //인터넷 연결상태 확인
    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            //access token을 성공적으로 발급 받아 valid access token을 가지고 있는 상태. 일반적으로 로그인 후의 다음 activity로 이동한다.
            if(Session.getCurrentSession().isOpened()){ // 한 번더 세션을 체크해주었습니다.
                requestMe();
            }
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    private void requestLogout() {
        success_layout.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(loginActivity.this, "로그아웃 성공", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void requestMe() {
        success_layout.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e("onFailure", errorResult + "");
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("onSessionClosed",errorResult + "");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.e("onSuccess",userProfile.toString());
                user_nickname.setText(userProfile.getNickname());
                user_email.setText(userProfile.getEmail());
                aQuery.id(user_img).image(userProfile.getThumbnailImagePath()); // <- 프로필 작은 이미지 , userProfile.getProfileImagePath() <- 큰 이미지
            }

            @Override
            public void onNotSignedUp() {
                Log.e("onNotSignedUp","onNotSignedUp");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }







    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
}
