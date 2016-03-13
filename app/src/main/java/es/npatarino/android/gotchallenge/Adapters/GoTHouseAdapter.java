package es.npatarino.android.gotchallenge.Adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.Models.GoTHouse;
import es.npatarino.android.gotchallenge.Models.GoTStruct;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ViewModels.CharacterViewModel;
import es.npatarino.android.gotchallenge.ViewModels.HouseViewModel;
import es.npatarino.android.gotchallenge.databinding.GotCharacterRowBinding;
import es.npatarino.android.gotchallenge.databinding.GotHouseRowBinding;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTHouseAdapter extends RecyclerView.Adapter<GoTHouseAdapter.HouseBindingHolder> {

    private static Activity activity;
    private ArrayList<GoTHouse> housesArray;

    public GoTHouseAdapter(Activity activity, ArrayList<GoTHouse> houses) {
        this.activity = activity;
        this.housesArray = houses;
    }

    @Override
    public HouseBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         GotHouseRowBinding houseBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.got_house_row,
                parent,
                false);
        return new HouseBindingHolder(houseBinding);
    }

    @Override
    public void onBindViewHolder(HouseBindingHolder holder, int position) {
        holder.bindRepository(housesArray.get(position));
    }

    @Override
    public int getItemCount() {
        return housesArray.size();
    }

    public static class HouseBindingHolder extends RecyclerView.ViewHolder {
        final GotHouseRowBinding binding;

        public HouseBindingHolder(GotHouseRowBinding binding) {
            super(binding.rlParent);
            this.binding = binding;
        }

        void bindRepository(GoTHouse house) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new HouseViewModel(activity, house));
            } else {
                binding.getViewModel().setHouse(house);
            }
        }
    }

}
