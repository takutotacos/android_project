package ramstalk.co.jp.project.app.presenter;

import ramstalk.co.jp.project.app.contract.LargeGenreSearchActivityContract;
import ramstalk.co.jp.project.data.City;
import ramstalk.co.jp.project.data.CityList;
import ramstalk.co.jp.project.data.LargeGenreList;
import ramstalk.co.jp.project.data.source.CityDataSource;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;
import ramstalk.co.jp.project.domain.repository.factory.ApiObserver;
import ramstalk.co.jp.project.domain.repository.factory.ApiUtil;

/**
 * Created by sugitatakuto on 2017/07/24.
 */

public class LargeGenreSearchActivityPresenter implements LargeGenreSearchActivityContract.UserAction, CityDataSource.LoadCityCallback {

    private LargeGenreSearchActivityContract.View view;
    private ApiErrorView errorView;
    private final CityDataSource cityRepository;
    private boolean shouldLoadMasterDataFromRemote;

    public LargeGenreSearchActivityPresenter(LargeGenreSearchActivityContract.View view, ApiErrorView errorView, CityDataSource cityRepository, boolean shouldLoadMasterDataFromRemote) {
        this.view = view;
        this.errorView = errorView;
        this.cityRepository = cityRepository;
        this.shouldLoadMasterDataFromRemote = shouldLoadMasterDataFromRemote;
    }


    @Override
    public void setInitialDisplay(String areaCd) {
        switch (areaCd) {
            case "shinjuku":
                areaCd = "新宿区";
                break;
            case "shibuya":
                areaCd = "渋谷区";
                break;
            case "nakano":
                areaCd = "中野区";
                break;
            default:
                areaCd = "新宿区";
        }

        // get the city object matching the "areaCd" gained from the activity
        if (shouldLoadMasterDataFromRemote) {
            loadMasterDataFromRemote(areaCd, this);
        } else {
            cityRepository.getCity(areaCd, this);
        }
    }

    // 地域ごとに取得対象（お気に入り？）の中ジャンルのみ取得する。
    @Override
    public void onCityLoaded(City city) {
        ApiUtil.getAvailableLargeGenresForArea(city.getId())
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

    @Override
    public void onDataNotAvailable() {
        errorView.showServerError("Something went wrong");
    }

    private void loadMasterDataFromRemote(final String areaCd, final CityDataSource.LoadCityCallback loadCityCallback) {
        // make sure to have master data in the local db
        ApiUtil.getAllCities()
        .subscribe(new ApiObserver<CityList>(errorView) {
            @Override
            public void onNext(CityList cities) {
                cityRepository.updateCities(cities.getCityList());

                view.changeMasterDataLoadStatusTo(true);
                shouldLoadMasterDataFromRemote = false;
                cityRepository.getCity(areaCd, loadCityCallback);
            }
        });
    }
}
