package project.capstone.com.matchingkak.Main.me;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import project.capstone.com.matchingkak.MainActivity;
import project.capstone.com.matchingkak.R;


public class meFragment extends Fragment {

    private MainActivity.BackPressCloseSystem backPressCloseSystem;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView myWebView;


    public meFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static meFragment newInstance() {
        meFragment fragment = new meFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.me_content_temp, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //init();


    }


    private class myWebViewClient extends WebViewClient {


        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            CookieManager manager=CookieManager.getInstance();
            manager.setAcceptCookie(true);

            String cookiesString = manager.getCookie(url);

            if (cookiesString != null && !cookiesString.isEmpty()) {

                Log.d("meFragment2","load:"+cookiesString);



            }
            /*
            if(url.contains("login.php")){

                Intent intent=new Intent(MainActivity.this,loginActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                return true;
            }
            if(url.contains("join.php")||url.contains("w_message")||url.contains("editor")||url.contains("profile.php")){
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
*/
return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            myWebView.clearHistory();
            myWebView.clearCache(true);

            super.onPageFinished(view, url);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.d("should",request.getUrl().getPath());
            return super.shouldOverrideUrlLoading(view, request);
        }
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
