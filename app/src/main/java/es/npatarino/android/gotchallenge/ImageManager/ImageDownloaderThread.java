package es.npatarino.android.gotchallenge.ImageManager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

import es.npatarino.android.gotchallenge.Interfaces.ImageManagerInterface;

/**
 * Created by Usuario on 12/03/2016.
 */
public class ImageDownloaderThread implements ImageManagerInterface {

    private final Activity activity;

    public ImageDownloaderThread(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void setImageUrlIntoImageView(final String imageUrl, final ImageView imageView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bmp);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
