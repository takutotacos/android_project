package ramstalk.co.jp.project.domain.repository.factory;

/**
 * Created by sugitatakuto on 2017/07/30.
 */

public interface ApiErrorView {
    void showNetworkError(String message);

    void showServerError(String message);
}
