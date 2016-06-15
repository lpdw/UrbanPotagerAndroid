package com.lpdw.urbanproject.Api;

import com.lpdw.urbanproject.Me;
import com.lpdw.urbanproject.Token;
import com.lpdw.urbanproject.User;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by yassin on 03/06/16.
 */
public class UrbanPotagerApi {
    final String baseUrl = "https://urbanpotager.labesse.me/";
    public interface API {
        @FormUrlEncoded
        @POST("users")
        Call<Response> createUser(@Field("username") String username, @Field("email") String email, @Field("plainPassword") String plainPassword);

        @FormUrlEncoded
        @POST("token")
        Call<Token> signIn(@Field("username") String username, @Field("password") String password);

        @FormUrlEncoded
        @POST("me")
        Call<Response> me(@Header("Authorization") String contentRange);
    }

    public abstract static class CallbackWrapper {
        public abstract void onResponse(Object object);
        public abstract void onFailure(Call call, Throwable t);
    }

    public OkHttpClient interceptorAuthorizationHeader(){
        final Me me = Me.get();

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer " + me.token).build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        return client;
    }

    public void createUser(final Me me, final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        API api = retro.create(API.class);

        Call<Response> call = api.createUser(me.username, me.email, me.plainPassword);

        call.enqueue(new retrofit2.Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 201 && response.body() != null) {
                    callbackWrapper.onResponse(response.body().me);
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", response.code()));
                    callbackWrapper.onFailure(call, t);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callbackWrapper.onFailure(call, t);
            }

        });
    }

    public void signIn(final Me me, final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        API api = retro.create(API.class);

        Call<Token> call = api.signIn(me.username, me.plainPassword);

        call.enqueue(new retrofit2.Callback<Token>() {

            @Override
            public void onResponse(Call<Token> call, retrofit2.Response<Token> token) {
                if (token.code() == 200 && token.body() != null) {
                    callbackWrapper.onResponse(token.body());
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", token.code()));
                    callbackWrapper.onFailure(call, t);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                callbackWrapper.onFailure(call, t);
            }
        });
    }

    public void me(final CallbackWrapper callbackWrapper){
        Me me = Me.get();

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(interceptorAuthorizationHeader())
                .build();
        API api = retro.create(API.class);

        Call<Response> call = api.me(me.token);

        call.enqueue(new retrofit2.Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200 && response.body() != null) {
                    callbackWrapper.onResponse(response.body());
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", response.code()));
                    callbackWrapper.onFailure(call, t);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callbackWrapper.onFailure(call, t);
            }
        });
    }
}
