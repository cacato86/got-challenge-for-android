package es.npatarino.android.gotchallenge.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import es.npatarino.android.gotchallenge.CharacterDetail;
import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;

/**
 * Created by Usuario on 12/03/2016.
 */
public class CharacterViewModel extends BaseObservable {
    private static Activity activity;
    private GoTCharacter character;

    public CharacterViewModel(Activity activity, GoTCharacter character) {
        this.activity = activity;
        this.character = character;
    }

    public void setCharacter(GoTCharacter character) {
        this.character = character;
        notifyChange();
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(activity, CharacterDetail.class);
        intent.putExtra("character", character);
        activity.startActivity(intent);
    }

    public String getName(){
        return character.getName();
    }

    public String getImageUrl(){
        return character.getImageUrl();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageManager imageManager = new ImageManager(view.getContext());
        imageManager.getDowloaderImageTask().setImageUrlIntoImageView(imageUrl, view);
    }
}
