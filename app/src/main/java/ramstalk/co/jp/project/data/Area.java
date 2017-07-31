package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by sugitatakuto on 2017/07/25.
 */

@JsonObject
public class Area extends BaseData {
    @JsonField
    private City city;

    @JsonField
    private Prefecture prefecture;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Prefecture getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(Prefecture prefecture) {
        this.prefecture = prefecture;
    }
}
