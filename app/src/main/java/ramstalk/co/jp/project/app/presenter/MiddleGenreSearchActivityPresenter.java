package ramstalk.co.jp.project.app.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableMaybeObserver;
import ramstalk.co.jp.project.app.contract.MiddleGenreSearchActivityContract;
import ramstalk.co.jp.project.data.MiddleGenreList;
import ramstalk.co.jp.project.data.Subscription;
import ramstalk.co.jp.project.data.SubscriptionList;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;
import ramstalk.co.jp.project.domain.repository.factory.ApiObserver;
import ramstalk.co.jp.project.domain.repository.factory.ApiUtil;

/**
 * Created by sugitatakuto on 2017/07/31.
 */

public class MiddleGenreSearchActivityPresenter implements MiddleGenreSearchActivityContract.UserAction {

    private ApiErrorView errorView;
    private MiddleGenreSearchActivityContract.View view;
    private String userId;
    private static final String SUCCESS_LOAD_INFO = "success";
    private static final String FAILURE_LOAD_INFO = "failure";

    public MiddleGenreSearchActivityPresenter(ApiErrorView errorView, MiddleGenreSearchActivityContract.View view, String userId) {
        this.errorView = errorView;
        this.view = view;
        this.userId = userId;
    }

    @Override
    public void setInitialDisplay(String largeGenreId) {
        Observable.zip(
                ApiUtil.getAvailableMiddleGenresForLargeGenre(largeGenreId),
                ApiUtil.getSubscriptions(userId),
                new BiFunction<MiddleGenreList, SubscriptionList, String>() {
                    @Override
                    public String apply(MiddleGenreList middleGenreList, SubscriptionList subscriptionList) {
                        if (middleGenreList == null || middleGenreList.getMiddleGenreList() == null) {
                            view.hideMiddleGenreList();
                            return FAILURE_LOAD_INFO;
                        }

                        List<String> subscriptionsMiddleIds = new ArrayList<>();
                        if (subscriptionList != null && subscriptionList.getSubscriptions() != null) {
                            for (Subscription subscription : subscriptionList.getSubscriptions()) {
                                subscriptionsMiddleIds.add(subscription.getMiddleGenreId());
                            }
                        }
                        view.showMiddleGenreList(middleGenreList.getMiddleGenreList(), subscriptionsMiddleIds);
                        return SUCCESS_LOAD_INFO;
                    }
            }
        ).subscribe(new ApiObserver<String>(errorView) {
            @Override
            public void onNext(String message) {
                if (message.equals(FAILURE_LOAD_INFO)) {
                    errorView.showNetworkError(FAILURE_LOAD_INFO);
                }
            }
        });
    }

    @Override
    public void subscribeMiddleGenre(String middleGenreId) {
        ApiUtil.postRegisterSubscription(userId, middleGenreId)
                .subscribe(new MaybeApiObserver<Void>(errorView));
    }

    @Override
    public void unsubscribeMiddleGenre(String middleGenreId) {
        ApiUtil.deleteSubscription(userId, middleGenreId)
                .subscribe(new MaybeApiObserver<Void>(errorView));
    }

    private class MaybeApiObserver<T> extends DisposableMaybeObserver<T> {

        private ApiErrorView apiErrorView;

        public MaybeApiObserver(ApiErrorView errorView) {
            this.apiErrorView = errorView;
        }

        @Override
        public void onSuccess(T value) {
            // NOP
        }

        @Override
        public void onError(Throwable e) {
            apiErrorView.showServerError(e.getMessage());
        }

        @Override
        public void onComplete() {
            // NOP
        }
    }
}
