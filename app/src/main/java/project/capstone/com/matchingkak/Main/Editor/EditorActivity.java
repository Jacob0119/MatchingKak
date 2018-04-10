package project.capstone.com.matchingkak.Main.Editor;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import project.capstone.com.matchingkak.LoginActivity;
import project.capstone.com.matchingkak.MainActivity;
import project.capstone.com.matchingkak.R;

import static project.capstone.com.matchingkak.R.id.webview_msg;
import static project.capstone.com.matchingkak.R.menu.editor_menu;

public class EditorActivity extends AppCompatActivity {
    WebView myWebView;
    String url;

    private ValueCallback<Uri> filePathCallbackNormal;
    private ValueCallback<Uri[]> filePathCallbackLollipop;
    private Uri mCapturedImageURI;
    final int FILECHOOSER_NORMAL_REQ_CODE=1;
    final int FILECHOOSER_LOLLIPOP_REQ_CODE=2;
    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //noinspection deprecation
            CookieSyncManager.createInstance(this);
        }
        Toolbar toolbar=findViewById(R.id.editor_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("글쓰기");


        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        myWebView = findViewById(webview_msg);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setUserAgentString("MatchingkakApp");
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new myWebViewClient());
        myWebView.addJavascriptInterface(new Object(){


            @JavascriptInterface
            public void onclickA(String mb_no){

                Log.d("onclickA","start");
                //Toast.makeText(MainActivity.this,mb_no,Toast.LENGTH_LONG).show();
                //FirebaseMessaging.getInstance().subscribeToTopic("news");

                String token= FirebaseInstanceId.getInstance().getToken();
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
                Toast.makeText(EditorActivity.this,"onclick(value)",Toast.LENGTH_SHORT).show();
                //FirebaseMessaging.getInstance().subscribeToTopic("news");
                // FirebaseInstanceId.getInstance().getToken();
                // String token=FirebaseInstanceId.getInstance().getToken();
                //sendRegistrationToServer(token);

            }


        },"app");
        myWebView.setWebChromeClient(  new WebChromeClient() {
            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooser(uploadMsg, "");
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                filePathCallbackNormal = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_NORMAL_REQ_CODE);
            }

            // For Android 4.1+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooser(uploadMsg, acceptType);
            }


            // For Android 5.0+
            public boolean onShowFileChooser(
                    WebView webView, ValueCallback<Uri[]> filePathCallback,
                    WebChromeClient.FileChooserParams fileChooserParams) {
                if (filePathCallbackLollipop != null) {
//                    filePathCallbackLollipop.onReceiveValue(null);
                    filePathCallbackLollipop = null;
                }
                filePathCallbackLollipop = filePathCallback;


                // Create AndroidExampleFolder at sdcard
                File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AndroidExampleFolder");
                if (!imageStorageDir.exists()) {
                    // Create AndroidExampleFolder at sdcard
                    imageStorageDir.mkdirs();
                }

                // Create camera captured image file path and name
                File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                mCapturedImageURI = Uri.fromFile(file);

                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");

                // Create file chooser intent
                Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
                // Set camera intent to file chooser
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});

                // On select image call onActivityResult method of activity
                startActivityForResult(chooserIntent, FILECHOOSER_LOLLIPOP_REQ_CODE);
                return true;

            }




            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
                new AlertDialog.Builder(EditorActivity.this)

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(editor_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.editor_cancle:
                onBackPressed();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //noinspection deprecation
            CookieSyncManager.getInstance().startSync();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //noinspection deprecation
            CookieSyncManager.getInstance().stopSync();
        }
    }

    private class myWebViewClient extends WebViewClient {


        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("url log",url);
           if(url.contains("index.php")){

               Intent intent=new Intent(EditorActivity.this,MainActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
               return true;
           }
          else if(url.contains("login.php")||url.contains("join.php")||url.contains("w_message")||url.contains("r_")||url.contains("editor")||url.contains("profile.php")){
               Intent intent = new Intent(EditorActivity.this, LoginActivity.class);
               intent.putExtra("url",url);
               startActivity(intent);
                return true;
           }else if(url.contains("logout.php")){

               return false;

           }

            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //noinspection deprecation
                CookieSyncManager.getInstance().sync();
            } else {
                // 롤리팝 이상에서는 CookieManager의 flush를 하도록 변경됨.
                CookieManager.getInstance().flush();
            }
        }
    }

    private void sendRegistrationToServer(final String mb_no,final String token){

        new AsyncTask<Void,Void,Void>(){
            private URL url;
            private String data, strUrl, strCookie,result;

            @Override
            protected  void onPreExecute(){

                super.onPreExecute();
                strUrl="http://matchingkak.cafe24.com/fcm/register.php";
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
}
