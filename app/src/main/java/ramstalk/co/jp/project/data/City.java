package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by sugitatakuto on 2017/07/25.
 */
@JsonObject
public class City {
    @JsonField
    private Prefecture prefecture;

    public Prefecture getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(Prefecture prefecture) {
        this.prefecture = prefecture;
    }
}
