package es.npatarino.android.gotchallenge.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import es.npatarino.android.gotchallenge.DetailActivity;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ViewModels.CharacterViewModel;
import es.npatarino.android.gotchallenge.databinding.GotCharacterRowBinding;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTCharacterAdapter extends RecyclerView.Adapter<GoTCharacterAdapter.CharacterBindingHolder> {

    private ArrayList<GoTCharacter> charactersArray;

    public GoTCharacterAdapter(ArrayList<GoTCharacter> characters) {
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
        holder.bindRepository(charactersArray.get(position));
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

        void bindRepository(GoTCharacter character) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new CharacterViewModel(itemView.getContext(), character));
            } else {
                binding.getViewModel().setCharacter(character);
            }
        }
    }

}
