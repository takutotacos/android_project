package ramstalk.co.jp.project.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.app.listener.GenreCheckListener;
import ramstalk.co.jp.project.data.BaseGenreData;

/**
 * Created by sugitatakuto on 2017/07/25.
 */

public class GenreAdapter<E extends BaseGenreData> extends BaseAdapter {

    private Context context;
    private GenreCheckListener genreCheckListener;
    private LayoutInflater layoutInflater;
    private List<E> genres;
    private List<String> subscriptionsMiddleIds;


    public GenreAdapter(Context context, GenreCheckListener genreCheckListener) {
        this(context);
        this.genreCheckListener = genreCheckListener;
    }

    public GenreAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGenreList(List<E> genres) {
        this.genres = genres;
    }

    public void setSubscriptionsMiddleIds(List<String> subscriptionsMiddleIds) {
        this.subscriptionsMiddleIds = subscriptionsMiddleIds;
    }

    @Override
    public int getCount() {
        return genres.size();
    }

    @Override
    public E getItem(int position) {
        return genres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(genres.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_view_genres, parent, false);

        ((TextView) convertView.findViewById(R.id.tv_genre_name)).setText(genres.get(position).getName());
        ((TextView) convertView.findViewById(R.id.tv_topic_number)).setText(String.valueOf(genres.get(position).getSubContentCount()));

        if (subscriptionsMiddleIds == null) {
            // 大ジャンルの場合
            ((ToggleButton) convertView.findViewById(R.id.tb_subscribe_middle_genre)).setVisibility(View.GONE);
        } else {
            // 中ジャンルの場合（ジャンル自体をフォローできる）

            ((ToggleButton) convertView.findViewById(R.id.tb_subscribe_middle_genre)).setVisibility(View.VISIBLE);
            ((ToggleButton) convertView.findViewById(R.id.tb_subscribe_middle_genre)).setSelected(false);
            ((ToggleButton) convertView.findViewById(R.id.tb_subscribe_middle_genre)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        genreCheckListener.onGenreSubscribed(genres.get(position).getId());
                    } else {
                        genreCheckListener.onGenreUnsubscribed(genres.get(position).getId());
                    }
                }
            });

            if (subscriptionsMiddleIds.contains(genres.get(position).getId())) {
                ((ToggleButton) convertView.findViewById(R.id.tb_subscribe_middle_genre)).setSelected(true);
            }
        }
        return convertView;
    }
}
