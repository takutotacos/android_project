package ramstalk.co.jp.project.data.source;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import ramstalk.co.jp.project.data.User;

/**
 * Created by sugitatakuto on 2017/08/06.
 */
@JsonObject
public class UserResult {
    @JsonField(name="user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
