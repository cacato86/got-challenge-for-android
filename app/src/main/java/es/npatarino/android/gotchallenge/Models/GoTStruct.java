package es.npatarino.android.gotchallenge.Models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import es.npatarino.android.gotchallenge.Deserializer.CharactersAndHousesDeserializer;

/**
 * Created by Usuario on 11/03/2016.
 */
public class GoTStruct {

    private HashMap<GoTHouse, ArrayList<GoTCharacter>> charactersAndHouses;

    public GoTStruct(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(HashMap.class, new CharactersAndHousesDeserializer());
        Gson gson = gsonBuilder.create();

        charactersAndHouses = gson.fromJson(jsonString.toString(), HashMap.class);
    }

    public ArrayList<GoTCharacter> getCharactersFromHouse(GoTHouse house) {
        return charactersAndHouses.get(house);
    }

    public ArrayList<GoTHouse> getAllHouses() {
        ArrayList<GoTHouse> totalHouses = new ArrayList<>();
        Iterator it = charactersAndHouses.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            totalHouses.add((GoTHouse) pair.getKey());
        }
        return totalHouses;
    }

    public ArrayList<GoTCharacter> getAllCharacters() {
        ArrayList<GoTCharacter> totalCharacters = new ArrayList<>();
        Iterator it = charactersAndHouses.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            Log.e("KEY", ((GoTHouse) pair.getKey()).getName());
            totalCharacters.addAll((ArrayList<GoTCharacter>) pair.getValue());
        }
        return totalCharacters;
    }
}
