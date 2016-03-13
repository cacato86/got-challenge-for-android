package es.npatarino.android.gotchallenge;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.npatarino.android.gotchallenge.Adapters.GoTCharacterAdapter;
import es.npatarino.android.gotchallenge.Adapters.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.ImageManager.ImageManager;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.Models.GoTHouse;

/**
 * Created by Usuario on 12/03/2016.
 */
public class HouseDetail extends AppCompatActivity {
    private ContentLoadingProgressBar progressBar;
    private RecyclerView charactersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_detail);

        progressBar = (ContentLoadingProgressBar) findViewById(R.id.pb);
        charactersList = (RecyclerView) findViewById(R.id.rv);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            GoTHouse house = ((GoTHouse) bundle.get("house"));
            Log.e("HOUSE",house.getName());
            createToolbar(house.getName());
            fillDetail(house);
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void createToolbar(String titleToolbar) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        toolbar.setTitle(titleToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void fillDetail(GoTHouse house) {
        final GoTCharacterAdapter characterAdapter = new GoTCharacterAdapter(HouseDetail.this, house.getCharactersOfThisHouse());
        charactersList.setLayoutManager(new LinearLayoutManager(HouseDetail.this));
        charactersList.setHasFixedSize(true);
        charactersList.setAdapter(characterAdapter);
        progressBar.hide();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.ic_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }
}
