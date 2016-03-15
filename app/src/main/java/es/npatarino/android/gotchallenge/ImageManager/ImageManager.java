package es.npatarino.android.gotchallenge.ImageManager;

import android.content.Context;

import es.npatarino.android.gotchallenge.Interfaces.ImageManagerInterface;

/**
 * Created by Usuario on 12/03/2016.
 */
public class ImageManager {
    private final Context context;

    public ImageManager(Context context) {
        this.context = context;
    }

    public ImageManagerInterface getDowloaderImageTask() {
        //return new ImageDownloaderThread(context);
        return new ImageDownloaderPicasso(context);
    }
}
