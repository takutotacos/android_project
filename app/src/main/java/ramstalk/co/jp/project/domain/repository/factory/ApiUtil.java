package ramstalk.co.jp.project.domain.repository.factory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ramstalk.co.jp.project.data.LargeGenreList;
import ramstalk.co.jp.project.data.MiddleGenreList;

/**
 * Created by sugitatakuto on 2017/07/29.
 */

public class ApiUtil {

    // GET

    public static Observable<LargeGenreList> getAvailableLargeGenresForArea(String areaId) {
        return RetrofitAdapter.getRetrofit()
                .create(Api.class)
                .getAvailableLargeGenresForArea(areaId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<MiddleGenreList> getAvailableMiddleGenresForLargeGenre(String largeGenreId) {
        return RetrofitAdapter.getRetrofit()
                .create(Api.class)
                .getAvailableMiddleGenresForLargeGenre(largeGenreId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // POST

    public static Observable<String> postCreateUser(String name, String email, String password) {
        return RetrofitAdapter.getRetrofit()
                .create(Api.class)
                .postCreateUser(name, email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
