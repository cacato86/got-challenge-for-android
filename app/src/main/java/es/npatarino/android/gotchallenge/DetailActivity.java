package es.npatarino.android.gotchallenge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            GoTCharacter character = ((GoTCharacter) bundle.get("character"));
            Log.e("CHAR", character.getName()+" /");
            createToolbar(character.getName());
            fillDetail(character);
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void createToolbar(String titleToolbar){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        toolbar.setTitle(titleToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void fillDetail(GoTCharacter character) {
        final ImageView imageDetail = (ImageView) findViewById(R.id.iv_photo);
        final TextView nameDetail = (TextView) findViewById(R.id.tv_name);
        final TextView DescriptionDetail = (TextView) findViewById(R.id.tv_description);

        nameDetail.setText(character.getName());
        DescriptionDetail.setText(character.getDescription());

        ImageManager imageManager = new ImageManager(DetailActivity.this);
        imageManager.getDowloaderImageTask().setImageUrlIntoImageView(character.getImageUrl(), imageDetail);

    }
}
