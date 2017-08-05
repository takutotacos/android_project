package ramstalk.co.jp.project.data.source;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ramstalk.co.jp.project.data.City;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public class CityRepository implements CityDataSource {

    private static CityRepository INSTANCE = null;
    private final CityDataSource cityLocalDataSource;
    Map<String, City> cachedCities;

    // prevent direct instantiation.
    private CityRepository(@NonNull CityDataSource cityLocalDataSource) {
        this.cityLocalDataSource = cityLocalDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param cityLocalDataSource
     * @return
     */
    public static CityRepository getInstance(CityDataSource cityLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CityRepository(cityLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * used to force {@link #getInstance(CityDataSource)} to create a new instance
     * next time it's called
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getCity(final String name, final LoadCityCallback callback) {
        City city = getCityWithName(name);

        // respond immediately with cache if available
        if (city != null) {
            callback.onCityLoaded(city);
            return;
        }

        // Load from server/persisted if needed.
        cityLocalDataSource.getCity(name, new LoadCityCallback() {
            @Override
            public void onCityLoaded(City city) {
                // Do in memory cache update to keep the app UI up to date
                if (cachedCities == null) {
                    cachedCities = new LinkedHashMap<String, City>();
                }
                cachedCities.put(city.getName(), city);
                callback.onCityLoaded(city);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateCities(List<City> cities) {
        cityLocalDataSource.updateCities(cities);
    }

    private City getCityWithName(String name) {
        if (cachedCities == null || cachedCities.isEmpty()) {
            return null;
        } else {
            return cachedCities.get(name);
        }
    }
}
