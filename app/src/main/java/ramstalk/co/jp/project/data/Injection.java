package ramstalk.co.jp.project.data;

import android.content.Context;

import ramstalk.co.jp.project.data.source.CityRepository;
import ramstalk.co.jp.project.data.source.local.CityLocalDataSource;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public class Injection {

    public static CityRepository provideCityRepository(Context context) {
        return CityRepository.getInstance(CityLocalDataSource.getInstance(context));
    }
}
