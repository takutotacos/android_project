package ramstalk.co.jp.project.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.app.contract.MiddleGenreSearchActivityContract;
import ramstalk.co.jp.project.app.presenter.MiddleGenreSearchActivityPresenter;
import ramstalk.co.jp.project.data.MiddleGenre;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;

public class MiddleGenreSearchActivity extends AppCompatActivity implements MiddleGenreSearchActivityContract.View, ApiErrorView {

    public static final String MIDDLE_GENRE_ID = "middle_genre_id";
    private String largeGenreId;
    private MiddleGenreSearchActivityContract.UserAction presenter;

    public static Intent createIntent(Context context, String middleGenreId) {
        Intent i = new Intent(context, MiddleGenreSearchActivity.class);
        i.putExtra(MIDDLE_GENRE_ID, middleGenreId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_genre_search);

        largeGenreId = getIntent().getStringExtra(MIDDLE_GENRE_ID);

        MiddleGenreSearchActivityContract.View view = this;
        ApiErrorView errorView = this;
        presenter = new MiddleGenreSearchActivityPresenter(errorView, view);
    }

    @Override
    public void onResume() {
        presenter.setInitialDisplay(largeGenreId);
        super.onResume();

    }

    @Override
    public void showMiddleGenreList(List<MiddleGenre> middleGenres) {

    }

    @Override
    public void hideMiddleGenreList() {

    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void showServerError(String message) {

    }
}
