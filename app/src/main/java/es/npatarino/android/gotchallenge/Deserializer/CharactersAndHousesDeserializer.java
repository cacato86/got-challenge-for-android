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
import java.util.HashMap;

import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.Models.GoTHouse;

/**
 * Created by Usuario on 12/03/2016.
 */
public class CharactersAndHousesDeserializer implements JsonDeserializer<HashMap<GoTHouse, ArrayList<GoTCharacter>>> {

    private HashMap<GoTHouse, ArrayList<GoTCharacter>> formatedData;

    @Override
    public HashMap<GoTHouse, ArrayList<GoTCharacter>> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        formatedData = new HashMap<>();
        ArrayList<GoTCharacter> charactersArray = new ArrayList<>();
        Gson gson = new Gson();

        final JsonArray jsonData = json.getAsJsonArray();
        final int sizeData = jsonData.size();

        for (int i = 0; i < sizeData; i++) {
            JsonObject element = jsonData.get(i).getAsJsonObject();
            GoTCharacter character = gson.fromJson(element, GoTCharacter.class);
            GoTHouse house = gson.fromJson(element, GoTHouse.class);
            addCharacterToHouse(character, house);
        }
        return formatedData;
    }

    private void addCharacterToHouse(GoTCharacter character, GoTHouse house) {
        if (formatedData.containsKey(house)) {
            ArrayList<GoTCharacter> charactersFromHouse = formatedData.get(house);
            if (!charactersFromHouse.contains(character)) {
                charactersFromHouse.add(character);
            }
        } else {
            ArrayList<GoTCharacter> characters = new ArrayList<>();
            characters.add(character);
            formatedData.put(house, characters);
        }
    }
}
