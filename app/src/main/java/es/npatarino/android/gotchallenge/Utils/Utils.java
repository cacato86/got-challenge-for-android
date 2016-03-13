package es.npatarino.android.gotchallenge.Utils;

import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Models.GoTCharacter;

/**
 * Created by Usuario on 13/03/2016.
 */
public class Utils {
    public static ArrayList<GoTCharacter> filterList(ArrayList<GoTCharacter> models, String query) {
        query = query.toLowerCase();

        final ArrayList<GoTCharacter> filteredModelList = new ArrayList<>();
        for (GoTCharacter model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}