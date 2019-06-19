package sk.fri.uniza.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import sk.fri.uniza.api.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public interface WindFarmRequest {

    @GET("/backend/login/public-key")
    Call<PublicKey> getPublicKey();

    @GET("/backend/login/")
    Call<ResponseBody> getLoginHtml(@QueryMap Map<String, String> oauthRequest);

    @FormUrlEncoded
    @POST("/backend/login/token")
    Call<AccessToken> getAccessToken(@FieldMap Map<String, String> tokenRequest);
/*
    @GET("/backend/persons")
    Call<List<Person>> getAllPersons(@Header("Authorization") String authorization);
*/
    @GET("/backend/persons/{id}")
    Call<Person> getPerson(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("/backend/persons")
    Call<Paged<List<Person>>> getPagedPersons(@Header("Authorization") String authorization, @Query("limit") Integer limit, @Query("page") Integer page);

    @POST("/backend/persons")
    Call<Person> savePersons(@Header("Authorization") String authorization, @Body Person person);

    /********* Moje vytvorene volania na back-end *********/
    @GET("/backend/persons/{id}/cities")
    Call<Paged<List<City>>> getPagedCities(@Header("Authorization") String authorization, @Path("id") Long id, @Query("limit") Integer limit,  @Query("page") Integer page);

    @GET("/backend/cities/countries")
    Call<List<String>> getCountries(@Header("Authorization") String authorization);

    @GET("/backend/cities")
    Call<List<City>> getCities(@Header("Authorization") String authorization,@Query("country")  String country);

    @POST("/backend/persons/{id}/cities")
    Call<Void> saveCity(@Header("Authorization") String authorization,@Path("id") Long userId, @Query("cityID")  Long cityID);

    @DELETE("/backend/persons/{id}/cities")
    Call<Void> deleteCity(@Header("Authorization") String authorization,@Path("id") Long userId, @Query("cityID")  Long cityID);


    @GET("/backend/weather/data/recordList")
    Call<List<WeatherRecord>> getRecords(@Header("Authorization") String authorization, @Query("cityID")  Long cityID);


    /***************************************************/

    @POST("/backend/persons/password")
    @FormUrlEncoded
    Call<Void> saveNewPassword(@Header("Authorization") String authorization, @Query("id") Long id,  @Field("password") String password);

    @DELETE("/backend/persons")
    Call<Void> deletePerson(@Header("Authorization") String bearerToken, @Query("id") Long id);
}
