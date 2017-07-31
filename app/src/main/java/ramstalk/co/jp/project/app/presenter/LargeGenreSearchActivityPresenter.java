package ramstalk.co.jp.project.app.presenter;

import android.content.Context;
import android.text.TextUtils;

import ramstalk.co.jp.project.app.contract.LargeGenreSearchActivityContract;
import ramstalk.co.jp.project.data.LargeGenreList;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;
import ramstalk.co.jp.project.domain.repository.factory.ApiObserver;
import ramstalk.co.jp.project.domain.repository.factory.ApiUtil;

/**
 * Created by sugitatakuto on 2017/07/24.
 */

public class LargeGenreSearchActivityPresenter implements LargeGenreSearchActivityContract.UserAction {

    private Context context;
    private LargeGenreSearchActivityContract.View view;
    private ApiErrorView errorView;

    public LargeGenreSearchActivityPresenter(Context context,LargeGenreSearchActivityContract.View view, ApiErrorView errorView) {
        this.context = context;
        this.view = view;
        this.errorView = errorView;
    }


    @Override
    public void setInitialDisplay(String areaCd) {
        if (TextUtils.isEmpty(areaCd)) {
            areaCd = "shinjuku";
        }
        //@Todo
        // 1. put all the cities into sqlight during starting up
        // 2. get the city object matching the "areaCd" gained from the activity
        // 3. pass the city_id to get getLargeGenreList(city_id)
        switch (areaCd) {
            case "shinjuku":
                getLargeGenreList("3");
                break;
            case "shibuya":
                getLargeGenreList("12");
                break;

            case "nakano":
                getLargeGenreList("13");
                break;

            default:
                getLargeGenreList("1");
        }
    }

    // 地域ごとに取得対象（お気に入り？）の中ジャンルのみ取得する。
    private void getLargeGenreList(String areaId) {
        ApiUtil.getAvailableLargeGenresForArea(areaId)
                .subscribe(new ApiObserver<LargeGenreList>(errorView) {
                    @Override
                    public void onNext(LargeGenreList largeGenres) {
                        if (largeGenres == null || largeGenres.getLargeGenreList().size() == 0) {
                            view.hideLargeGenreList();
                            return;
                        }
                        view.showLargeGenreList(largeGenres.getLargeGenreList());
                    }
                });
    }
}
