package ramstalk.co.jp.project.data.source;

import java.util.List;

import ramstalk.co.jp.project.data.Prefecture;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public interface PrefectureDataSouce {

    interface LoadPrefecturesCallback {

        void onPrefecturesLoaded(List<Prefecture> prefectures);

        void onDataNotAvailable();

    }

    void getPrefectures(LoadPrefecturesCallback callback);

    void savePrefectures(List<Prefecture> prefectures);

    void updatePrefectures(List<Prefecture> prefectures);
}
