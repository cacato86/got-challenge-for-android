package es.npatarino.android.gotchallenge.ViewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import java.util.IllegalFormatException;

import es.npatarino.android.gotchallenge.CharacterDetail;
import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class CharacterViewModel extends BaseObservable {
    private GoTCharacter character;

    public CharacterViewModel(GoTCharacter character) {
        this.character = character;
    }

    public void setCharacter(GoTCharacter character) {
        this.character = character;
        notifyChange();
    }

    public void onItemClick(View view) throws IllegalFormatException{
        Context context = view.getContext();
        Intent intent = new Intent(context, CharacterDetail.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("character", character);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView imageShared = (ImageView) view.findViewById(R.id.ivBackground);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, imageShared, context.getString(R.string.image_detail_trans));
            ActivityCompat.startActivity((Activity) context, intent, options.toBundle());
        } else {
            context.startActivity(intent);
        }
    }

    public String getName() {
        return character.getName();
    }

    public String getImageUrl() {
        return character.getImageUrl();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageManager imageManager = new ImageManager(view.getContext());
        imageManager.getDowloaderImageTask().setImageUrlIntoImageView(imageUrl, view);
    }
}
