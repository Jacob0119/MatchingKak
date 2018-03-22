package project.capstone.com.matchingkak.restAPI;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lee on 2017-10-27.
 */

public class APIAdapter {

    protected static Object retrofit(Class<?> serviceName){


        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new WebviewCookieHandler())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                            Request request=chain.request();
                            Request newRequest;
                            newRequest=request.newBuilder()
                                    .header("User-Agent",APIUrl.USER_AGENT)
                                    .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Log.d("property",System.getProperty("http.agent"));

        /*
        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                                    .addInterceptor(new ReceivedCookiesInterceptor(context))
                                    .addNetworkInterceptor(new AddCookiesInterceptor(context))
                                    .cookieJar(new JavaNetCookieJar())
                                    .build();
*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

            return retrofit.create(serviceName);

    }
}
