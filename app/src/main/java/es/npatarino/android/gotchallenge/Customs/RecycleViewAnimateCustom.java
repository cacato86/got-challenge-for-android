package es.npatarino.android.gotchallenge.Customs;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Adapters.GoTCharacterAdapter;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;

/**
 * Created by Usuario on 13/03/2016.
 */
public abstract class RecycleViewAnimateCustom extends RecyclerView.Adapter<GoTCharacterAdapter.CharacterBindingHolder> {
    public static Activity activity;
    public ArrayList<GoTCharacter> charactersArray = new ArrayList<>();

    public RecycleViewAnimateCustom(Activity activity) {
        this.activity = activity;
    }

    public void setCharacterArray(ArrayList<GoTCharacter> characters) {
        charactersArray = new ArrayList<>(characters);
        notifyDataSetChanged();
    }

    public void animateTo(ArrayList<GoTCharacter> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<GoTCharacter> newModels) {
        for (int i = charactersArray.size() - 1; i >= 0; i--) {
            final GoTCharacter model = charactersArray.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<GoTCharacter> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final GoTCharacter model = newModels.get(i);
            if (!charactersArray.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<GoTCharacter> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final GoTCharacter model = newModels.get(toPosition);
            final int fromPosition = charactersArray.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public GoTCharacter removeItem(int position) {
        final GoTCharacter model = charactersArray.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, GoTCharacter model) {
        charactersArray.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final GoTCharacter model = charactersArray.remove(fromPosition);
        charactersArray.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}
