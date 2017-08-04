package ramstalk.co.jp.project.domain.repository.factory;

import java.io.IOException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by sugitatakuto on 2017/07/30.
 */

public abstract class ApiObserver<T> extends DisposableObserver<T> {

    private ApiErrorView view;

    public ApiObserver(ApiErrorView view) {
        this.view = view;
    }

    @Override
    public void onComplete() {
        // NOP
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException) {
            view.showNetworkError(e.getMessage());
        } else if (e instanceof HttpException) {
            view.showServerError(e.getMessage());
        } else {
            view.showServerError(e.getMessage());
        }
    }
}
