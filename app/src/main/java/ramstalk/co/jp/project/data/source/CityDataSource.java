package ramstalk.co.jp.project.data.source;

import java.util.List;

import ramstalk.co.jp.project.data.City;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public interface CityDataSource {
    interface LoadCityCallback {

        void onCityLoaded(City city);

        void onDataNotAvailable();

    }

    void getCity(String name, LoadCityCallback callback);

    void updateCities(List<City> cities);
}
