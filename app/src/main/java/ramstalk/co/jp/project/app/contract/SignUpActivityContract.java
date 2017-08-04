package ramstalk.co.jp.project.app.contract;

import ramstalk.co.jp.project.data.User;

/**
 * Created by takuto.sugita on 2017/08/01.
 */

public interface SignUpActivityContract {

    interface View {
        void processUserRegisterSuccess();

        void showNameValidationError(String error);

        void showEmailValidationError(String error);

        void showPasswordValidationError(String error);
    }

    interface Presenter {

        void postUserInfo(User user);

    }
}
