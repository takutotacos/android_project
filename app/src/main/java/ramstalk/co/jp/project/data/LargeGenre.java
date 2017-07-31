package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by sugitatakuto on 2017/07/25.
 */
@JsonObject
public class LargeGenre extends BaseGenreData {
    @JsonField
    private City city;

    public LargeGenre() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
