package ramstalk.co.jp.project.domain.repository.factory;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import ramstalk.co.jp.project.data.*;
import ramstalk.co.jp.project.data.source.UserResult;
import retrofit2.http.*;

/**
 * Created by sugitatakuto on 2017/07/29.
 */

public interface Api {

    @GET("prefectures/")
    Observable<PrefectureList> getAllPrefectures();

    @GET("cities/")
    Observable<CityList> getAllCities();

    @GET("large_genres/{area_id}")
    Observable<LargeGenreList> getAvailableLargeGenresForArea(@Path("area_id") String areaId);

    @GET("middle_genres/{large_genre_id}/")
    Observable<MiddleGenreList> getAvailableMiddleGenresForLargeGenre(@Path("large_genre_id") String largeGenreId);

    @GET("user/{user_id}/subscriptions/")
    Observable<SubscriptionList> getSubscriptions(@Path("user_id") String userid);


    @FormUrlEncoded
    @POST("user/create/")
    Observable<String> postCreateUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/login/")
    Observable<UserResult> postLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/{userId}/subscriptions/")
    Maybe<Void> postRegisterSubscription(@Path("userId") String userId, @Field("user_id") String user_id, @Field("middle_genre_id") String middleGenreId);

    @FormUrlEncoded
    @POST("user/{user_id}/subscriptions/delete/")
    Maybe<Void> deleteSubscription(@Path("user_id") String userId, @Field("middle_genre_id") String middleGenreId);
}
