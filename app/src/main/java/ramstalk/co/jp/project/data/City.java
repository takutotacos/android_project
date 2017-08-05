package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by sugitatakuto on 2017/07/25.
 */
@JsonObject
public class City extends BaseData{
    public String getPrefectureId() {
        return prefectureId;
    }

    public void setPrefectureId(String prefectureId) {
        this.prefectureId = prefectureId;
    }

    @JsonField(name="prefecture_id")
    private String prefectureId;

}
