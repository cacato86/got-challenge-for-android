package es.npatarino.android.gotchallenge.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class GoTHouse implements Serializable {

    @SerializedName("houseImageUrl")
    String imageUrl;
    @SerializedName("houseName")
    String name;
    @SerializedName("houseId")
    String id;
    ArrayList<GoTCharacter> charactersOfThisHouse = new ArrayList<>();

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

    public ArrayList<GoTCharacter> getCharactersOfThisHouse() {
        return charactersOfThisHouse;
    }

    public void setCharactersOfThisHouse(ArrayList<GoTCharacter> charactersOfThisHouse) {
        this.charactersOfThisHouse = charactersOfThisHouse;
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
