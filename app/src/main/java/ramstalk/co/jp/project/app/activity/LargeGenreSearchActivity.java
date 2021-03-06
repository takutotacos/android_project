package ramstalk.co.jp.project.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.app.adapter.GenreAdapter;
import ramstalk.co.jp.project.app.contract.LargeGenreSearchActivityContract;
import ramstalk.co.jp.project.app.presenter.LargeGenreSearchActivityPresenter;
import ramstalk.co.jp.project.app.util.StringUtil;
import ramstalk.co.jp.project.data.Injection;
import ramstalk.co.jp.project.data.LargeGenre;
import ramstalk.co.jp.project.databinding.ActivityLargeGenreSearchBinding;
import ramstalk.co.jp.project.domain.repository.factory.ApiErrorView;

public class LargeGenreSearchActivity extends AppCompatActivity
        implements LargeGenreSearchActivityContract.View, ApiErrorView {

    private LargeGenreSearchActivityContract.UserAction presenter;
    private ActivityLargeGenreSearchBinding binding;
    private String areaCd;
    private SharedPreferences sharedPreferences;

    public static final String MESSAGE_FOR_TOAST = "message_for_toast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_large_genre_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_key_file_name), MODE_PRIVATE);
        showToast();

        LargeGenreSearchActivityContract.View view = this;
        ApiErrorView apiErrorView = this;
        presenter = new LargeGenreSearchActivityPresenter(view, apiErrorView, Injection.provideCityRepository(getApplicationContext()), true);
    }

    @Override
    public void onResume() {
        areaCd = sharedPreferences.getString("areaCd", "shinjuku");
        presenter.setInitialDisplay(areaCd);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        this.presenter = null;
        this.binding = null;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.large_genre_search_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                showAreaSelectingPopUpMenu();
                break;
        }
        return true;
    }


    private void showAreaSelectingPopUpMenu() {
        PopupMenu popup = new PopupMenu(getApplicationContext(), findViewById(R.id.menu_filter));
        popup.getMenuInflater().inflate(R.menu.area_selection_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_shinjuku:
                        areaCd = "shinjuku";
                        break;

                    case R.id.menu_shibuya:
                        areaCd = "shibuya";
                        break;

                    case R.id.menu_nakano:
                        areaCd = "nakano";
                        break;

                    default:
                        areaCd = "none";
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.shared_pref_key_area_cd), areaCd);
                presenter.setInitialDisplay(areaCd);
                return true;
            }
        });
        popup.show();
    }

    @Override
    public void showLargeGenreList(List<LargeGenre> largeGenres) {
        final GenreAdapter<LargeGenre> largeGenreAdapter = new GenreAdapter<>(getApplicationContext());
        largeGenreAdapter.setGenreList(largeGenres);
        binding.lvLargeGenre.setAdapter(largeGenreAdapter);

        binding.lvLargeGenre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedId = String.valueOf(largeGenreAdapter.getItemId(position));
                Intent i = MiddleGenreSearchActivity.createIntent(getApplicationContext(), clickedId);
                startActivity(i);
            }
        });
    }

    @Override
    public void hideLargeGenreList() {
        binding.lvLargeGenre.setVisibility(View.GONE);
        binding.tvLargeGenreNotFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeMasterDataLoadStatusTo(boolean val) {

    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void showServerError(String message) {

    }

    private void showToast() {
        String message = getIntent().getStringExtra(MESSAGE_FOR_TOAST);
        if (StringUtil.isEmpty(message)) {
            return;
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
