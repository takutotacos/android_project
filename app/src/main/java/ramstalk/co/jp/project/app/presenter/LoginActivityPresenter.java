package ramstalk.co.jp.project.app.presenter;

import ramstalk.co.jp.project.app.contract.LoginActivityContract;
import ramstalk.co.jp.project.app.util.StringUtil;
import ramstalk.co.jp.project.data.User;
import ramstalk.co.jp.project.data.source.UserResult;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;
import ramstalk.co.jp.project.domain.repository.factory.ApiObserver;
import ramstalk.co.jp.project.domain.repository.factory.ApiUtil;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public class LoginActivityPresenter implements LoginActivityContract.Presenter {

    private LoginActivityContract.View view;
    private ApiErrorView errorView;
    private String userId;

    public LoginActivityPresenter(LoginActivityContract.View view, ApiErrorView errorView, String userId) {
        this.view = view;
        this.errorView = errorView;
        this.userId = userId;
    }

    @Override
    public void setInitialUI() {
        if (StringUtil.isEmpty(userId)) {
            return;
        }
        view.processLoginSuccessful(userId);
    }

    @Override
    public void login(String email, String password) {
        if (!checkUserInput(email, password)) {
            return;
        }

        ApiUtil.postLogin(email, password)
                .subscribe(new ApiObserver<UserResult>(errorView) {
                    @Override
                    public void onNext(UserResult user) {
                        view.processLoginSuccessful(user.getUser().getId());
                    }
                });
    }

    private boolean checkUserInput(String email, String password) {
        boolean isValidInput = true;

        if (StringUtil.isEmpty(email)) {
            view.showEmailInputError("入力必須です。");
            isValidInput =false;
        }

        if (!StringUtil.isEmpty(email) && !email.contains("@")) {
            view.showEmailInputError("メールアドレスを入力してください。");
            isValidInput =false;
        }

        if (StringUtil.isEmpty(password)) {
            view.showPasswordInputError("入力必須です。");
            isValidInput =false;
        }

        return isValidInput;
    }
}
