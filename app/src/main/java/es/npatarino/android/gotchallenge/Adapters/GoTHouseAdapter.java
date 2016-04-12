package es.npatarino.android.gotchallenge.Adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import es.npatarino.android.gotchallenge.Models.GoTHouse;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ViewModels.HouseViewModel;
import es.npatarino.android.gotchallenge.databinding.HouseRowBinding;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class GoTHouseAdapter extends RecyclerView.Adapter<GoTHouseAdapter.HouseBindingHolder> {

    private static Context context;
    private ArrayList<GoTHouse> housesArray = new ArrayList<>();

    @Inject
    public GoTHouseAdapter(Context context) {
        this.context = context;
    }

    public void setHousesArray(ArrayList<GoTHouse> housesArray) {
        this.housesArray = housesArray;
        notifyDataSetChanged();
    }

    @Override
    public HouseBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HouseRowBinding houseBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.house_row,
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
        final HouseRowBinding binding;

        public HouseBindingHolder(HouseRowBinding binding) {
            super(binding.rlParent);
            this.binding = binding;
        }

        void bindRepository(GoTHouse house) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new HouseViewModel(house));
            } else {
                binding.getViewModel().setHouse(house);
            }
        }
    }

}
