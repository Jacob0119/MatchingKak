package project.capstone.com.matchingkak.restAPI;

/**
 * Created by Lee on 2017-10-27.
 */

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;
import project.capstone.com.matchingkak.SharedPreferencesManager;

/**
 * ReceivedCookiesInterceptor 클래스
 *
 * @author devetude
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    // CookieSharedReferences 객체
    private SharedPreferencesManager cookieSharedPreferences;

    /**
     * 생성자
     *
     * @param context
     */
    public ReceivedCookiesInterceptor(Context context){
        // CookieSharedReferences 객체 초기화

        cookieSharedPreferences = SharedPreferencesManager.getInstanceOf(context);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        // 가져온 chain으로 부터 리스폰스 객체를 생성
        Response response = chain.proceed(chain.request());

        // 리스폰스의 헤더 영역에 Set-Cookie가 설정되어있는 경우
        if (!response.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            Log.d("Intercept",response.headers().toString());
            // 쿠키 값을 읽어옴
            for (String header : response.headers("Set-Cookie")) {
                cookies.add(header);
                Log.d("Intercept",header);
            }

            // 쿠키 값을 CookieSharedPreferences에 저장
            cookieSharedPreferences.putHashSet(SharedPreferencesManager.COOKIE_SHARED_PREFERENCES_KEY, cookies);
        }

        // 리스폰스 객체 반환
        return response;
    }
}
