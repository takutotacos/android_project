package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

@JsonObject
public class PrefectureList {

    public List<Prefecture> getPrefectureList() {
        return prefectureList;
    }

    public void setPrefectureList(List<Prefecture> prefectureList) {
        this.prefectureList = prefectureList;
    }

    @JsonField(name="prefecture_list")
    private List<Prefecture> prefectureList;
}
