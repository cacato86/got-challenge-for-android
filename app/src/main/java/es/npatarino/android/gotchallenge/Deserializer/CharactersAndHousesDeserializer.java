package es.npatarino.android.gotchallenge.Deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.Models.GoTHouse;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class CharactersAndHousesDeserializer implements JsonDeserializer<ArrayList<GoTHouse>> {

    private ArrayList<GoTHouse> houses = new ArrayList<>();

    @Override
    public ArrayList<GoTHouse> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        final JsonArray jsonData = json.getAsJsonArray();
        final int sizeData = jsonData.size();

        for (int i = 0; i < sizeData; i++) {
            JsonObject element = jsonData.get(i).getAsJsonObject();
            GoTCharacter character = gson.fromJson(element, GoTCharacter.class);
            GoTHouse house = gson.fromJson(element, GoTHouse.class);
            addCharacterToHouse(character, house);
        }
        return houses;
    }

    private void addCharacterToHouse(GoTCharacter character, GoTHouse house) {
        if (!houses.contains(house)) {
            if (!house.getCharactersOfThisHouse().contains(character)) {
                house.addCharacterToThisHouse(character);
                houses.add(house);
            }
        } else {
            int indexHouse = houses.indexOf(house);
            houses.get(indexHouse).addCharacterToThisHouse(character);
        }
    }
}
