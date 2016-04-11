package es.npatarino.android.gotchallenge;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.npatarino.android.gotchallenge.Customs.AppCompatActivityCustom;
import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;

public class CharacterDetail extends AppCompatActivityCustom {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            GoTCharacter character = ((GoTCharacter) bundle.get("character"));
            createToolbar(character.getName());
            fillDetail(character);
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void createToolbar(String titleToolbar) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(titleToolbar);
    }

    private void fillDetail(GoTCharacter character) {
        final ImageView imageDetail = (ImageView) findViewById(R.id.iv_photo);
        final TextView DescriptionDetail = (TextView) findViewById(R.id.tv_description);

        DescriptionDetail.setText(character.getDescription());

        ImageManager imageManager = new ImageManager(CharacterDetail.this);
        imageManager.getDowloaderImageTask().setImageUrlIntoImageView(character.getImageUrl(), imageDetail);
    }
}
