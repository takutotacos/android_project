package ramstalk.co.jp.project.app.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.app.contract.SignUpActivityContract;
import ramstalk.co.jp.project.app.presenter.SignUpActivityPresenter;
import ramstalk.co.jp.project.data.User;
import ramstalk.co.jp.project.databinding.ActivitySignUpBinding;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;

public class SignUpActivity extends AppCompatActivity implements SignUpActivityContract.View, ApiErrorView {

    private ActivitySignUpBinding binding;
    private SignUpActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ApiErrorView apiErrorView = this;
        SignUpActivityContract.View view = this;
        presenter = new SignUpActivityPresenter(apiErrorView, view);
    }

    @Override
    public void onResume() {
       super.onResume();
        setupUI();
    }

    private void setupUI() {
        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.postUserInfo(createUserFromInput());
            }
        });
    }

    private User createUserFromInput() {
        String email = binding.edEmail.toString();
        String password = binding.edPassword.toString();
        String passwordConfirmation = binding.edPasswordConfirm.toString();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPassowrdConfirmation(passwordConfirmation);
        return user;
    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void showServerError(String message) {

    }

    @Override
    public void processUserRegisterSuccess() {
//        SharedPreferences.Editor editor =

        Intent i = new Intent(getApplicationContext(), LargeGenreSearchActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showEmailValidationError(String error) {
        binding.edEmail.setError(error);
    }

    @Override
    public void showPasswordValidationError(String error) {
        binding.edPassword.setError(error);
    }

}
