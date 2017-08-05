package ramstalk.co.jp.project.app.contract;

import java.util.List;

import ramstalk.co.jp.project.data.LargeGenre;

/**
 * Created by sugitatakuto on 2017/07/24.
 */

public interface LargeGenreSearchActivityContract {

    interface View {
        void showLargeGenreList(List<LargeGenre> largeGenreList);

        void hideLargeGenreList();

        void changeMasterDataLoadStatusTo(boolean val);
    }

    interface UserAction {
        void setInitialDisplay(String areaId);
    }
}
