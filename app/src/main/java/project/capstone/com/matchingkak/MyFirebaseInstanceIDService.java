package project.capstone.com.matchingkak;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Lee on 2017-05-28.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{


    private static final String TAG="MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed token: "+token);

       // sendRegistrationToServer(token);

    }
    private void sendRegistrationToServer(String token){
        String mb_no="105";
        OkHttpClient client =new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                            .add("mb_no",mb_no)
                            .add("mb_token",token)
                            .build();

        Request request=new Request.Builder()
                            .url("http://matchingkak.com/fcm/register.php")
                            .post(body)
                            .build();

        try{
            client.newCall(request).execute();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
