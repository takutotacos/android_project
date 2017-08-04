package ramstalk.co.jp.project.domain.repository.factory;

import io.reactivex.Observable;
import ramstalk.co.jp.project.data.LargeGenreList;
import ramstalk.co.jp.project.data.MiddleGenreList;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sugitatakuto on 2017/07/29.
 */

public interface Api {

    @GET("large_genres/{area_id}")
    Observable<LargeGenreList> getAvailableLargeGenresForArea(@Path("area_id") String areaId);

    @GET("middle_genres/{large_genre_id}/")
    Observable<MiddleGenreList> getAvailableMiddleGenresForLargeGenre(@Path("large_genre_id") String largeGenreId);

    @FormUrlEncoded
    @POST("user/create/")
    Observable<String> postCreateUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);

}
