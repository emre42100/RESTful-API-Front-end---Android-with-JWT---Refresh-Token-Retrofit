package com.inalsozluk.app.network;

import com.inalsozluk.app.TokenManager;
import com.inalsozluk.app.getMyCustomerActivity;
//import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitBuilder {

    public static final String BASE_URL = "https://inalsozluk.com/tutorial/";

    private final static OkHttpClient client = buildClient();
    private final static Retrofit retrofit = buildRetrofit(client);

    private static OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder()
                                .addHeader("Accept", "application/json");
                        request = builder.build();
                        return chain.proceed(request);

                    }
                });

//        if (BuildConfig.DEBUG) {
//            builder.addNetworkInterceptor(new StethoInterceptor());
//        }

        return builder.build();

    }

    private static Retrofit buildRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    public static <T> T createService(Class<T> service) {

        return retrofit.create(service);
    }


    public static <T> T createServiceWithAuth(Class<T> service, final TokenManager tokenManager) {
        OkHttpClient newClient = client.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                Request.Builder builder = request.newBuilder();

                if (tokenManager.getToken().getToken() != null) {
                    builder.addHeader("Authorization", "Bearer " + tokenManager.getToken().getToken());
                }
                request = builder.build();
                return chain.proceed(request);
            }
        }).authenticator(getMyCustomerActivity.getInstance(tokenManager)).build();



        Retrofit newRetrofit = retrofit.newBuilder().client(newClient).build();
        return newRetrofit.create(service);
    }



    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitInterface getApiService() {
        return RetrofitBuilder.createService(RetrofitInterface.class);
    }


}

