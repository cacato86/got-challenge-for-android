package es.npatarino.android.gotchallenge.ImageManager;

import android.app.Activity;
import android.widget.ImageView;

import es.npatarino.android.gotchallenge.Interfaces.ImageManagerInterface;

/**
 * Created by Usuario on 12/03/2016.
 */
public class ImageManager {
    private ImageManagerInterface dowloaderImageTask;

    public ImageManager(Activity activity) {
        dowloaderImageTask = createDownloaderTask(activity);
    }

    private ImageManagerInterface createDownloaderTask(Activity activity) {
        //return new ImageDownloaderPicasso(activity);
        return new ImageDownloaderThread(activity);
    }

    public ImageManagerInterface getDowloaderImageTask() {
        return dowloaderImageTask;
    }
}
