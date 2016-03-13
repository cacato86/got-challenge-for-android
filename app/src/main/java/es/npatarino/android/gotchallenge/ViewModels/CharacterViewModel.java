package es.npatarino.android.gotchallenge.ViewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import es.npatarino.android.gotchallenge.CharacterDetail;
import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Usuario on 12/03/2016.
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

    public void onItemClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, CharacterDetail.class);
        intent.putExtra("character", character);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
             View imageShared = view.findViewById(R.id.ivBackground);
            View txtShared = view.findViewById(R.id.tv_name);
            Pair<View, String> pairImage = Pair.create(imageShared, imageShared.getTransitionName());
            Pair<View, String> pairText = Pair.create(txtShared, txtShared.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairImage, pairText);

            /*ImageView imageShared = (ImageView) view.findViewById(R.id.ivBackground);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, imageShared, context.getString(R.string.image_detail_trans));*/
            context.startActivity(intent, options.toBundle());
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
