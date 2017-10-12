package project.capstone.com.matchingkak;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Lee on 2017-05-30.
 */

public class myWebView extends WebViewClient {
    private String MainUrl;


    public myWebView(String url){

            MainUrl=url;
    }
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.contains(MainUrl)){
            Log.d("URL:",url);
            return false;
        }else {

           Log.d("URL:",url);

        }

        Log.d("shold url",url);

        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.d("should",request.getUrl().getPath());
        return super.shouldOverrideUrlLoading(view, request);
    }
}
