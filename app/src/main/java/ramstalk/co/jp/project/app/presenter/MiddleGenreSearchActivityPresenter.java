package ramstalk.co.jp.project.app.presenter;

import ramstalk.co.jp.project.app.contract.MiddleGenreSearchActivityContract;
import ramstalk.co.jp.project.data.MiddleGenreList;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;
import ramstalk.co.jp.project.domain.repository.factory.ApiObserver;
import ramstalk.co.jp.project.domain.repository.factory.ApiUtil;

/**
 * Created by sugitatakuto on 2017/07/31.
 */

public class MiddleGenreSearchActivityPresenter implements MiddleGenreSearchActivityContract.UserAction {

    private ApiErrorView errorView;
    private MiddleGenreSearchActivityContract.View view;

    public MiddleGenreSearchActivityPresenter(ApiErrorView errorView, MiddleGenreSearchActivityContract.View view) {
        this.errorView = errorView;
        this.view = view;
    }

    @Override
    public void setInitialDisplay(String largeGenreId) {
        ApiUtil.getAvailableMiddleGenresForLargeGenre(largeGenreId)
                .subscribe(new ApiObserver<MiddleGenreList>(errorView) {
                    @Override
                    public void onNext(MiddleGenreList middleGenreList) {
                        if (middleGenreList == null || middleGenreList.getMiddleGenreList().size() == 0) {
                            view.hideMiddleGenreList();
                            return;
                        }
                        view.showMiddleGenreList(middleGenreList.getMiddleGenreList());
                    }
                });
    }
}
