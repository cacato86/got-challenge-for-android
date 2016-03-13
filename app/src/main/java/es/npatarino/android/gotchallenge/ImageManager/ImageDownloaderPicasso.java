package es.npatarino.android.gotchallenge.ImageManager;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import es.npatarino.android.gotchallenge.Interfaces.ImageManagerInterface;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Usuario on 12/03/2016.
 */
public class ImageDownloaderPicasso implements ImageManagerInterface {

    private final Context context;

    public ImageDownloaderPicasso(Context context) {
        this.context = context;
    }

    @Override
    public void setImageUrlIntoImageView(String imageUrl, ImageView imageView) {
        if (!imageUrl.equals("")){
            Picasso.with(context).load(imageUrl).into(imageView);
        }else{
            Picasso.with(context).load(R.drawable.got_placeholder).into(imageView);
        }
    }
}
