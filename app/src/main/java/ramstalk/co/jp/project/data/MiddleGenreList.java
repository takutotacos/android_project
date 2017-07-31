package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by sugitatakuto on 2017/07/30.
 */

@JsonObject
public class MiddleGenreList {

    @JsonField(name="middle_genre_list")
    private List<MiddleGenre> middleGenreList;

    public List<MiddleGenre> getMiddleGenreList() {
        return middleGenreList;
    }

    public void setMiddleGenreList(List<MiddleGenre> middleGenreList) {
        this.middleGenreList = middleGenreList;
    }
}
