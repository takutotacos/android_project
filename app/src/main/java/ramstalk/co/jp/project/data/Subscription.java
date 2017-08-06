package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by sugitatakuto on 2017/08/06.
 */
@JsonObject
public class Subscription {
    @JsonField
    private String id;

    @JsonField(name="user_id")
    private String userId;

    @JsonField(name="middle_genre_id")
    private String middleGenreId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMiddleGenreId() {
        return middleGenreId;
    }

    public void setMiddleGenreId(String middleGenreId) {
        this.middleGenreId = middleGenreId;
    }
}
