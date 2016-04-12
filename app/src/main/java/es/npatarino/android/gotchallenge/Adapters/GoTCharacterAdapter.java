package es.npatarino.android.gotchallenge.Adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import es.npatarino.android.gotchallenge.Customs.RecycleViewAnimateCustom;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ViewModels.CharacterViewModel;
import es.npatarino.android.gotchallenge.databinding.CharacterRowBinding;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class GoTCharacterAdapter extends RecycleViewAnimateCustom {

    @Inject public GoTCharacterAdapter(Context context) {
        super(context);
    }

    @Override
    public CharacterBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CharacterRowBinding characterBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.character_row,
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
        final CharacterRowBinding binding;

        public CharacterBindingHolder(CharacterRowBinding binding) {
            super(binding.rlParent);
            this.binding = binding;
        }

        void bindCharacter(GoTCharacter character) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new CharacterViewModel(character));
            } else {
                binding.getViewModel().setCharacter(character);
            }
        }
    }

}
