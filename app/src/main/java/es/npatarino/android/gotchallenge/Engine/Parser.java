package es.npatarino.android.gotchallenge.Engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Deserializer.CharactersAndHousesDeserializer;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.Models.GoTHouse;

/**
 * Created by Usuario on 11/03/2016.
 */
public class Parser {

    private final String jsonString;

    public Parser(String jsonString) {
        this.jsonString = jsonString;
    }

    public ArrayList<GoTHouse> getAllHouses() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ArrayList.class, new CharactersAndHousesDeserializer());
        Gson gson = gsonBuilder.create();
        ArrayList<GoTHouse> houseGson = gson.fromJson(jsonString.toString(), ArrayList.class);
        if (houseGson != null) {
            return houseGson;
        } else {
            return new ArrayList<>();
        }
    }

    public ArrayList<GoTCharacter> getAllCharacters() {
        Type listType = new TypeToken<ArrayList<GoTCharacter>>() {
        }.getType();
        ArrayList<GoTCharacter> characterGson = new Gson().fromJson(jsonString, listType);
        if (characterGson != null) {
            return characterGson;
        } else {
            return new ArrayList<>();
        }
    }
}
