package ramstalk.co.jp.project.app.presenter;

import android.text.TextUtils;

import ramstalk.co.jp.project.app.contract.SignUpActivityContract;
import ramstalk.co.jp.project.data.User;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;

/**
 * Created by takuto.sugita on 2017/08/01.
 */

public class SignUpActivityPresenter implements SignUpActivityContract.Presenter {

    ApiErrorView errorView;
    SignUpActivityContract.View view;

    public SignUpActivityPresenter(ApiErrorView errorView, SignUpActivityContract.View view) {
        this.errorView = errorView;
        this.view = view;
    }

    @Override
    public void postUserInfo(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String passwordConfirmation = user.getPassowrdConfirmation();

        if (TextUtils.isEmpty(email)) {
            view.showEmailValidationError("からです。");
        }
        if (TextUtils.isEmpty(password)) {
            view.showPasswordValidationError("入力必須です。");
        }

        if (!TextUtils.isEmpty(email) && !email.contains("@")) {
            view.showEmailValidationError("メールアドレスを入力してください。");
        }
        if (!password.equals(passwordConfirmation)) {
            view.showPasswordValidationError("パスワードが合致しません。");
        }


    }
}
