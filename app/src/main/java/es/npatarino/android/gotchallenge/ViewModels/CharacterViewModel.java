package es.npatarino.android.gotchallenge.ViewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import es.npatarino.android.gotchallenge.DetailActivity;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;

/**
 * Created by Usuario on 12/03/2016.
 */
public class CharacterViewModel extends BaseObservable {
    private Context context;
    private GoTCharacter character;

    public CharacterViewModel(Context context, GoTCharacter character) {
        this.context = context;
        this.character = character;
    }

    public void setCharacter(GoTCharacter character) {
        this.character = character;
        notifyChange();
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("character", character);
        context.startActivity(intent);
    }

    public String getName(){
        return character.getName();
    }

    public String getImageUrl(){
        return character.getImageUrl();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
