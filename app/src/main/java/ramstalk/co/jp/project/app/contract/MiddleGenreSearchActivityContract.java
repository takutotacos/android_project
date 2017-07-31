package ramstalk.co.jp.project.app.contract;

import java.util.List;

import ramstalk.co.jp.project.data.MiddleGenre;

/**
 * Created by sugitatakuto on 2017/07/31.
 */

public interface MiddleGenreSearchActivityContract {
    interface View {
        void showMiddleGenreList(List<MiddleGenre> middleGenres);

        void hideMiddleGenreList();
    }

    interface UserAction {
        void setInitialDisplay(String largeGenreId);
    }

}
