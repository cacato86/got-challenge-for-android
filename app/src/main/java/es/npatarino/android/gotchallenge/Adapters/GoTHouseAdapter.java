package es.npatarino.android.gotchallenge.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Models.GoTHouse;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTHouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GoTHouse> housesArray;
    private Activity activity;

    public GoTHouseAdapter(Activity activity, ArrayList<GoTHouse> housesArray) {
        this.housesArray = housesArray;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.got_house_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotCharacterViewHolder gotCharacterViewHolder = (GotCharacterViewHolder) holder;
        gotCharacterViewHolder.render(housesArray.get(position));
    }

    @Override
    public int getItemCount() {
        return housesArray.size();
    }

    class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";
        ImageView houseImage;

        public GotCharacterViewHolder(View itemView) {
            super(itemView);
            houseImage = (ImageView) itemView.findViewById(R.id.ivBackground);
        }

        public void render(final GoTHouse goTHouse) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (!goTHouse.getImageUrl().equals("")){
                        try {
                            URL url = new URL(goTHouse.getImageUrl());
                            final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    houseImage.setImageBitmap(bmp);
                                }
                            });
                        } catch (IOException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                        }
                    }else{
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                houseImage.setImageResource(R.drawable.got_placeholder);
                            }
                        });
                    }
                }
            }).start();
        }
    }

}
