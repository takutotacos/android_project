package ramstalk.co.jp.project.app.listener;

/**
 * Created by sugitatakuto on 2017/08/06.
 */

public interface GenreCheckListener {

    void onGenreSubscribed(String middleGenreId);

    void onGenreUnsubscribed(String middleGenreId);

}
