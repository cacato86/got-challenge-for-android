package es.npatarino.android.gotchallenge.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
    private String UNKNOWN_HOUSE = "Unknown House";

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

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
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
