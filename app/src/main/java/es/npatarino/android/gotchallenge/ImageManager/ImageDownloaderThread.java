package es.npatarino.android.gotchallenge.ImageManager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

import es.npatarino.android.gotchallenge.Interfaces.ImageManagerInterface;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class ImageDownloaderThread implements ImageManagerInterface {

    private final Context context;

    public ImageDownloaderThread(Context context) {
        this.context = context;
    }

    @Override
    public void setImageUrlIntoImageView(final String imageUrl, final ImageView imageView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    ((Activity) context).runOnUiThread(new Runnable() {
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
