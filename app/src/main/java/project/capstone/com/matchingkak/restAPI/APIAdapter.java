package project.capstone.com.matchingkak.restAPI;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lee on 2017-10-27.
 */

public class APIAdapter {

    protected static Object retrofit(Context context, Class<?> serviceName){


        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                                    .addInterceptor(new ReceivedCookiesInterceptor(context))
                                    .addNetworkInterceptor(new AddCookiesInterceptor(context))
                                    .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

            return retrofit.create(serviceName);

    }
}
