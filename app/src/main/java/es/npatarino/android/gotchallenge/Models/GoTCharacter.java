package es.npatarino.android.gotchallenge.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nicolás Patarino on 21/02/16.
 */
public class GoTCharacter implements Serializable {

    @SerializedName("name")
    String name;
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("description")
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
