package com.lpdw.urbanproject.Api;

import android.util.Log;

import com.lpdw.urbanproject.Me;
import com.lpdw.urbanproject.Token;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
        @POST("forget-password")
        Call<Void> forgetPassword(@Field("username") String username);

        @FormUrlEncoded
        @POST("token")
        Call<Token> signIn(@Field("username") String username, @Field("password") String password);

        @FormUrlEncoded
        @POST("token/refresh")
        Call<Token> refreshToken(@Field("refresh_token") String refresh_token);

        @FormUrlEncoded
        @POST("me")
        Call<Response> me(@Header("Authorization") String contentRange);

        @GET("me/gardens")
        Call<Response> myGardens();

        @GET("alerts")
        Call<DataResponse> getAllAlerts();

        @GET("types")
        Call<DataResponse> getAllTypes();

        @GET("gardens/{slugGarden}/measures/{slugType}")
        Call<DataResponse> getMeasure(@Path("slugGarden") String slugGarden, @Path("slugType") String slugType);

        @GET("/gardens/{slugGarden}/configurations")
        Call<DataResponse> getGardenConfig(@Path("slugGarden") String slugGarden);


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

    public void forgetPassword(final String username, final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        API api = retro.create(API.class);

        Call<Void> call = api.forgetPassword(username);

        call.enqueue(new retrofit2.Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.code() == 204) {
                    callbackWrapper.onResponse("ok");
                } else if(response.code() == 404) {
                    callbackWrapper.onResponse("unknown user");
                } else if(response.code() == 409) {
                    callbackWrapper.onResponse("conflict");
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", response.code()));
                    callbackWrapper.onFailure(call, t);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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

    public void refreshToken(final Me me, final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        API api = retro.create(API.class);

        Call<Token> call = api.refreshToken(me.refreshToken);

        call.enqueue(new retrofit2.Callback<Token>() {

            @Override
            public void onResponse(Call<Token> call, retrofit2.Response<Token> token) {
                if (token.code() == 200 && token.body() != null) {
                    //callbackWrapper.onResponse(token.body());
                    Log.d("REFRESHING TOKEN", token.body().token);
                    me.token = token.body().token;
                    me.refreshed = true;
                    me.save();
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", token.code()));
                    onFailure(call, t);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                //callbackWrapper.onFailure(call, t);
                t.printStackTrace();
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

    public void getMeasure(final String gardenSlug, final String typeSlug, final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(interceptorAuthorizationHeader())
                .build();
        API api = retro.create(API.class);

        Call<DataResponse> call = api.getMeasure(gardenSlug, typeSlug);

        call.enqueue(new retrofit2.Callback<DataResponse>() {

            @Override
            public void onResponse(Call<DataResponse> call, retrofit2.Response<DataResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    Me.get().unsetRefreshed();
                    callbackWrapper.onResponse(response.body());
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", response.code()));
                    if(response.code() == 401 && !Me.get().refreshed){
                        refreshToken(Me.get(), new UrbanPotagerApi.CallbackWrapper() {
                            @Override
                            public void onResponse(Object object) {
                                getMeasure(gardenSlug, typeSlug, callbackWrapper);
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                callbackWrapper.onFailure(call, t);
                            }
                        });
                    } else {
                        callbackWrapper.onFailure(call, t);
                    }
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                callbackWrapper.onFailure(call, t);
            }
        });
    }

    public void getGardenConfig(final String gardenSlug, final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(interceptorAuthorizationHeader())
                .build();
        API api = retro.create(API.class);

        Call<DataResponse> call = api.getGardenConfig(gardenSlug);

        call.enqueue(new retrofit2.Callback<DataResponse>() {

            @Override
            public void onResponse(Call<DataResponse> call, retrofit2.Response<DataResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    Me.get().unsetRefreshed();
                    callbackWrapper.onResponse(response.body());
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", response.code()));
                    if(response.code() == 401 && !Me.get().refreshed){
                        refreshToken(Me.get(), new UrbanPotagerApi.CallbackWrapper() {
                            @Override
                            public void onResponse(Object object) {
                                getGardenConfig(gardenSlug, callbackWrapper);
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                callbackWrapper.onFailure(call, t);
                            }
                        });
                    } else {
                        callbackWrapper.onFailure(call, t);
                    }
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                callbackWrapper.onFailure(call, t);
            }
        });
    }



    public void myGardens(final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(interceptorAuthorizationHeader())
                .build();
        API api = retro.create(API.class);

        Call<Response> call = api.myGardens();

        call.enqueue(new retrofit2.Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200 && response.body() != null) {
                    callbackWrapper.onResponse(response.body().gardens);
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

    public void getAllAlerts(final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(interceptorAuthorizationHeader())
                .build();
        API api = retro.create(API.class);

        Call<DataResponse> call = api.getAllAlerts();

        call.enqueue(new retrofit2.Callback<DataResponse>() {

            @Override
            public void onResponse(Call<DataResponse> call, retrofit2.Response<DataResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    callbackWrapper.onResponse(response.body());
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", response.code()));
                    callbackWrapper.onFailure(call, t);
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                callbackWrapper.onFailure(call, t);
            }
        });
    }

    public void getAllTypes(final CallbackWrapper callbackWrapper){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(interceptorAuthorizationHeader())
                .build();
        API api = retro.create(API.class);

        Call<DataResponse> call = api.getAllTypes();

        call.enqueue(new retrofit2.Callback<DataResponse>() {

            @Override
            public void onResponse(Call<DataResponse> call, retrofit2.Response<DataResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    callbackWrapper.onResponse(response.body());
                } else {
                    Throwable t = new Throwable(String.format("HTTP CODE: %d", response.code()));
                    callbackWrapper.onFailure(call, t);
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                callbackWrapper.onFailure(call, t);
            }
        });
    }
}
