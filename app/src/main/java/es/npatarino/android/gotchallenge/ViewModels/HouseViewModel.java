package es.npatarino.android.gotchallenge.ViewModels;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import es.npatarino.android.gotchallenge.HouseDetail;
import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTHouse;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class HouseViewModel extends BaseObservable {
    private GoTHouse house;
    private String UNKNOWN_HOUSE = "Unknown House";

    public HouseViewModel(GoTHouse house) {
        this.house = house;
    }

    public void setHouse(GoTHouse house) {
        this.house = house;
        notifyChange();
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(view.getContext(), HouseDetail.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("house", house);
        view.getContext().startActivity(intent);
    }


    public String getName() {
        if (house.getId().equals("")) {
            house.setName(UNKNOWN_HOUSE);
            return UNKNOWN_HOUSE;
        } else {
            return house.getName();
        }
    }

    public String getImageUrl() {
        return house.getImageUrl();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageManager imageManager = new ImageManager(view.getContext());
        imageManager.getDowloaderImageTask().setImageUrlIntoImageView(imageUrl, view);

    }
}
