package es.npatarino.android.gotchallenge;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Adapters.GoTCharacterAdapter;
import es.npatarino.android.gotchallenge.Customs.AppCompatActivityCustom;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.Models.GoTHouse;
import es.npatarino.android.gotchallenge.Utils.Utils;

/**
 * Created by Usuario on 12/03/2016.
 */
public class HouseDetail extends AppCompatActivityCustom implements SearchView.OnQueryTextListener {

    private ContentLoadingProgressBar progressBar;
    private RecyclerView charactersList;
    private GoTCharacterAdapter characterAdapter;
    private ArrayList<GoTCharacter> charactersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_detail);

        progressBar = (ContentLoadingProgressBar) findViewById(R.id.pb);
        charactersList = (RecyclerView) findViewById(R.id.rv);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            GoTHouse house = ((GoTHouse) bundle.get("house"));
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
        charactersArray = house.getCharactersOfThisHouse();
        characterAdapter = new GoTCharacterAdapter(HouseDetail.this);
        characterAdapter.setCharacterArray(charactersArray);
        charactersList.setLayoutManager(new LinearLayoutManager(HouseDetail.this));
        charactersList.setHasFixedSize(true);
        charactersList.setAdapter(characterAdapter);
        progressBar.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.ic_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final ArrayList<GoTCharacter> filteredModelList = Utils.filterList(charactersArray, query);
        characterAdapter.animateTo(filteredModelList);
        charactersList.scrollToPosition(0);
        return true;
    }
}
