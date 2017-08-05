package ramstalk.co.jp.project.app.contract;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public interface LoginActivityContract {

    interface View {

        void showEmailInputError(String message);

        void showPasswordInputError(String message);

        void processLoginSuccessful();

    }

    interface Presenter {

        void login(String email, String password);

    }
}
