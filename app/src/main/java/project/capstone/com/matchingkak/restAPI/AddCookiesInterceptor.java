package project.capstone.com.matchingkak.restAPI;

/**
 * Created by Lee on 2017-10-27.
 */

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import project.capstone.com.matchingkak.SharedPreferencesManager;

/**
 * AddCookiesInterceptor 클래스
 *
 * @author devetude
 */
public class AddCookiesInterceptor implements Interceptor {
    // CookieSharedReferences 객체
    private SharedPreferencesManager cookieSharedPreferences;

    /**
     * 생성자
     *
     * @param context
     */
    public AddCookiesInterceptor(Context context){
        // CookieSharedReferences 객체 초기화
        cookieSharedPreferences = SharedPreferencesManager.getInstanceOf(context);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        // 가져온 chain으로 부터 빌더 객체를 생성
        Request.Builder builder = chain.request().newBuilder();

        // CookieSharedPreferences에 저장되어있는 쿠키 값을 가져옴
        HashSet<String> cookies = (HashSet) cookieSharedPreferences.getHashSet(
                SharedPreferencesManager.COOKIE_SHARED_PREFERENCES_KEY,
                new HashSet<String>()
        );

        // 빌더 헤더 영역에 쿠키 값 추가
        for (String cookie : cookies) {
            builder.addHeader("Cookie", cookie);
            String cookieName = HttpCookie.parse(cookie).get(0).getName();
            String cookieValue = HttpCookie.parse(cookie).get(0).getValue();

            String cookieString = cookieName + "=" + cookieValue;



            Log.d("add Intercept",cookie);
        }


        // 체인에 빌더를 적용 및 반환
        return chain.proceed(builder.build());
    }
}
