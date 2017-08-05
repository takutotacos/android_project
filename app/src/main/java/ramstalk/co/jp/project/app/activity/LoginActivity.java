package ramstalk.co.jp.project.app.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.app.contract.LoginActivityContract;
import ramstalk.co.jp.project.app.presenter.LoginActivityPresenter;
import ramstalk.co.jp.project.databinding.ActivityLoginBinding;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View, ApiErrorView{

    LoginActivityContract.Presenter presenter;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        LoginActivityContract.View view = this;
        ApiErrorView errorView = this;
        presenter = new LoginActivityPresenter(view, errorView);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpUI();
    }

    private void setUpUI() {
        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edEmail.getText().toString();
                String password = binding.edPassword.getText().toString();
                presenter.login(email, password);
            }
        });
    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void showServerError(String message) {

    }

    @Override
    public void showEmailInputError(String message) {
        binding.edEmail.setError(message);
    }

    @Override
    public void showPasswordInputError(String message) {
        binding.edPassword.setError(message);
    }

    @Override
    public void processLoginSuccessful() {
        Intent i = new Intent(getApplicationContext(), LargeGenreSearchActivity.class);
        startActivity(i);
        finish();
    }
}
