package ramstalk.co.jp.project.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by takuto.sugita on 2017/08/01.
 */

@JsonObject
public class User extends BaseData {

    @JsonField
    private String email;

    @JsonField
    private String password;

    private String passowrdConfirmation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassowrdConfirmation() {
        return passowrdConfirmation;
    }

    public void setPassowrdConfirmation(String passowrdConfirmation) {
        this.passowrdConfirmation = passowrdConfirmation;
    }
}
