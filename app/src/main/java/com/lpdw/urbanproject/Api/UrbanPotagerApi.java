package com.lpdw.urbanproject.Api;

import android.content.SharedPreferences;
import android.util.Log;

import com.lpdw.urbanproject.DataContainer;
import com.lpdw.urbanproject.Token;
import com.lpdw.urbanproject.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yassin on 03/06/16.
 */
public class UrbanPotagerApi {
    final String baseUrl = "https://urbanpotager.labesse.me/";
    public interface API {
        @Headers("Content-DataContainer: application/json")
        @FormUrlEncoded
        @POST("users")
        Call<Response> createUser(@Field("username") String username, @Field("email") String email, @Field("plainPassword") String plainPassword);
    }
    public abstract static class CallbackCreateUser {
        public abstract void onResponse(User user);
        public abstract void onFailure(Throwable t);
    }

    public void createUser(final User user, final CallbackCreateUser callbackCreateUser){
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        API api = retro.create(API.class);

        Call<Response> call = api.createUser(user.username, user.email, user.plainPassword);

        call.enqueue(new retrofit2.Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                callbackCreateUser.onResponse(user);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callbackCreateUser.onFailure(t);
            }
        });
    }
}
