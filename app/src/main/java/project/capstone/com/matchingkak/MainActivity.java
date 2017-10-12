package project.capstone.com.matchingkak;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.helper.Utility.getPackageInfo;
import static project.capstone.com.matchingkak.R.id.webview;

public class MainActivity extends AppCompatActivity {
    WebView myWebView;
    private BackPressCloseSystem backPressCloseSystem;

    @Override
    protected void onRestart() {
        myWebView.loadUrl("http://matchingkak.com");
       //System.out.println("http://matchingkak.cafe24.com");
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // System.out.println(FirebaseInstanceId.getInstance().getToken());
        setContentView(R.layout.activity_main);

        System.out.println("http://matchingkak.com");
        backPressCloseSystem = new BackPressCloseSystem(this);
        myWebView=(WebView) findViewById(webview);
        WebSettings webSettings=myWebView.getSettings();
        webSettings.setUserAgentString("MatchingkakApp");
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://matchingkak.com");
        myWebView.setWebViewClient(new myWebViewClient());






        myWebView.addJavascriptInterface(new Object(){




            @JavascriptInterface
            public void onclickA(String mb_no){

                Log.d("onclickA","start");
                //Toast.makeText(MainActivity.this,mb_no,Toast.LENGTH_LONG).show();
                //FirebaseMessaging.getInstance().subscribeToTopic("news");

                String token=FirebaseInstanceId.getInstance().getToken();
                sendRegistrationToServer(mb_no,token);
            }
            @JavascriptInterface
            public void onclick_logout(String mb_no){

               Log.d("Logout","start");
                //Toast.makeText(MainActivity.this,mb_no,Toast.LENGTH_LONG).show();
                //FirebaseMessaging.getInstance().subscribeToTopic("news");

                //String token=FirebaseInstanceId.getInstance().getToken();
               sendLogoutToServer(mb_no);
            }
            @JavascriptInterface
            public void toastShort(){
                Toast.makeText(MainActivity.this,"onclick(value)",Toast.LENGTH_SHORT).show();
                //FirebaseMessaging.getInstance().subscribeToTopic("news");
                // FirebaseInstanceId.getInstance().getToken();
                // String token=FirebaseInstanceId.getInstance().getToken();
                //sendRegistrationToServer(token);

            }
            @JavascriptInterface
            public void startMessage(){


                Intent i=new Intent(MainActivity.this,MessageActivity.class);
                startActivity(i);
            }

        },"app");

        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
                new AlertDialog.Builder(MainActivity.this)

                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            }
        });

    }
    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("ERROR", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }
    private class myWebViewClient extends WebViewClient{


        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains("login.php")||url.contains("join.php")||url.contains("w_message")||url.contains("editor")||url.contains("profile.php")){
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                return true;
            }else if(url.contains("detail.php"))
            {   Log.d("Main",url);
                String[] params=url.split("gm_no=");
                Log.d("MainActivity","?gm_no="+params[1]);
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);

                intent.putExtra("gm_no",params[1]);
                System.out.println("detail.php"+url+"????"+params[1]);
                intent.putExtra("url",url);
                startActivity(intent);
                return true;
            }

            else if(url.contains("logout.php")){

                return false;

            } else{
                Intent intent=new Intent(MainActivity.this,MessageActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                return true;
            }


        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.d("should",request.getUrl().getPath());
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    private void sendRegistrationToServer(final String mb_no,final String token){

        new AsyncTask<Void,Void,Void>(){
            private URL url;
            private String data, strUrl, strCookie,result;

            @Override
            protected  void onPreExecute(){

                super.onPreExecute();
                strUrl="http://matchingkak.com/fcm/register.php";
                data="mb_no="+mb_no+"&mb_token="+token;
                System.out.println(data);

            }


            @Override
            protected Void doInBackground(Void... voids) {
                try{

                    url=new URL(strUrl);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setDefaultUseCaches(false);




                    OutputStream os = conn.getOutputStream(); // 서버로 보내기 위한 출력 스트림
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // UTF-8로 전송
                    bw.write(data); // 매개변수 전송
                    bw.flush();
                    bw.close();
                    os.close();

                    strCookie=conn.getHeaderField("Set-Cookie");
                    InputStream is=conn.getInputStream();



                        // 웹 서버로 보낼 매개변수가 있는 경우우





                    StringBuilder builder=new StringBuilder();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
                    String line;
                    while((line=reader.readLine())!=null){
                        builder.append(line+"\n");


                    }
                    result=builder.toString();

                }catch (MalformedURLException |ProtocolException e){
                    e.printStackTrace();
                }catch(IOException io){
                    io.printStackTrace();

                }return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);
                System.out.println(result);
            }
        }.execute();







        /*
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
        }*/
    }

    private void sendLogoutToServer(final String mb_no){


        new AsyncTask<Void,Void,Void>(){
            private URL url;
            private String data, strUrl, strCookie,result;

            @Override
            protected  void onPreExecute(){

                super.onPreExecute();
                strUrl="http://matchingkak.com/fcm/logout.php";
                Log.d("Logout","start logout");
                data="mb_no="+mb_no;

                System.out.println(data);

            }


            @Override
            protected Void doInBackground(Void... voids) {
                try{

                    url=new URL(strUrl);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setDefaultUseCaches(false);



                    OutputStream os = conn.getOutputStream(); // 서버로 보내기 위한 출력 스트림
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // UTF-8로 전송
                    bw.write(data); // 매개변수 전송
                    bw.flush();
                    bw.close();
                    os.close();



                    //php 연산 결과값 가져오기
                    strCookie=conn.getHeaderField("Set-Cookie");
                    InputStream is=conn.getInputStream();
                    StringBuilder builder=new StringBuilder();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
                    String line;
                    while((line=reader.readLine())!=null){
                        builder.append(line+"\n");


                    }
                    result=builder.toString();

                }catch (MalformedURLException |ProtocolException e){
                    e.printStackTrace();
                }catch(IOException io){
                    io.printStackTrace();

                }return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("Logout","end of logout");
                System.out.println(result);
            }
        }.execute();




    }
    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
    public class BackPressCloseSystem {

        private long backKeyPressedTime = 0;
        private Toast toast;

        private Activity activity;

        public BackPressCloseSystem(Activity activity) {
            this.activity = activity;
        }

        public void onBackPressed() {

            if (isAfter2Seconds()) {
                backKeyPressedTime = System.currentTimeMillis();
                // 현재시간을 다시 초기화

                toast = Toast.makeText(activity,
                        "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.",
                        Toast.LENGTH_SHORT);
                toast.show();

                return;
            }

            if (isBefore2Seconds()) {
                programShutdown();
                toast.cancel();
            }
        }

        private Boolean isAfter2Seconds() {
            return System.currentTimeMillis() > backKeyPressedTime + 2000;
            // 2초 지났을 경우
        }

        private Boolean isBefore2Seconds() {
            return System.currentTimeMillis() <= backKeyPressedTime + 2000;
            // 2초가 지나지 않았을 경우
        }

        private void programShutdown() {
            activity .moveTaskToBack(true);
            activity .finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}
