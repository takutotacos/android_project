package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by sugitatakuto on 2017/07/29.
 */

@JsonObject
public class BaseGenreData extends BaseData {
    @JsonField(name="sub_content_count")
    private int subContentCount;

    public int getSubContentCount() {
        return subContentCount;
    }

    public void setSubContentCount(int subContentCount) {
        this.subContentCount = subContentCount;
    }
}
