package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by sugitatakuto on 2017/07/25.
 */

@JsonObject
public class MiddleGenre extends BaseGenreData {
    @JsonField(name="large_genre")
    private LargeGenre largeGenre;

    @JsonField
    private Area area;

    public MiddleGenre() {
    }

    public MiddleGenre(LargeGenre largeGenre, Area area) {
        this.largeGenre = largeGenre;
        this.area = area;
    }

    public LargeGenre getLargeGenre() {
        return largeGenre;
    }

    public void setLargeGenre(LargeGenre largeGenre) {
        this.largeGenre = largeGenre;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

}
