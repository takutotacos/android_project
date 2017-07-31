package ramstalk.co.jp.project.data;

import android.graphics.Bitmap;
import android.nfc.Tag;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by sugitatakuto on 2017/07/25.
 */

@JsonObject
public class Topic extends BaseData {
    @JsonField
    private String title;

    @JsonField
    private String description;

    @JsonField(name="large_genre")
    private LargeGenre largeGenre;

    @JsonField(name="middle_genre")
    private MiddleGenre middleGenre;

    @JsonField(name="area_id")
    private Area areaId;

    @JsonField
    private List<Tag> tags;

    @JsonField
    private Bitmap image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LargeGenre getLargeGenre() {
        return largeGenre;
    }

    public void setLargeGenre(LargeGenre largeGenre) {
        this.largeGenre = largeGenre;
    }

    public MiddleGenre getMiddleGenre() {
        return middleGenre;
    }

    public void setMiddleGenre(MiddleGenre middleGenre) {
        this.middleGenre = middleGenre;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
