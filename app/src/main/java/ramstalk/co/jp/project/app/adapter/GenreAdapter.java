package ramstalk.co.jp.project.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ramstalk.co.jp.project.R;
import ramstalk.co.jp.project.data.BaseGenreData;

/**
 * Created by sugitatakuto on 2017/07/25.
 */

public class GenreAdapter<E extends BaseGenreData> extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<E> genres;

    public GenreAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGenreList(List<E> genres) {
        this.genres = genres;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_view_genres, parent, false);

        ((TextView) convertView.findViewById(R.id.tv_genre_name)).setText(genres.get(position).getName());
        ((TextView) convertView.findViewById(R.id.tv_topic_number)).setText(String.valueOf(genres.get(position).getSubContentCount()));

        return convertView;
    }
}
