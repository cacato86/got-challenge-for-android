package es.npatarino.android.gotchallenge.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import es.npatarino.android.gotchallenge.HouseDetail;
import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTHouse;

/**
 * Created by Usuario on 12/03/2016.
 */
public class HouseViewModel extends BaseObservable {
    private static Activity activity;
    private GoTHouse house;

    public HouseViewModel(Activity activity, GoTHouse house) {
        this.activity = activity;
        this.house = house;
    }

    public void setHouse(GoTHouse house) {
        this.house = house;
        notifyChange();
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(activity, HouseDetail.class);
        intent.putExtra("house", house);
        activity.startActivity(intent);
    }

    public String getName() {
        return house.getName();
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
