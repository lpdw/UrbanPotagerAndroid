package com.lpdw.urbanproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by OBYON on 30/05/16.
 */
public interface API {
    @Headers("Content-DataContainer: application/json, Accept: application/json")
    @POST("token")
    Call<Token> createUser(@Body User user);

    @Headers("Accept: application/json")
    @GET("types/{slug}")
    Call<DataContainer> getData(@Path("slug") String slug);

    @Headers("Accept: application/json")
    @GET("gardens/{slug}")
    Call<GardenContainer> getGarden(@Path("slug") String slug);
}


/*        @Headers("Accept: application/json")
        @GET("weather?&appid=e56a3908ef9901a8a73e62cad60e27cb")
        Call<CurrentWeather> getTodayByCity(@Query("q") String cityName,
                                            @Query("units") String unitsFormat);

        @Headers("Accept: application/json")
        @GET("forecast?&appid=e56a3908ef9901a8a73e62cad60e27cb")
        Call<ForecastWeather> getForecastByCity(@Query("q") String cityName,
                                                @Query("units") String unitsFormat);

        @Headers("Accept: application/json")
        @GET("weather?&appid=e56a3908ef9901a8a73e62cad60e27cb")
        Call<CurrentWeather> getTodayByCoo(@Query("lat") double lat,
                                           @Query("lon") double lon,
                                           @Query("units") String unitsFormat);

        @Headers("Accept: application/json")
        @GET("forecast?&appid=e56a3908ef9901a8a73e62cad60e27cb")
        Call<ForecastWeather> getForecastByCoo(@Query("lat") double lat,
                                               @Query("lon") double lon,
                                               @Query("units") String unitsFormat);*/
