package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by sugitatakuto on 2017/07/30.
 */

@JsonObject
public class LargeGenreList {
    @JsonField(name="large_genre_list")
    private List<LargeGenre> largeGenreList;

    public List<LargeGenre> getLargeGenreList() {
        return largeGenreList;
    }

    public void setLargeGenreList(List<LargeGenre> largeGenreList) {
        this.largeGenreList = largeGenreList;
    }
}
