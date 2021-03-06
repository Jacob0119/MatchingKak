package project.capstone.com.matchingkak;

/**
 * Created by Lee on 2017-10-27.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;

/**
 * CookieSharedPreferences 클래스
 *
 * @author devetude
 */
public class SharedPreferencesManager {
    /**
     * CookieSharedPreferences를 참조하기 위한 key
     *
     * 권고) 겹치지 않는 고유한 형태의 string으로 구성할 것
     */
    public static final String COOKIE_SHARED_PREFERENCES_KEY = "www.matchingkak.cookie";
    public static final String LOGIN_STATE_SHARED_PREFERENCE_KEY="www.matchingkak.isLogin";

    // 싱글톤 모델로 객체 초기화
    private static SharedPreferencesManager SharedPreferences = null;

    public static SharedPreferencesManager getInstanceOf(Context c){
        if(SharedPreferences == null){
            SharedPreferences = new SharedPreferencesManager(c);
        }

        return SharedPreferences;
    }

    private SharedPreferences sharedPreferences;

    /**
     * 생성자
     *
     * @param context
     */
    public SharedPreferencesManager(Context context) {
        final String COOKIE_SHARED_PREFERENCE_NAME = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(COOKIE_SHARED_PREFERENCE_NAME, Activity.MODE_PRIVATE);
    }

    /**
     * SharedPreferences에 값을 추가하는 메소드
     *
     * @param key
     * @param hashSet
     */
    public void putHashSet(String key, HashSet<String> hashSet){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, hashSet);
        editor.commit();
    }

    /**
     * SharedPreferences에서 값을 가져오는 메소드
     *
     * @param key
     * @param cookie
     * @return
     */
    public HashSet<String> getHashSet(String key, HashSet<String> cookie){
        try {
            return (HashSet<String>) sharedPreferences.getStringSet(key, cookie);
        } catch (Exception e) {
            return cookie;
        }
    }

    public boolean getLoginState(){

        return sharedPreferences.getBoolean(SharedPreferencesManager.LOGIN_STATE_SHARED_PREFERENCE_KEY,false);

    }
    public void setLoginState(boolean state){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SharedPreferences.LOGIN_STATE_SHARED_PREFERENCE_KEY,state);
        editor.commit();
    }
}
