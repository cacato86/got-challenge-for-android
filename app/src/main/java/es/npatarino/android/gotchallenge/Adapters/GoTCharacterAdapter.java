package es.npatarino.android.gotchallenge.Adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ViewModels.CharacterViewModel;
import es.npatarino.android.gotchallenge.databinding.GotCharacterRowBinding;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTCharacterAdapter extends RecyclerView.Adapter<GoTCharacterAdapter.CharacterBindingHolder> {

    private static Activity activity;
    private ArrayList<GoTCharacter> charactersArray;

    public GoTCharacterAdapter(Activity activity, ArrayList<GoTCharacter> characters) {
        this.activity = activity;
        this.charactersArray = characters;
    }

    @Override
    public CharacterBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GotCharacterRowBinding characterBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.got_character_row,
                parent,
                false);
        return new CharacterBindingHolder(characterBinding);
    }

    @Override
    public void onBindViewHolder(CharacterBindingHolder holder, int position) {
        holder.bindCharacter(charactersArray.get(position));
    }

    @Override
    public int getItemCount() {
        return charactersArray.size();
    }

    public static class CharacterBindingHolder extends RecyclerView.ViewHolder {
        final GotCharacterRowBinding binding;

        public CharacterBindingHolder(GotCharacterRowBinding binding) {
            super(binding.rlParent);
            this.binding = binding;
        }

        void bindCharacter(GoTCharacter character) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new CharacterViewModel(activity, character));
            } else {
                binding.getViewModel().setCharacter(character);
            }
        }
    }

}
