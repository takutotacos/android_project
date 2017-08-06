package ramstalk.co.jp.project.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.app.contract.LoginActivityContract;
import ramstalk.co.jp.project.app.presenter.LoginActivityPresenter;
import ramstalk.co.jp.project.databinding.ActivityLoginBinding;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View, ApiErrorView{

    private LoginActivityContract.Presenter presenter;
    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_key_file_name), MODE_PRIVATE);
        userId = sharedPreferences.getString(getString(R.string.shared_pref_key_user_id), "");

        LoginActivityContract.View view = this;
        ApiErrorView errorView = this;
        presenter = new LoginActivityPresenter(view, errorView, userId);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpUI();
    }

    private void setUpUI() {
        presenter.setInitialUI();
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    public void processLoginSuccessful(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.shared_pref_key_user_id), userId);
        editor.apply();

        Intent i = new Intent(getApplicationContext(), LargeGenreSearchActivity.class);
        startActivity(i);
        finish();
    }
}
