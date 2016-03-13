package es.npatarino.android.gotchallenge.Models;

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

    public ArrayList<GoTHouse> getAllHouses() {
        ArrayList<GoTHouse> totalHouses = new ArrayList<>();
        if (charactersAndHouses != null && charactersAndHouses.size() > 0) {
            Iterator it = charactersAndHouses.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                GoTHouse house = (GoTHouse) pair.getKey();
                house.setCharactersOfThisHouse((ArrayList<GoTCharacter>) pair.getValue());
                totalHouses.add(0, house);
            }
        }
        return totalHouses;
    }

    public ArrayList<GoTCharacter> getAllCharacters() {
        ArrayList<GoTCharacter> totalCharacters = new ArrayList<>();
        if (charactersAndHouses != null && charactersAndHouses.size() > 0) {
            Iterator it = charactersAndHouses.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                totalCharacters.addAll(0, (ArrayList<GoTCharacter>) pair.getValue());
            }
        }
        return totalCharacters;
    }
}
