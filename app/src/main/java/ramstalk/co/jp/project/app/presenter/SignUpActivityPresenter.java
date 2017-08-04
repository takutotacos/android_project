package ramstalk.co.jp.project.app.presenter;

import ramstalk.co.jp.project.app.contract.SignUpActivityContract;
import ramstalk.co.jp.project.app.util.StringUtil;
import ramstalk.co.jp.project.data.User;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;
import ramstalk.co.jp.project.domain.repository.factory.ApiObserver;
import ramstalk.co.jp.project.domain.repository.factory.ApiUtil;

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
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String passwordConfirmation = user.getPassowrdConfirmation();

        if (!validateUserInput(name, email, password, passwordConfirmation)) {
            return;
        }

        ApiUtil.postCreateUser(name, email, password)
                .subscribe(new ApiObserver<String>(errorView) {
                    @Override
                    public void onNext(String value) {
                        view.processUserRegisterSuccess();
                    }
                });
    }

    private boolean validateUserInput(String name, String email, String password, String passwordConfirmation) {
        boolean isValid = true;

        if(StringUtil.isEmpty(name)) {
            view.showNameValidationError("入力必須です。");
            isValid = false;
        }
        if (StringUtil.isEmpty(email)) {
            view.showEmailValidationError("入力必須です。");
            isValid = false;
        }
        if (StringUtil.isEmpty(password)) {
            view.showPasswordValidationError("入力必須です。");
            isValid = false;
        }
        if (!StringUtil.isEmpty(email) && !email.contains("@")) {
            view.showEmailValidationError("メールアドレスを入力してください。");
            isValid = false;
        }
        if (!StringUtil.isEmpty(password) && !password.equals(passwordConfirmation)) {
            view.showPasswordValidationError("パスワードが合致しません。");
            isValid = false;
        }
        return isValid;
    }
}
