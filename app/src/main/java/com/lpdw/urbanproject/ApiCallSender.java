package com.lpdw.urbanproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by OBYON on 02/06/16.
 */
public class ApiCallSender {
    public Context context;
    public Token token = new Token();
    public Interceptor interceptor;
    public OkHttpClient.Builder builder;
    public Retrofit retrofit;

    public ApiCallSender(Context context){
        this.context = context;

        token.token = context.getSharedPreferences("token_file", context.MODE_PRIVATE).getString("token", "no token");
        token.refresh_token = context.getSharedPreferences("token_file", context.MODE_PRIVATE).getString("refresh_token", "no refresh_token");

        interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token.token).build();
                return chain.proceed(newRequest);
            }
        };

        builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://urbanpotager.labesse.me/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();
    }

    private void writeInSharedPref(String file_title, String content_title, String content){
        SharedPreferences prefs = context.getSharedPreferences(file_title, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(content_title, content);
        editor.commit();
    }

    public void getData(String slug){
        API api = retrofit.create(API.class);

        Call<DataContainer> call = api.getData("test");
        call.enqueue(new Callback<DataContainer>() {
            public void onResponse(Call<DataContainer> call, Response<DataContainer> response) {
                writeInSharedPref("data_file", response.body().type.name, response.body().type.description);
            }
            @Override
            public void onFailure(Call<DataContainer> call, Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });
    }
}
