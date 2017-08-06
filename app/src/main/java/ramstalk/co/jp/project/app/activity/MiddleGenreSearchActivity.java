package ramstalk.co.jp.project.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.app.adapter.GenreAdapter;
import ramstalk.co.jp.project.app.contract.MiddleGenreSearchActivityContract;
import ramstalk.co.jp.project.app.listener.GenreCheckListener;
import ramstalk.co.jp.project.app.presenter.MiddleGenreSearchActivityPresenter;
import ramstalk.co.jp.project.data.MiddleGenre;
import ramstalk.co.jp.project.databinding.ActivityMiddleGenreSearchBinding;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;


public class MiddleGenreSearchActivity extends AppCompatActivity
        implements MiddleGenreSearchActivityContract.View, ApiErrorView, GenreCheckListener {

    public static final String MIDDLE_GENRE_ID = "middle_genre_id";
    private String largeGenreId;
    private String userId;
    private SharedPreferences sharedPreferences;
    private MiddleGenreSearchActivityContract.UserAction presenter;
    private ActivityMiddleGenreSearchBinding binding;

    public static Intent createIntent(Context context, String middleGenreId) {
        Intent i = new Intent(context, MiddleGenreSearchActivity.class);
        i.putExtra(MIDDLE_GENRE_ID, middleGenreId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_middle_genre_search);

        setupToolbar();
        setupNecessaryInfo();

        MiddleGenreSearchActivityContract.View view = this;
        ApiErrorView errorView = this;
        presenter = new MiddleGenreSearchActivityPresenter(errorView, view, userId);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupNecessaryInfo() {
        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_key_file_name), MODE_PRIVATE);
        largeGenreId = getIntent().getStringExtra(MIDDLE_GENRE_ID);
        userId = sharedPreferences.getString(getString(R.string.shared_pref_key_user_id), "");
    }

    @Override
    public void onGenreSubscribed(String middleGenreId) {
        presenter.subscribeMiddleGenre(middleGenreId);
    }

    @Override
    public void onGenreUnsubscribed(String middleGenreId) {
        presenter.unsubscribeMiddleGenre(middleGenreId);
    }

    @Override
    public void onResume() {
        presenter.setInitialDisplay(largeGenreId);
        super.onResume();
    }

    @Override
    public void showMiddleGenreList(List<MiddleGenre> middleGenres, List<String> subscriptionMiddleIds) {
        GenreCheckListener genreCheckListener = this;
        final GenreAdapter<MiddleGenre> middleGenreGenreAdapter = new GenreAdapter<>(getApplicationContext(), genreCheckListener);

        middleGenreGenreAdapter.setGenreList(middleGenres);
        middleGenreGenreAdapter.setSubscriptionsMiddleIds(subscriptionMiddleIds);

        binding.lvMiddleGenre.setAdapter(middleGenreGenreAdapter);
        binding.lvMiddleGenre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedId = String.valueOf(middleGenreGenreAdapter.getItemId(position));
                Intent i = MiddleGenreSearchActivity.createIntent(getApplicationContext(), clickedId);
                startActivity(i);
            }
        });
    }

    @Override
    public void hideMiddleGenreList() {
        binding.lvMiddleGenre.setVisibility(View.GONE);
        binding.tvMiddleGenreNotFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void showServerError(String message) {

    }
}