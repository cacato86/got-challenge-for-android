package es.npatarino.android.gotchallenge.ImageManager;

import android.app.Activity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import es.npatarino.android.gotchallenge.Interfaces.ImageManagerInterface;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Usuario on 12/03/2016.
 */
public class ImageDownloaderPicasso implements ImageManagerInterface {

    private final Activity activity;

    public ImageDownloaderPicasso(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void setImageUrlIntoImageView(String imageUrl, ImageView imageView) {
        if (!imageUrl.equals("")){
            Picasso.with(activity).load(imageUrl).into(imageView);
        }else{
            Picasso.with(activity).load(R.drawable.got_placeholder).into(imageView);
        }
    }
}
