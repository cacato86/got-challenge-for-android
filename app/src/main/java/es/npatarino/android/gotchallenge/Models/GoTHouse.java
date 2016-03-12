package es.npatarino.android.gotchallenge.Models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class GoTHouse {

    @SerializedName("houseImageUrl")
    String imageUrl;
    @SerializedName("houseName")
    String name;
    @SerializedName("houseId")
    String id;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoTHouse goTHouse = (GoTHouse) o;

        return id != null ? id.equals(goTHouse.id) : goTHouse.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
